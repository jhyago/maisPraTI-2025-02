package com.t1.springbasics.iocbasics;

/**
 * Abstração de um motor para demonstrar IoC/DI no Spring.
 *
 * <p>Por que uma interface?
 * - Permite trocar a implementação (combustão, elétrico, mock de teste) sem alterar quem usa (ex.: {@link Carrinho}).
 * - Facilita testes (pode-se injetar um stub/fake/lambda).
 *
 * <p>Observação:
 * - A interface NÃO precisa de anotações Spring. Quem é registrado como bean são
 *   as classes que <b>implementam</b> esta interface (ex.: {@code @Component} em {@code MotorCombustao}).
 */
@FunctionalInterface // Garante exatamente um método abstrato e permite uso com lambdas em testes.
public interface Motor {

    /**
     * Liga o motor e retorna uma mensagem/status.
     * Implementações devem retornar um texto claro que identifique o tipo/estado do motor.
     */
    String ligar();
}