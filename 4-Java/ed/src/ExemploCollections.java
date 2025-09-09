import java.util.*;

public class ExemploCollections {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista.add("Java");
        lista.add("Python");
        lista.add("C");
        lista.add("PHP");
        lista.add("Java");
        System.out.println("Lista: " + lista);

        Set<String> conjunto = new HashSet<>();
        conjunto.add("Java");
        conjunto.add("Python");
        conjunto.add("C");
        conjunto.add("PHP");
        conjunto.add("Java");
        System.out.println("Conjunto: " + conjunto);

        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("Java", 1);
        mapa.put("Python", 2);
        mapa.put("C", 3);
        mapa.put("Java", 4);
        System.out.println("Mapa: " + mapa);

        Queue<String> queue = new LinkedList<>();
        queue.add("Primeiro");
        queue.add("Segundo");
        queue.add("Terceiro");
        System.out.println("Queue: " + queue);

        Collection<String> colecao = new ArrayList<>();
        colecao.add("A");
        colecao.add("B");
        colecao.add("C");

        System.out.println(colecao.contains("A"));
        System.out.println(colecao.size());

        List<String> listaExemplo = new ArrayList<>();
        listaExemplo.add("Java");
        listaExemplo.add("Python");
        listaExemplo.add("C");
        System.out.println(listaExemplo.get(0));
        listaExemplo.remove(1);
        System.out.println(listaExemplo);

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int somaQuadradosPares = numeros.stream().filter(n -> n % 2 == 0).map(n -> n * n).reduce(0, Integer::sum);
        System.out.println(somaQuadradosPares);

        String[] nomes = {"Jucilei", "Gertrudes", "João"};
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }
}

//Crie uma classe chamada Funcionario com os seguintes atributos: nome (String), salario (double), cargo (String) e idade (int). Implemente o construtor, os métodos getters e setters necessários.

// Em seguida, crie uma lista de 10 funcionários, com valores variados para os atributos, e realize as seguintes operações utilizando Collections e Streams:

//Filtre e exiba os nomes e cargos dos funcionários com mais de 30 anos.

//Ordene os funcionários por salário em ordem crescente e exiba os nomes e salários.

//Calcule e exiba a média salarial dos funcionários.

//Exiba o total de funcionários com salário superior a R$ 4.000,00.

//Crie uma lista com os nomes dos funcionários e a exiba.

//Utilize os recursos de Streams para realizar as operações de forma concisa e eficiente.
