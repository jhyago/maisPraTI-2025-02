import java.time.Duration; // Importa Duration para calcular tempos de espera
import java.time.LocalDateTime; // Importa LocalDateTime para registrar chegada/atendimento
import java.time.format.DateTimeFormatter; // Importa formatador de datas/horas
import java.util.ArrayDeque; // Importa implementação de Deque baseada em array
import java.util.ArrayList; // Importa ArrayList para histórico
import java.util.Deque; // Importa interface de fila dupla (Deque)
import java.util.List; // Importa interface List
import java.util.Locale; // Importa Locale para padronizar comparações
import java.util.Scanner; // Importa Scanner para ler comandos do usuário

/**
 * Gerenciador de fila de atendimento com prioridades.
 * Regra de atendimento: a cada ciclo, atender **2 preferenciais** para **1 normal** (2P:1N).
 * Este arquivo está **comentado linha a linha** e traz extras: status, relatório, modo demo e saída limpa.
 */
public class GerenciadorFilaAtendimento { // Declaração da classe principal

    // ===================== CONFIGURAÇÕES E TIPOS =====================

    enum Tipo { // Enumeração para diferenciar tipos de senha
        PREFERENCIAL, // Valor para senha preferencial
        NORMAL        // Valor para senha normal
    } // Fim do enum Tipo

    static final class Senha { // Classe imutável (exceto atendimento) que representa uma senha
        final Tipo tipo; // Tipo da senha (preferencial ou normal)
        final String codigo; // Código legível da senha (p.ex.: P001, N010)
        final LocalDateTime chegada; // Momento em que a senha entrou na fila
        LocalDateTime atendimento; // Momento em que a senha foi atendida (nulo até ser atendida)

        Senha(Tipo tipoParametro, String codigoParametro, LocalDateTime chegadaParametro) { // Construtor da senha
            this.tipo = tipoParametro; // Atribui o tipo recebido ao campo
            this.codigo = codigoParametro; // Atribui o código recebido ao campo
            this.chegada = chegadaParametro; // Atribui o instante de chegada recebido ao campo
            this.atendimento = null; // Inicialmente ainda não atendida (nulo ao invés de agora)
        } // Fim do construtor

        @Override // Indica que estamos sobrescrevendo toString padrão
        public String toString() { // Representação textual amigável para histórico
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss"); // Define formato HH:mm:ss
            String textoChegada = chegada.format(formatoHora); // Formata a hora de chegada
            String textoAtend = (atendimento == null) ? "(pendente)" : atendimento.format(formatoHora); // Formata atendimento
            String textoTipo = (tipo == Tipo.PREFERENCIAL ? "Preferencial" : "Normal"); // Texto do tipo
            String textoEspera; // Declaramos variável para exibir o tempo de espera
            if (atendimento == null) { // Se ainda não foi atendida
                textoEspera = "-"; // Não há espera a exibir
            } else { // Caso já tenha atendimento
                long segundosEspera = Duration.between(chegada, atendimento).getSeconds(); // Calcula espera em segundos
                textoEspera = segundosEspera + "s"; // Constrói string de espera
            } // Fim do if/else para espera
            return codigo + " [" + textoTipo + "] chegada=" + textoChegada + ", atendimento=" + textoAtend + ", espera=" + textoEspera; // Monta linha
        } // Fim do toString
    } // Fim da classe Senha

    // Constante para a regra 2P:1N (pode ser alterada para experimentar outras razões, se desejar)
    private static final int RAZAO_PREFERENCIAL = 2; // Atender 2 preferenciais antes de 1 normal

    // Formatador de data/hora padrão para mensagens rápidas
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss"); // HH:mm:ss

    // ===================== INTERFACE DE TEXTO =====================

    private static void imprimirMenu() { // Imprime o menu de ajuda/comandos
        System.out.println(
                "=== Gerenciador de Fila (Regra: " + RAZAO_PREFERENCIAL + " Preferenciais : 1 Normal) ===" // Cabeçalho com regra
                        + "\nComandos:" // Introdução aos comandos
                        + "\n- chegar p | chegar pref : adiciona senha preferencial" // Chegada pref
                        + "\n- chegar n | chegar normal : adiciona senha normal" // Chegada normal
                        + "\n- atender : chama a próxima senha (regra 2P:1N)" // Atender próxima
                        + "\n- historico : mostra o histórico de chamadas" // Histórico simples
                        + "\n- status : mostra tamanhos das filas e próximos a serem chamados" // Estado atual
                        + "\n- relatorio : estatísticas (totais e tempo médio de espera)" // Estatística extra
                        + "\n- demo : simula chegadas e atendimentos automaticamente" // Modo demonstração
                        + "\n- ajuda : reimprime este menu" // Reimpressão do menu
                        + "\n- sair : encerra imprimindo histórico e relatório"); // Encerramento
    } // Fim do método imprimirMenu

    // ===================== PONTO DE ENTRADA =====================

    public static void main(String[] args) { // Método main (início do programa)
        Scanner leitorComandos = new Scanner(System.in); // Cria Scanner para ler entradas do usuário

        Deque<Senha> filaPreferencial = new ArrayDeque<>(); // Fila de preferenciais (entrada no fim, saída no início)
        Deque<Senha> filaNormal = new ArrayDeque<>(); // Fila de normais
        List<Senha> historicoChamadas = new ArrayList<>(); // Histórico das senhas atendidas

        int sequenciaPreferencial = 1; // Contador sequencial para códigos de preferenciais
        int sequenciaNormal = 1; // Contador sequencial para códigos de normais

        int preferenciaisSeguidas = 0; // Quantas preferenciais já foram atendidas seguidamente neste ciclo

        imprimirMenu(); // Exibe o menu inicial

        while (true) { // Loop principal do programa
            System.out.print("> "); // Prompt visual para o usuário
            String linhaDigitada = leitorComandos.nextLine().trim(); // Lê a linha de comando e remove espaços extras

            if (linhaDigitada.isEmpty()) { // Se o usuário não digitou nada
                continue; // Volta ao início do loop e pede novamente
            } // Fim do if de linha vazia

            String comandoNormalizado = linhaDigitada.toLowerCase(Locale.ROOT); // Normaliza para comparação case-insensitive

            switch (comandoNormalizado) { // Início do switch para comandos simples sem parâmetros
                case "ajuda" -> { // Se o usuário digitou "ajuda"
                    imprimirMenu(); // Reimprime o menu
                } // Fim do caso ajuda
                case "status" -> { // Se o usuário pediu status
                    imprimirStatus(filaPreferencial, filaNormal, preferenciaisSeguidas, sequenciaPreferencial, sequenciaNormal); // Mostra estado
                } // Fim do caso status
                case "historico" -> { // Se pediu histórico
                    imprimirHistorico(historicoChamadas); // Imprime histórico
                } // Fim do caso histórico
                case "relatorio" -> { // Se pediu relatório
                    imprimirRelatorio(historicoChamadas); // Gera e imprime estatísticas
                } // Fim do caso relatório
                case "demo" -> { // Se pediu o modo de demonstração
                    int[] novosNumeradores = executarDemo(filaPreferencial, filaNormal, historicoChamadas, sequenciaPreferencial, sequenciaNormal); // Executa demo e recebe novas sequências
                    sequenciaPreferencial = novosNumeradores[0]; // Atualiza sequência de preferenciais
                    sequenciaNormal = novosNumeradores[1]; // Atualiza sequência de normais
                    System.out.println("(demo concluída às " + LocalDateTime.now().format(FORMATO_HORA) + ")"); // Feedback de conclusão
                } // Fim do caso demo
                case "atender" -> { // Se o usuário pediu para atender
                    preferenciaisSeguidas = processarProximoAtendimento(filaPreferencial, filaNormal, historicoChamadas, preferenciaisSeguidas); // Processa e atualiza contador
                } // Fim do caso atender
                case "sair" -> { // Se o usuário decidiu encerrar
                    System.out.println("\n=== Encerrando... ==="); // Mensagem de encerramento
                    imprimirHistorico(historicoChamadas); // Mostra histórico final
                    imprimirRelatorio(historicoChamadas); // Mostra relatório final
                    leitorComandos.close(); // Fecha o Scanner (boa prática)
                    return; // Sai do programa
                } // Fim do caso sair
                default -> { // Comandos com parâmetros ou não reconhecidos
                    if (comandoNormalizado.startsWith("chegar ")) { // Trata comandos do tipo "chegar X"
                        String parametroTipo = comandoNormalizado.substring("chegar ".length()).trim(); // Isola o que vem após "chegar"
                        if (parametroTipo.equals("p") || parametroTipo.equals("pref") || parametroTipo.equals("preferencial")) { // Chegada preferencial
                            String codigoGerado = construirCodigo(Tipo.PREFERENCIAL, sequenciaPreferencial++); // Gera código Pxxx e incrementa contador
                            Senha novaSenhaPreferencial = new Senha(Tipo.PREFERENCIAL, codigoGerado, LocalDateTime.now()); // Cria senha com chegada agora
                            filaPreferencial.addLast(novaSenhaPreferencial); // Enfileira no fim da fila preferencial
                            System.out.println("Chegada registrada: " + codigoGerado + " (Preferencial). Em espera: " + filaPreferencial.size()); // Feedback ao usuário
                        } else if (parametroTipo.equals("n") || parametroTipo.equals("normal")) { // Chegada normal
                            String codigoGerado = construirCodigo(Tipo.NORMAL, sequenciaNormal++); // Gera código Nxxx e incrementa contador
                            Senha novaSenhaNormal = new Senha(Tipo.NORMAL, codigoGerado, LocalDateTime.now()); // Cria senha normal
                            filaNormal.addLast(novaSenhaNormal); // Enfileira no fim da fila normal
                            System.out.println("Chegada registrada: " + codigoGerado + " (Normal). Em espera: " + filaNormal.size()); // Feedback ao usuário
                        } else { // Caso o parâmetro de chegada seja desconhecido
                            System.out.println("Tipo de chegada não reconhecido. Use 'chegar p' ou 'chegar n'."); // Mensagem de erro
                        } // Fim do if/else de tipos de chegada
                    } else { // Se não for um comando de chegada nem um conhecido
                        System.out.println("Comando não reconhecido. Digite 'ajuda' para ver a lista."); // Orienta o usuário
                    } // Fim do if/else de comando default
                } // Fim do default
            } // Fim do switch
        } // Fim do while(true)
    } // Fim do main

    // ===================== LÓGICA DE ATENDIMENTO =====================

    private static int processarProximoAtendimento(Deque<Senha> filaPrefParametro, Deque<Senha> filaNormalParametro, List<Senha> historicoParametro, int preferenciaisSeguidasParametro) { // Decide quem atender seguindo 2P:1N
        if (filaPrefParametro.isEmpty() && filaNormalParametro.isEmpty()) { // Se ambas filas estão vazias
            System.out.println("Nenhuma senha aguardando. (Filas vazias)"); // Informa que não há senhas
            return preferenciaisSeguidasParametro; // Retorna contador inalterado
        } // Fim do if de filas vazias

        if (preferenciaisSeguidasParametro < RAZAO_PREFERENCIAL && !filaPrefParametro.isEmpty()) { // Se ainda não atendemos 2 preferenciais seguidas e há preferenciais na fila
            atender(filaPrefParametro, historicoParametro); // Atende uma preferencial
            return preferenciaisSeguidasParametro + 1; // Incrementa o contador de preferenciais seguidas
        } // Fim do if de prioridade preferencial

        if (!filaNormalParametro.isEmpty()) { // Se já atendemos 2 preferenciais ou não há preferencial, e existe normal
            atender(filaNormalParametro, historicoParametro); // Atende um normal
            return 0; // Zera o contador de preferenciais seguidas para reiniciar o ciclo 2P:1N
        } // Fim do if de atendimento normal

        // Se chegou aqui, não há normais, mas ainda há preferenciais (ou seja, atende preferencial)
        atender(filaPrefParametro, historicoParametro); // Atende preferencial restante
        int novoContador = preferenciaisSeguidasParametro < RAZAO_PREFERENCIAL ? preferenciaisSeguidasParametro + 1 : preferenciaisSeguidasParametro; // Ajusta contador
        return novoContador; // Retorna contador atualizado
    } // Fim do método processarProximoAtendimento

    private static void atender(Deque<Senha> filaParametro, List<Senha> historicoParametro) { // Remove da fila e registra atendimento
        Senha senhaRemovida = filaParametro.pollFirst(); // Retira o primeiro da fila
        if (senhaRemovida == null) { // Se a fila estava vazia
            System.out.println("Fila vazia!"); // Mensagem de fila vazia
            return; // Retorna sem fazer mais nada
        } // Fim do if de fila vazia

        senhaRemovida.atendimento = LocalDateTime.now(); // Marca o horário de atendimento como agora
        historicoParametro.add(senhaRemovida); // Registra a senha no histórico
        String etiquetaTipo = (senhaRemovida.tipo == Tipo.PREFERENCIAL ? "preferencial" : "normal"); // Constrói o rótulo textual do tipo
        System.out.println("Chamando: " + senhaRemovida.codigo + " (" + etiquetaTipo + ") às " + senhaRemovida.atendimento.format(FORMATO_HORA)); // Imprime a chamada
    } // Fim do método atender

    // ===================== SAÍDAS (HISTÓRICO, STATUS, RELATÓRIO) =====================

    private static void imprimirHistorico(List<Senha> historicoParametro) { // Percorre e imprime o histórico
        System.out.println("=== Histórico de Chamadas ==="); // Cabeçalho do histórico
        if (historicoParametro.isEmpty()) { // Se não há chamadas registradas
            System.out.println("Histórico vazio!"); // Informa vazio
            return; // Encerra o método
        } // Fim do if
        for (Senha senhaNoHistorico : historicoParametro) { // Para cada senha atendida
            System.out.println(senhaNoHistorico); // Usa toString da senha para imprimir dados formatados
        } // Fim do for
    } // Fim do método imprimirHistorico

    private static void imprimirStatus(Deque<Senha> filaPrefParametro, Deque<Senha> filaNormalParametro, int preferenciaisSeguidasParametro, int proximoNumeroPrefParametro, int proximoNumeroNormalParametro) { // Estado atual
        System.out.println("=== Status das Filas ==="); // Cabeçalho de status
        Senha proximaPref = filaPrefParametro.peekFirst(); // Olha o próximo preferencial (sem remover)
        Senha proximaNormal = filaNormalParametro.peekFirst(); // Olha o próximo normal (sem remover)
        System.out.println("Preferenciais em espera: " + filaPrefParametro.size() + (proximaPref == null ? "" : ", próximo: " + proximaPref.codigo)); // Mostra tamanho e próximo P
        System.out.println("Normais em espera: " + filaNormalParametro.size() + (proximaNormal == null ? "" : ", próximo: " + proximaNormal.codigo)); // Mostra tamanho e próximo N
        System.out.println("Ciclo atual 2P:1N — preferenciais atendidas nesta sequência: " + preferenciaisSeguidasParametro + "/" + RAZAO_PREFERENCIAL); // Mostra progresso do ciclo
        System.out.println("Próximos códigos a emitir — Preferencial: " + construirCodigo(Tipo.PREFERENCIAL, proximoNumeroPrefParametro) + ", Normal: " + construirCodigo(Tipo.NORMAL, proximoNumeroNormalParametro)); // Mostra próximos códigos
    } // Fim do método imprimirStatus

    private static void imprimirRelatorio(List<Senha> historicoParametro) { // Estatísticas gerais
        System.out.println("=== Relatório ==="); // Cabeçalho
        int totalAtendimentos = historicoParametro.size(); // Total de chamados
        long totalPreferenciais = historicoParametro.stream().filter(s -> s.tipo == Tipo.PREFERENCIAL).count(); // Conta preferenciais
        long totalNormais = historicoParametro.stream().filter(s -> s.tipo == Tipo.NORMAL).count(); // Conta normais

        long somaEsperaSegundos = 0; // Acumulador de espera total em segundos
        for (Senha s : historicoParametro) { // Percorre o histórico
            if (s.atendimento != null) { // Garante que tem atendimento registrado
                somaEsperaSegundos += Duration.between(s.chegada, s.atendimento).getSeconds(); // Soma tempo de espera
            } // Fim do if
        } // Fim do for
        double mediaEspera = totalAtendimentos == 0 ? 0.0 : (double) somaEsperaSegundos / totalAtendimentos; // Calcula média (evita divisão por zero)

        System.out.println("Total atendimentos: " + totalAtendimentos + " (Preferenciais: " + totalPreferenciais + ", Normais: " + totalNormais + ")"); // Linha com totais
        System.out.printf(Locale.ROOT, "Tempo médio de espera: %.1fs\n", mediaEspera); // Exibe média com 1 casa decimal
    } // Fim do método imprimirRelatorio

    // ===================== UTILITÁRIOS =====================

    private static String construirCodigo(Tipo tipoParametro, int numeroSequencialParametro) { // Monta códigos Pxxx/Nxxx
        String prefixo = (tipoParametro == Tipo.PREFERENCIAL ? "P" : "N"); // Define prefixo com base no tipo
        return prefixo + String.format(Locale.ROOT, "%03d", numeroSequencialParametro); // Formata com 3 dígitos e concatena
    } // Fim do método construirCodigo

    private static int[] executarDemo(Deque<Senha> filaPrefParametro, Deque<Senha> filaNormalParametro, List<Senha> historicoParametro, int sequenciaPrefParametro, int sequenciaNormalParametro) { // Simula um cenário
        System.out.println("=== DEMO: simulando chegadas e atendimentos ==="); // Cabeçalho da demo
        // Adiciona algumas senhas na ordem P, N, P, N, N, P para criar uma dinâmica interessante
        filaPrefParametro.addLast(new Senha(Tipo.PREFERENCIAL, construirCodigo(Tipo.PREFERENCIAL, sequenciaPrefParametro++), LocalDateTime.now())); // Enfileira P
        filaNormalParametro.addLast(new Senha(Tipo.NORMAL, construirCodigo(Tipo.NORMAL, sequenciaNormalParametro++), LocalDateTime.now())); // Enfileira N
        filaPrefParametro.addLast(new Senha(Tipo.PREFERENCIAL, construirCodigo(Tipo.PREFERENCIAL, sequenciaPrefParametro++), LocalDateTime.now())); // Enfileira P
        filaNormalParametro.addLast(new Senha(Tipo.NORMAL, construirCodigo(Tipo.NORMAL, sequenciaNormalParametro++), LocalDateTime.now())); // Enfileira N
        filaNormalParametro.addLast(new Senha(Tipo.NORMAL, construirCodigo(Tipo.NORMAL, sequenciaNormalParametro++), LocalDateTime.now())); // Enfileira N
        filaPrefParametro.addLast(new Senha(Tipo.PREFERENCIAL, construirCodigo(Tipo.PREFERENCIAL, sequenciaPrefParametro++), LocalDateTime.now())); // Enfileira P

        int contadorPreferenciaisSeguidasDemo = 0; // Zera contador para a demo
        // Realiza 8 atendimentos (ou até as filas esvaziarem), observando a regra 2P:1N
        for (int iteracaoAtendimento = 0; iteracaoAtendimento < 8; iteracaoAtendimento++) { // Loop de 8 chamadas
            if (filaPrefParametro.isEmpty() && filaNormalParametro.isEmpty()) { // Se não há mais quem atender
                break; // Sai do loop
            } // Fim do if
            contadorPreferenciaisSeguidasDemo = processarProximoAtendimento(filaPrefParametro, filaNormalParametro, historicoParametro, contadorPreferenciaisSeguidasDemo); // Processa uma chamada
            try { Thread.sleep(250); } catch (InterruptedException ignored) { } // Pequena pausa para variar o horário (apenas visual)
        } // Fim do for
        return new int[]{sequenciaPrefParametro, sequenciaNormalParametro}; // Retorna as novas sequências para o main
    } // Fim do método executarDemo
} // Fim da classe GerenciadorFilaAtendimento