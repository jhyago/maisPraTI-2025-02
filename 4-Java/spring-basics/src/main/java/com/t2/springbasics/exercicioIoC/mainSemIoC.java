package com.t2.springbasics.exercicioIoC;

public class mainSemIoC {
    public static void main(String[] args) {
        Sabor sabor = new SaborChocolate();
        MaquinaSorvete maquina = new MaquinaSorvete(sabor);
        System.out.println(maquina.servir());
    }
}
