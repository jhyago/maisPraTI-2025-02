package ExercicioGrafos;

import java.util.*;

public class GrafoDeTarefas {
    private Map<String, Tarefa> tarefas;

    public GrafoDeTarefas() {
        this.tarefas = new HashMap<>();
    }

    public void adicionarTarefa(Tarefa tarefa) throws TarefaNaoEncontradaException {
        if(tarefas.containsKey(tarefa.getNome())) {
            throw new TarefaNaoEncontradaException("A tarefa " + tarefa.getNome() + " já existe.");
        }

        tarefas.put(tarefa.getNome(), tarefa);
    }

    public int calcularDuracaoTotal(String nomeTarefa) throws TarefaNaoEncontradaException, DependenciaCircularException {
        Tarefa tarefa = tarefas.get(nomeTarefa);
        if(tarefa == null) {
            throw new TarefaNaoEncontradaException("Tarefa " + nomeTarefa + " não encontrada");
        }

        Set<Tarefa> tarefasVisitadas = new HashSet<>();
        return calcularDuracaoTotalRecursivo(tarefa, tarefasVisitadas);
    }

    private int calcularDuracaoTotalRecursivo(Tarefa tarefa, Set<Tarefa> tarefasVisitadas) throws DependenciaCircularException {
        if(tarefasVisitadas.contains(tarefa)) {
            throw new DependenciaCircularException("Dependência circular detectada envolvendo a tarefa " + tarefa.getNome());
        }

        tarefasVisitadas.add(tarefa);
        int duracaoTotal = tarefa.getDuracao();

        for(Tarefa dependente : tarefa.getDependencias()) {
            duracaoTotal += calcularDuracaoTotalRecursivo(dependente, tarefasVisitadas);
        }

        tarefasVisitadas.remove(tarefa);
        return duracaoTotal;
    }

    public List<Tarefa> listarTarefasComDuracaoSuperiorA(int duracaoMinima) {
        return tarefas.values().stream().filter(tarefa -> tarefa.getDuracao() > duracaoMinima).toList();
    }
}
