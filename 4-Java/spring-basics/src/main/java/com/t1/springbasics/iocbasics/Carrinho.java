package com.t1.springbasics.iocbasics;        // Pacote de “conceitos básicos” para ilustrar IoC/DI no Spring.

import org.springframework.stereotype.Service;

/**
 * Carrinho
 *
 * Papel didático: mostrar Inversão de Controle (IoC) + Injeção de Dependência (DI).
 *
 * - IoC: quem cria e gerencia dependências não é a classe (Carrinho), mas o CONTÊINER do Spring.
 * - DI via CONSTRUTOR: o Spring injeta um bean de Motor no construtor de Carrinho.
 *
 * Observações:
 * - @Service registra a classe como bean gerenciado no contexto do Spring (component scanning).
 * - O atributo 'motor' é final: imutável após a injeção → facilita testes e raciocínio.
 * - Não há @Autowired no construtor: com um único construtor público, o Spring injeta automaticamente.
 */
@Service
public class Carrinho {

    private final Motor motor;                           // Dependência: uma abstração/bean do tipo Motor.

    /**
     * Construtor com DI (Dependency Injection).
     * O Spring resolverá um bean compatível com 'Motor' e o fornecerá aqui.
     */
    public Carrinho(Motor motor) {
        this.motor = motor;
    }

    /**
     * Exemplo de uso da dependência injetada.
     * Carrinho não "sabe" como o motor é criado/configurado — apenas o utiliza.
     */
    public String andar() {
        return motor.ligar() + " e o carrinho está andando";
    }
}