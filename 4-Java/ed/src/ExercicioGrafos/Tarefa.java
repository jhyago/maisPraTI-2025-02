package ExercicioGrafos;

import java.util.List;

public class Tarefa {
    private String nome;
    private int duracao;
    private List<Tarefa> dependencias;

    public Tarefa(String nome, int duracao, List<Tarefa> dependencias) {
        this.nome = nome;
        this.duracao = duracao;
        this.dependencias = dependencias;
    }

    public String getNome() {
        return this.nome;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public List<Tarefa> getDependencias() {
        return this.dependencias;
    }
}
