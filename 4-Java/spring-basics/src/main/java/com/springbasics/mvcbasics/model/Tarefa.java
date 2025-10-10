package com.springbasics.mvcbasics.model;

public class Tarefa {
    private Long id;

    private String titulo;

    private boolean concluida;


    public Tarefa(Long id, String titulo, boolean concluida) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setConcluida(concluida);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
