import java.util.*; // Importa tipos fundamentais das coleções (List, Set, Map, Queue, etc.).

/*
 * Visão geral das principais coleções do Java:
 *
 * - Collection: contrato base para grupos de elementos.
 * - List: sequência ordenada com acesso por índice.
 * - Set: conjunto sem elementos duplicados (não garante ordem, salvo implementações específicas).
 * - Queue: fila com política de acesso FIFO (primeiro a entrar, primeiro a sair).
 * - Map: mapeamento chave → valor (não é uma Collection).
 * - Array: estrutura de tamanho fixo.
 * - Streams: pipeline declarativo para filtrar, transformar e agregar dados.
 *
 * Abaixo, um exemplo executável com comentários técnicos em cada etapa.
 */
public class ExemploCollections {
    public static void main(String[] args) {

        // --------------------------- COLLECTION ---------------------------
        // Collection é a interface raiz para coleções de elementos (adicionar, verificar e contar).
        // Aqui usamos uma ArrayList como implementação concreta.
        Collection<String> colecao = new ArrayList<>();
        colecao.add("Java");   // adiciona elemento
        colecao.add("Python");
        colecao.add("C");

        System.out.println("Collection contém 'Java'? " + colecao.contains("Java")); // pesquisa por ocorrência
        System.out.println("Tamanho da Collection: " + colecao.size());              // cardinalidade

        // --------------------------- LIST ---------------------------
        // List mantém ordem de inserção e permite acesso por índice (base 0).
        List<String> lista = new ArrayList<>();
        lista.add("Java");    // índice 0
        lista.add("Python");  // índice 1
        lista.add("C");       // índice 2

        System.out.println("\nList.get(1): " + lista.get(1)); // acesso posicional
        lista.remove(2);                                      // remoção por índice
        System.out.println("List após remover índice 2: " + lista); // impressão preserva ordem

        // --------------------------- SET ---------------------------
        // Set é um conjunto matemático: não admite duplicatas.
        // HashSet não garante ordem na iteração.
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("C");
        set.add("Python"); // duplicata ignorada

        System.out.println("\nSet (sem duplicatas; ordem não garantida): " + set);

        // --------------------------- QUEUE ---------------------------
        // Queue modela uma fila FIFO. LinkedList é uma implementação comum.
        // offer: insere no final da fila; poll: remove e retorna a cabeça; peek: inspeciona a cabeça sem remover.
        Queue<String> queue = new LinkedList<>();
        queue.offer("Primeiro");
        queue.offer("Segundo");
        queue.offer("Terceiro");

        System.out.println("\nQueue.poll(): " + queue.poll()); // remove e retorna o primeiro
        System.out.println("Queue.peek(): " + queue.peek());   // consulta o próximo sem remover
        System.out.println("Queue restante: " + queue);

        // --------------------------- MAP ---------------------------
        // Map armazena pares chave-valor. HashMap não garante ordem.
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1); // associação chave "A" → valor 1
        map.put("B", 2);
        map.put("C", 3);

        System.out.println("\nMap.get(\"A\"): " + map.get("A")); // acesso por chave
        System.out.println("Map.keySet(): " + map.keySet());     // conjunto de chaves
        System.out.println("Map completo: " + map);              // pares chave→valor

        // --------------------------- ARRAY ---------------------------
        // Array possui tamanho fixo e acesso posicional rápido.
        String[] nomes = {"Amaral", "Barcos", "Cristiano"};
        System.out.println("\nArray (for-each):");
        for (String nome : nomes) {
            System.out.println("- " + nome);
        }

        // --------------------------- STREAMS ---------------------------
        // Streams permitem processar coleções de forma declarativa com operações como filter, map e reduce.
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        // Soma dos números pares:
        // filter: mantém apenas pares; reduce: soma acumulada iniciando em 0.
        int somaDosPares = numeros.stream()
                                  .filter(n -> n % 2 == 0)
                                  .reduce(0, Integer::sum);

        System.out.println("\nSoma dos pares (2+4+6+8+10): " + somaDosPares);

        // Observações:
        // - Use List quando a ordem e o acesso por índice forem relevantes.
        // - Use Set para eliminar duplicidades.
        // - Use Queue para modelar filas e prioridades (PriorityQueue para ordenação por prioridade).
        // - Use Map quando precisar de pesquisa eficiente por chave.
        // - Use Streams para compor operações de consulta/transformação de forma concisa e legível.
    }
}