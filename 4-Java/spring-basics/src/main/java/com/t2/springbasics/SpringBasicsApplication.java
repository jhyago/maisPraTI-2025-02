package com.t2.springbasics;

import com.t2.springbasics.exercicioIoC.MaquinaDeSorveteIoC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicsApplication.class, args);
    }
//    Construir uma mini-aplicação que imprime o sabor do sorvete servido por uma máquina.
//    Você fará Parte A (sem IoC) e Parte B (com IoC/Spring), trocando a implementação no meio para ver o impacto.

    @Bean
    CommandLineRunner init(MaquinaDeSorveteIoC maquina) {
        return args -> {
            System.out.println("" + maquina.servir());
        };
    }
}
