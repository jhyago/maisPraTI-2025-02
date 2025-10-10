/*
Este exemplo mostra o ciclo de vida típico ao usar Hibernate "puro" (sem Spring):
1) Construir uma única SessionFactory (objeto pesado, thread-safe).
2) Abrir uma Session por unidade de trabalho (NÃO é thread-safe).
3) Iniciar uma transação (obrigatório para persistência consistente).
4) Executar operações (save/update/delete/query).
5) Commit (ou rollback em caso de erro).
6) Fechar recursos (Session e, ao finalizar a aplicação, SessionFactory).

Conceitos importantes usados nos comentários:
- ESTADOS DA ENTIDADE:
  * transient: objeto Java novo, ainda não conhecido pelo Hibernate (sem id persistido).
  * persistent/managed: objeto associado a uma Session ativa (o Hibernate rastreia mudanças).
  * detached: objeto que já foi persistido, mas cuja Session foi fechada/expirada.
- FLUSH:
  * Momento em que o Hibernate sincroniza o contexto de persistência com o banco (gera SQL).
  * Ocorre automaticamente antes de commit, ou quando necessário (ex.: query que depende de dados recém-salvos).
- TRANSAÇÃO:
  * Agrupa operações atômicas; garante consistência (commit) ou desfaz tudo (rollback).
- THREAD-SAFETY:
  * SessionFactory é thread-safe e deve existir uma única instância por aplicação.
  * Session NÃO é thread-safe; use uma por requisição/unidade de trabalho.

================================================================================
*/

import model.Cardapio;                 // Entidade @Entity mapeada para uma tabela (ex.: cardapio).
import model.Comanda;                  // Outra entidade do domínio da aplicação.
import model.Item_comanda;             // Entidade que provavelmente relaciona Comanda e itens do Cardapio.
import org.hibernate.Session;          // Representa um contexto de persistência conectado ao banco (não thread-safe).
import org.hibernate.SessionFactory;   // Fábrica de Sessions (pesada e thread-safe); cacheia metadados, mapeamentos etc.
import org.hibernate.cfg.Configuration;// Permite carregar hibernate.cfg.xml e registrar classes anotadas.

public class Main {
    public static void main(String[] args) {

        /*
         * 1) CONSTRUÇÃO DA SESSIONFACTORY
         * - new Configuration().configure(): lê o arquivo hibernate.cfg.xml do classpath
         *   (por padrão: src/main/resources/hibernate.cfg.xml).
         * - addAnnotatedClass(...): registra explicitamente entidades @Entity. (Se tiver
         *   "scanning" configurado, pode ser opcional; aqui é explícito para evitar dúvidas.)
         * - buildSessionFactory(): cria a SessionFactory, que é cara de construir.
         *
         * BOA PRÁTICA:
         * - Criar apenas UMA SessionFactory por aplicação e mantê-la viva até o shutdown.
         */
        SessionFactory factory = new Configuration()
                .configure() // carrega driver/url/credenciais/dialeto, etc. do hibernate.cfg.xml
                .addAnnotatedClass(Cardapio.class)
                .addAnnotatedClass(Comanda.class)
                .addAnnotatedClass(Item_comanda.class)
                .buildSessionFactory();

        // A Session representa um "contexto de primeira linha" (first-level cache) do Hibernate.
        // Cada Session mantém um conjunto de entidades "managed" (persistent state) e suas mudanças.
        // NÃO compartilhe a Session entre threads; crie/feche por unidade de trabalho.
        Session session = null;

        try {
            /*
             * 2) ABRIR UMA SESSION
             * - openSession(): você gerencia explicitamente o ciclo de vida (abrir/fechar).
             * - getCurrentSession(): alternativo que exige configurar "hibernate.current_session_context_class"
             *   (p.ex. "thread") e integra o fechamento no commit/rollback. Aqui usamos openSession() por clareza.
             */
            session = factory.openSession();

            /*
             * 3) INICIAR UMA TRANSAÇÃO
             * - Sempre inicie a transação ANTES de executar operações de escrita/leitura que dependem de consistência.
             * - O Hibernate fará flush automático antes do commit, garantindo que o SQL correspondente seja enviado.
             */
            session.beginTransaction();

            /*
             * 4) CRIAR A ENTIDADE EM ESTADO TRANSIENT
             * - Neste ponto, o objeto ainda não é conhecido pelo Hibernate (sem id no banco).
             * - Após chamar save()/persist(), passará a estado "persistent" dentro desta Session.
             */
            Cardapio cafe1 = new Cardapio();
            cafe1.setNome("Pizza");                     // Atribuímos atributos conforme mapeamento da entidade.
            cafe1.setDescricao("Pizza de calabresa");   // Esses valores serão persistidos como colunas no banco.
            cafe1.setPreco_unitario(10.0);              // Use tipos apropriados (BigDecimal para dinheiro em produção).

            /*
             * 5) OPERAÇÃO DE PERSISTÊNCIA
             * - save(cafe1):
             *   * Transiciona a entidade de transient -> persistent (associada à Session).
             *   * Gera a identificação (id) dependendo da estratégia (@GeneratedValue, sequência, etc.).
             *   * NÃO executa necessariamente o INSERT imediatamente; o SQL é emitido no flush/commit.
             *
             * - Diferença entre persist() e save() (resumo):
             *   * persist(): sem retorno, segue JPA; exceção se a entidade já tiver id gerado/gerenciamento incorreto.
             *   * save(): retorna o id; é mais "Hibernate nativo".
             */
            session.save(cafe1);

            /*
             * (Opcional) Aqui poderíamos fazer queries que já "veem" a entidade recém-persistida,
             * porque o Hibernate fará flush automático quando necessário (antes da query).
             */

            /*
             * 6) COMMIT
             * - Ao fazer commit:
             *   * O Hibernate executa flush (se ainda não o fez), emitindo INSERT/UPDATE/DELETE pendentes.
             *   * A transação do banco confirma as mudanças; todas as operações são aplicadas de forma atômica.
             * - Após o commit, as entidades podem continuar "managed" enquanto a Session estiver aberta;
             *   ao fechar a Session, elas se tornam "detached".
             */
            session.getTransaction().commit();

            /*
             * PÓS-COMMIT:
             * - Se você tentar acessar relações LAZY depois de fechar a Session, ocorrerá LazyInitializationException.
             * - Estratégias para evitar: inicializar antes do close, usar fetch join, ajustar fetch type, ou
             *   usar um padrão "Open Session in View" (com cuidado) em frameworks web.
             */

        } catch (Exception e) {
            /*
             * 7) TRATAMENTO DE ERROS E ROLLBACK
             * - Em caso de falha, faça rollback se a transação estiver ativa para garantir consistência.
             * - O rollback desfaz todas as operações não confirmadas.
             */
            if (session != null && session.getTransaction().isActive()) {
                try {
                    session.getTransaction().rollback();
                } catch (Exception rollbackError) {
                    // Logue adequadamente em um logger real; aqui simplificamos.
                    System.err.println("Falha ao executar rollback: " + rollbackError.getMessage());
                }
            }
            // Propaga como RuntimeException com contexto; em apps reais, prefira um logger.
            throw new RuntimeException("Erro ao persistir Cardapio", e);

        } finally {
            /*
             * 8) FECHAMENTO DE RECURSOS
             * - Sempre feche a Session após a unidade de trabalho (libera conexões e contexto).
             * - Feche a SessionFactory quando a aplicação encerrar (geralmente em um gancho de shutdown).
             */
            if (session != null && session.isOpen()) {
                try {
                    session.close(); // Ao fechar, entidades "managed" tornam-se "detached".
                } catch (Exception closeError) {
                    System.err.println("Falha ao fechar a Session: " + closeError.getMessage());
                }
            }
            try {
                factory.close(); // Libera caches, pools e metadados; faça isso uma única vez no fim da app.
            } catch (Exception factoryCloseError) {
                System.err.println("Falha ao fechar a SessionFactory: " + factoryCloseError.getMessage());
            }
        }
    }
}
