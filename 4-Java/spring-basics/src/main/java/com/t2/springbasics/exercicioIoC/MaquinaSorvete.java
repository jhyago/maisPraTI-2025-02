package com.t2.springbasics.exercicioIoC;

interface Sabor {
    String nome();
}

class SaborChocolate implements Sabor {
    public String nome() { return "Chocolate"; }
}

class SaborBaunilha implements Sabor {
    public String nome() { return "Baunilha"; }
}

public class MaquinaSorvete {
    private final Sabor sabor;

    public MaquinaSorvete(Sabor sabor) {
        this.sabor = sabor;
    }

    public String servir() { return "Servindo sorvete de: " + sabor.nome(); }
}
