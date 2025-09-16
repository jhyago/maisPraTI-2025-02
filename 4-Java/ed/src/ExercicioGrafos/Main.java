package ExercicioGrafos;

import java.util.ArrayList;
import java.util.List;

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
