import java.util.*;

public class ExemploCollections {
    public static void main(String[] args) {
        Collection<String> colecao = new ArrayList<>();
        colecao.add("Java");
        colecao.add("Python");
        colecao.add("C");

        System.out.println(colecao.contains("Java"));
        System.out.println(colecao.size());

        List<String> lista = new ArrayList<>();
        lista.add("Java");
        lista.add("Python");
        lista.add("C");
        System.out.println(lista.get(1));
        lista.remove(2);
        System.out.println(lista);

        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("C");
        set.add("Python");

        System.out.println(set);

        Queue<String> queue = new LinkedList<>();
        queue.offer("Primeiro");
        queue.offer("Segundo");
        queue.offer("Terceiro");

        System.out.println(queue.poll());
        System.out.println(queue.peek());

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        System.out.println(map.get("A"));
        System.out.println(map.keySet());
        System.out.println(map);

        String[] nomes = {"Amaral", "Barcos", "Cristiano"};
        for (String nome : nomes) {
            System.out.println(nome);
        }

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int somaDosPares = numeros.stream().filter(num -> num % 2 == 0).reduce(0, Integer::sum);

        System.out.println(somaDosPares);

//  Crie uma classe chamada Funcionario com os seguintes atributos: nome (String), salario (double), cargo (String) e idade (int). Implemente o construtor, os métodos getters e setters necessários.

//  Em seguida, crie uma lista de 10 funcionários, com valores variados para os atributos, e realize as seguintes operações utilizando Collections e Streams:

//  Filtre e exiba os nomes e cargos dos funcionários com mais de 30 anos.

//  Ordene os funcionários por salário em ordem crescente e exiba os nomes e salários.

//  Calcule e exiba a média salarial dos funcionários.

//  Exiba o total de funcionários com salário superior a R$ 4.000,00.

//  Crie uma lista com os nomes dos funcionários e a exiba.

//  Utilize os recursos de Streams para realizar as operações de forma concisa e eficiente.
    }
}


//Crie um sistema para gerenciar um conjunto de tarefas de um projeto, utilizando grafos, onde cada tarefa pode depender de outras para ser executada. Utilize Collections, Streams e tratamento de exceções com try-catch para validar e processar os dados.

//Você deve criar uma classe Tarefa que representa uma tarefa do projeto, com os atributos nome, duração (em dias) e uma lista de outras tarefas das quais depende. Crie também uma classe GrafoDeTarefas para gerenciar as tarefas, permitindo adicionar novas tarefas, remover tarefas existentes e calcular a duração total de uma tarefa, levando em conta as suas dependências.

//Ao trabalhar com as dependências, o sistema deve ser capaz de identificar e evitar dependências circulares, ou seja, quando uma tarefa depende de outra que, por sua vez, depende da primeira, criando um ciclo infinito. Caso isso aconteça, o sistema deve lançar uma exceção personalizada, DependenciaCircularException. Caso uma tarefa que não existe seja referenciada, uma outra exceção personalizada, TarefaNaoEncontradaException, deve ser lançada.

//Além disso, o sistema deve permitir o uso de Streams para filtrar e exibir tarefas que possuem dependências, calcular a duração total de uma tarefa (somando a duração da tarefa e de todas as suas dependências) e listar todas as tarefas cuja duração seja superior a um valor específico.

//Implemente o tratamento de exceções para garantir que, ao tentar calcular a duração total de uma tarefa ou adicionar uma tarefa com dependências inválidas, erros sejam capturados e mensagens apropriadas sejam exibidas.
