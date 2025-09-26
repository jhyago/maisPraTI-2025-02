package com.t1.springbasics.iocbasics;     // Mesmo pacote dos demais exemplos (Carrinho, Motor).

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Implementação concreta de {@link Motor} para ilustrar IoC/DI no Spring.
 *
 * Conceitos envolvidos:
 * - IoC (Inversão de Controle): quem cria/gerencia esta classe é o CONTÊINER do Spring,
 *   não o código de negócio (ex.: o Carrinho não faz "new MotorAGasolina()").
 * - DI (Injeção de Dependência): um bean de {@code Motor} é injetado no {@code Carrinho} via construtor.
 *
 * Anotações:
 * - @Service: registra esta classe como um BEAN gerenciado pelo Spring (poderia ser @Component também).
 * - @Primary: se houver várias implementações de {@code Motor} no contexto,
 *   esta será a ESCOLHA PADRÃO para injeção (a menos que se use @Qualifier).
 *
 * Observações:
 * - Para alternar a implementação injetada sem alterar o Carrinho, basta trocar @Primary
 *   ou usar @Qualifier no construtor do Carrinho.
 */
@Primary   // Marca esta implementação como a padrão para injeção quando há mais de um Motor disponível.
@Service   // Torna a classe um bean do Spring (descoberto por component scanning).
public class MotorAGasolina implements Motor {

    /**
     * Liga o motor a gasolina e retorna uma mensagem simples.
     * @return mensagem indicando que o motor foi ligado.
     */
    @Override
    public String ligar() {
        return "Motor a gasolina ligado!";
    }
}