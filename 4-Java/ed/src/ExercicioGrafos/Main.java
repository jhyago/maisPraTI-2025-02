package ExercicioGrafos;

import java.util.ArrayList;
import java.util.List;

//Crie um sistema para gerenciar um conjunto de tarefas de um projeto, utilizando grafos, onde cada tarefa pode depender de outras para ser executada. Utilize Collections, Streams e tratamento de exceções com try-catch para validar e processar os dados.

//Você deve criar uma classe Tarefa que representa uma tarefa do projeto, com os atributos nome, duração (em dias) e uma lista de outras tarefas das quais depende. Crie também uma classe GrafoDeTarefas para gerenciar as tarefas, permitindo adicionar novas tarefas, remover tarefas existentes e calcular a duração total de uma tarefa, levando em conta as suas dependências.

//Ao trabalhar com as dependências, o sistema deve ser capaz de identificar e evitar dependências circulares, ou seja, quando uma tarefa depende de outra que, por sua vez, depende da primeira, criando um ciclo infinito. Caso isso aconteça, o sistema deve lançar uma exceção personalizada, DependenciaCircularException. Caso uma tarefa que não existe seja referenciada, uma outra exceção personalizada, TarefaNaoEncontradaException, deve ser lançada.

//Além disso, o sistema deve permitir o uso de Streams para filtrar e exibir tarefas que possuem dependências, calcular a duração total de uma tarefa (somando a duração da tarefa e de todas as suas dependências) e listar todas as tarefas cuja duração seja superior a um valor específico.

//Implemente o tratamento de exceções para garantir que, ao tentar calcular a duração total de uma tarefa ou adicionar uma tarefa com dependências inválidas, erros sejam capturados e mensagens apropriadas sejam exibidas.
public class Main {
    public static void main(String[] args) throws TarefaNaoEncontradaException, DependenciaCircularException {
        Tarefa tarefa1 = new Tarefa("Enxugar o Gelo da geladeira", 4, new ArrayList<>());
        Tarefa tarefa2 = new Tarefa("Encher a praia de terra", 2, List.of(tarefa1));
        Tarefa tarefa3 = new Tarefa("Dar água pro peixe", 1, List.of(tarefa2));

        GrafoDeTarefas grafo1 = new GrafoDeTarefas();
        grafo1.adicionarTarefa(tarefa1);
        grafo1.adicionarTarefa(tarefa2);
        grafo1.adicionarTarefa(tarefa3);

        int duracaoTotal = grafo1.calcularDuracaoTotal("Dar água pro peixe");
        System.out.println("Quanto tempo leva dar água para o peixe? " + duracaoTotal);

        List<Tarefa> tarefasLongas = grafo1.listarTarefasComDuracaoSuperiorA(4);
        System.out.println("Tarefas com duração superior a 4 dias: ");
        tarefasLongas.forEach(tarefa -> System.out.println(tarefa.getNome()));
    }
}
