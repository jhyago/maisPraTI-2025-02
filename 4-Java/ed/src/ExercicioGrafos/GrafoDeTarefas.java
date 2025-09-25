package ExercicioGrafos;                                        // Pacote do exercício (organiza as classes).

import java.util.*;                                            // Importações de Collections/Map/Set/List/etc.

/**
 * Grafo dirigido de tarefas com dependências.
 * Fornece:
 *  - Adição de tarefas com validação de duplicidade;
 *  - Cálculo da duração total acumulada de uma tarefa, com detecção de ciclos;
 *  - (Otimização) Memoização de durações para evitar recomputo;
 *  - Listagem de tarefas por critério de duração.
 */
public class GrafoDeTarefas {                                  // Classe principal do grafo.
    private final Map<String, Tarefa> tarefas;                 // Mapa nome -> tarefa (imutável quanto à referência).

    /** Construtor: inicia o repositório interno. */
    public GrafoDeTarefas() {
        this.tarefas = new HashMap<>();                        // HashMap simples; troque por ConcurrentHashMap se precisar de concorrência.
    }

    /**
     * Adiciona uma tarefa ao grafo.
     * @throws NullPointerException se a tarefa ou seu nome forem nulos
     * @throws TarefaJaExisteException se já houver tarefa com mesmo nome
     */
    public void adicionarTarefa(Tarefa tarefa) throws TarefaJaExisteException {
        Objects.requireNonNull(tarefa, "tarefa não pode ser nula");     // Validação de referência.
        String nome = Objects.requireNonNull(tarefa.getNome(), "nome da tarefa não pode ser nulo"); // Nome não nulo.

        if (tarefas.containsKey(nome)) {                        // Já existe?
            throw new TarefaJaExisteException("A tarefa '" + nome + "' já existe."); // Exceção semanticamente correta.
        }
        tarefas.put(nome, tarefa);                              // Insere no mapa.
    }

    /**
     * Calcula a duração total de uma tarefa, somando suas dependências.
     * Detecta e sinaliza dependências circulares.
     *
     * Importante:
     * - Se uma dependência for compartilhada por duas cadeias diferentes, ela é somada **apenas uma vez** (memoização).
     *   Isso evita superestimar a duração em DAGs com compartilhamento.
     *
     * @param nomeTarefa nome da tarefa alvo
     * @return duração total em unidades inteiras (mesma unidade de Tarefa.getDuracao())
     * @throws TarefaNaoEncontradaException se não existir tarefa com esse nome
     * @throws DependenciaCircularException se houver ciclo no grafo a partir dessa tarefa
     */
    public int calcularDuracaoTotal(String nomeTarefa)
            throws TarefaNaoEncontradaException, DependenciaCircularException {

        Objects.requireNonNull(nomeTarefa, "nomeTarefa não pode ser nulo"); // Validação de parâmetro.

        Tarefa raiz = tarefas.get(nomeTarefa);                    // Busca a tarefa no mapa.
        if (raiz == null) {                                       // Não encontrada?
            throw new TarefaNaoEncontradaException("Tarefa '" + nomeTarefa + "' não encontrada.");
        }

        // Conjuntos para DFS com detecção de ciclo:
        Set<Tarefa> visitando = new HashSet<>();                  // Nós na pilha de recursão (cinza).
        Set<Tarefa> visitadas  = new HashSet<>();                 // Nós totalmente processados (preto).
        // Cache de durações acumuladas por tarefa para não recomputar (e para não duplicar compartilhadas):
        Map<Tarefa, Integer> memo = new IdentityHashMap<>();      // IdentityHashMap evita depender de equals/hashCode de Tarefa.

        return dfsDuracao(raiz, visitando, visitadas, memo);      // Chama DFS com memória.
    }

    /**
     * DFS com detecção de ciclo (visitando/visitadas) + memoização da duração acumulada.
     */
    private int dfsDuracao(Tarefa atual,
                           Set<Tarefa> visitando,
                           Set<Tarefa> visitadas,
                           Map<Tarefa, Integer> memo) throws DependenciaCircularException {

        if (memo.containsKey(atual)) {                            // Já calculado antes? Reaproveita.
            return memo.get(atual);
        }
        if (visitando.contains(atual)) {                          // Voltou para um nó em processamento -> ciclo.
            throw new DependenciaCircularException("Dependência circular detectada envolvendo a tarefa '" + atual.getNome() + "'.");
        }
        if (visitadas.contains(atual)) {                          // Já finalizado em outra rota -> (redundante com memo, mas barato).
            return memo.getOrDefault(atual, 0);
        }

        visitando.add(atual);                                     // Marca como em processamento.
        int total = atual.getDuracao();                           // Começa com a duração da própria tarefa.

        // Soma as dependências (cada uma uma vez, graças ao memo).
        for (Tarefa dep : atual.getDependencias()) {
            if (dep == null) {                                    // Defensiva: ignora nulos silenciosamente; pode-se lançar exceção se preferir.
                continue;
            }
            total += dfsDuracao(dep, visitando, visitadas, memo); // Acumula recursivamente.
        }

        visitando.remove(atual);                                  // Tira da pilha (fim do processamento).
        visitadas.add(atual);                                     // Marca como concluída.
        memo.put(atual, total);                                   // Guarda no cache para reaproveitar.

        return total;                                             // Retorna a duração acumulada a partir deste nó.
    }

    /**
     * Lista tarefas com duração estritamente maior que {@code duracaoMinima}.
     * @param duracaoMinima limite inferior estrito (tarefa precisa ter getDuracao() > duracaoMinima)
     * @return lista imutável com as tarefas que atendem ao critério
     */
    public List<Tarefa> listarTarefasComDuracaoSuperiorA(int duracaoMinima) {
        // Defensiva simples; se negativo, na prática retorna todas com getDuracao() > valor negativo.
        // if (duracaoMinima < 0) duracaoMinima = 0;

        // Stream + filtro. Em Java 16+, toList() já retorna lista imutável.
        List<Tarefa> resultado = tarefas.values()
                .stream()
                .filter(t -> t != null && t.getDuracao() > duracaoMinima)   // Filtra por duração.
                // .sorted(Comparator.comparingInt(Tarefa::getDuracao).reversed()) // (Opcional) Ordena por duração desc.
                .toList();

        return resultado;                                                   // Lista imutável (snapshot).
    }

    /* -----------------------------------------------------------
       Métodos utilitários adicionais (opcionais, mas úteis)
       ----------------------------------------------------------- */

    /** Verifica se existe tarefa com o nome informado. */
    public boolean existeTarefa(String nome) {
        return nome != null && tarefas.containsKey(nome);
    }

    /** Obtém uma tarefa por nome (Optional para evitar null). */
    public Optional<Tarefa> getTarefa(String nome) {
        return Optional.ofNullable(tarefas.get(nome));
    }

    /** Remove uma tarefa por nome (retorna true se removeu). */
    public boolean removerTarefa(String nome) {
        return tarefas.remove(nome) != null;
    }
}

/* ====================== Exceções auxiliares (não públicas) ====================== */

/** Exceção para tarefa duplicada. */
class TarefaJaExisteException extends RuntimeException {
    public TarefaJaExisteException(String message) { super(message); }
}

/** Exceção para tarefa não encontrada (mantida se outras APIs a usarem). */
class TarefaNaoEncontradaException extends Exception {
    public TarefaNaoEncontradaException(String message) { super(message); }
}

/** Exceção para dependência circular. */
class DependenciaCircularException extends Exception {
    public DependenciaCircularException(String message) { super(message); }
}