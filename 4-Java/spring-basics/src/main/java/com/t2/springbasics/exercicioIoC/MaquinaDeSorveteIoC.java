package com.t2.springbasics.exercicioIoC;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary
@Component
class SaborLimao implements Sabor { public String nome() { return "Limão"; } }
@Component

class SaborMorango2 implements Sabor { public String nome() { return "Morango"; } }

@Component
public class MaquinaDeSorveteIoC {
    private final Sabor sabor;

    public MaquinaDeSorveteIoC(Sabor sabor) {
        this.sabor = sabor;
    }
    public String servir() {
        return "Servindo sorvete de: " +  sabor.nome();
    }
}
