package com.t1.springbasics.iocbasics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {
    private final Carrinho carrinho;

    DemoRunner(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-> Carrinho: " + carrinho.andar());
    }
}
