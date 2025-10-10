import java.util.*;

/*
 * GRAFOS — EXEMPLO (com BFS para caminho mínimo em arestas sem peso)
 * ----------------------------------------------------------------------------
 * Ideias-chave que o código ilustra:
 * 1) Um grafo é um conjunto de VÉRTICES (nós) e ARESTAS (ligações entre nós).
 * 2) Pode ser DIRECIONADO (arestas têm sentido) ou NÃO DIRECIONADO (mão dupla).
 * 3) Usamos LISTA DE ADJACÊNCIA: para cada vértice, guardamos o conjunto de vizinhos.
 * 4) BFS (Breadth-First Search) encontra o CAMINHO MÍNIMO em número de arestas
 *    quando todas as arestas têm o mesmo “custo” (isto é, são não ponderadas).
 *
 * O que o código faz:
 * - addVertex(v): garante que o vértice exista no mapa de adjacência.
 * - addEdge(u, v): adiciona aresta u -> v; se o grafo for não direcionado, também v -> u.
 * - getNeighbors(v): devolve os vizinhos de v (conjunto de vértices alcançáveis em 1 aresta).
 * - bfsShortestPath(start, goal): devolve o caminho mínimo (lista de vértices) de start a goal.
 *
 * Dicas conceituais:
 * - “visitados” evita revisitar nós (laços infinitos) e garante O(V + E) de complexidade na BFS.
 * - “fila” (queue) na BFS expande nós em camadas (distância 0, 1, 2…), garantindo o menor nº de arestas.
 * - “prev” (predecessor) guarda por onde chegamos a cada nó; após a busca, reconstituímos o caminho.
 */
public class ExemploGrafo<T> {
    // Se true, arestas têm direção (u -> v); se false, arestas são de mão dupla (u <-> v).
    private final boolean directed;

    // Lista de adjacência: para cada vértice T, guardamos um Set<T> de vizinhos.
    // LinkedHashSet preserva ordem de inserção (útil para resultados previsíveis em exemplos).
    private final Map<T, Set<T>> adj = new LinkedHashMap<>();

    // Construtor: define se o grafo é direcionado ou não.
    public ExemploGrafo(boolean directed) {
        this.directed = directed;
    }

    // Garante que o vértice exista na estrutura (se já houver, não faz nada).
    public void addVertex(T v) {
        // putIfAbsent não sobrescreve se já existir; inicializa com conjunto vazio de vizinhos.
        adj.putIfAbsent(Objects.requireNonNull(v, "vértice não pode ser nulo"), new LinkedHashSet<>());
    }

    // Adiciona uma aresta u -> v; se não direcionado, também v -> u.
    public void addEdge(T u, T v) {
        Objects.requireNonNull(u, "vértice de origem não pode ser nulo");
        Objects.requireNonNull(v, "vértice de destino não pode ser nulo");
        addVertex(u);
        addVertex(v);
        adj.get(u).add(v);
        if (!directed) adj.get(v).add(u); // no grafo não direcionado, adicionamos a “volta”
    }

    // Vizinhos de v: quem está a uma aresta de distância. Retorna visão imutável (evita alterações externas).
    public Set<T> getNeighbors(T v) {
        return Collections.unmodifiableSet(adj.getOrDefault(v, Set.of()));
    }

    // BFS para caminho MÍNIMO (em nº de arestas) de start até goal.
    // Se não houver caminho, retorna lista vazia.
    public List<T> bfsShortestPath(T start, T goal) {
        Objects.requireNonNull(start, "start não pode ser nulo");
        Objects.requireNonNull(goal, "goal não pode ser nulo");

        // Casos simples: vértices ausentes ou start == goal
        if (!adj.containsKey(start) || !adj.containsKey(goal)) return List.of();
        if (start.equals(goal)) return List.of(start);

        // “visited” evita revisitar; “queue” processa em camadas; “prev” guarda por onde chegamos.
        Set<T> visited = new HashSet<>();
        Deque<T> queue = new ArrayDeque<>();
        Map<T, T> prev = new HashMap<>(); // prev[x] = nó anterior no caminho até x

        visited.add(start);
        queue.addLast(start);

        while (!queue.isEmpty()) {
            T current = queue.removeFirst(); // retira da frente (FIFO)

            // Se encontramos o objetivo, podemos parar a expansão.
            if (current.equals(goal)) break;

            // Percorre vizinhos (camada seguinte).
            for (T nb : getNeighbors(current)) {
                // visited.add(nb) retorna true se nb ainda não estava visitado; já marca visitado.
                if (visited.add(nb)) {
                    prev.put(nb, current);   // registramos que nb foi alcançado via current
                    queue.addLast(nb);       // enfileira para expandir sua vizinhança depois
                }
            }
        }

        // Se nunca setamos prev para o goal, não há caminho (exceto quando start==goal, já tratado).
        if (!prev.containsKey(goal)) return List.of();

        // Reconstrói o caminho de trás pra frente: goal -> ... -> start
        List<T> path = new ArrayList<>();
        for (T at = goal; at != null; at = prev.get(at)) {
            path.add(at);
            if (at.equals(start)) break;
        }

        // Inverte para ficar start -> ... -> goal
        Collections.reverse(path);
        return path;
    }

    // Representação simples: mapa de adjacência.
    @Override
    public String toString() { return adj.toString(); }

    // Pequena demonstração
    public static void main(String[] args) {
        // false = não direcionado (as arestas valem nos dois sentidos)
        ExemploGrafo<String> g = new ExemploGrafo<>(false);

        // Arestas do grafo (automaticamente criam os vértices citados)
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "D");
        g.addEdge("C", "E");
        g.addEdge("D", "F");
        g.addEdge("E", "F");

        System.out.println("Grafo (lista de adjacência): " + g);

        // Caminho mínimo (em número de arestas) de A até F usando BFS
        System.out.println("Caminho mínimo A -> F (BFS): " + g.bfsShortestPath("A", "F"));

        // Exemplos adicionais (opcionais):
        // - Vizinhos de C:
        System.out.println("Vizinhos de C: " + g.getNeighbors("C"));
        // - Caminho mínimo A -> E:
        System.out.println("Caminho mínimo A -> E (BFS): " + g.bfsShortestPath("A", "E"));
        // - Caminho inexistente (vértice não presente):
        System.out.println("Caminho mínimo A -> X (inexistente): " + g.bfsShortestPath("A", "X"));
    }
}