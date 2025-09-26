package com.t1.springbasics.iocbasics;   // Mesmo pacote dos exemplos de IoC/DI (Carrinho, Motor).

import org.springframework.stereotype.Service;

/**
 * Implementação concreta de {@link Motor} para demonstrar IoC/DI no Spring.
 *
 * Conceitos didáticos:
 * - IoC (Inversão de Controle): quem cria/gerencia este objeto é o container do Spring,
 *   não o código de negócio. O Carrinho não faz "new MotorEletrico()".
 * - DI (Injeção de Dependência): o Spring injeta um bean de tipo Motor (como este) no Carrinho via construtor.
 *
 * Anotação:
 * - @Service: registra a classe como BEAN gerenciado descoberto por component scanning.
 *   (Poderia ser @Component — aqui usamos @Service por semântica de “serviço” do domínio.)
 *
 * Observação importante:
 * - Se você tiver MAIS DE UMA implementação de Motor no contexto (ex.: MotorAGasolina com @Primary e esta aqui),
 *   o Spring injetará a que estiver marcada com @Primary por padrão.
 *   Para forçar especificamente este motor elétrico, use @Qualifier no Carrinho (ver exemplo abaixo).
 */
@Service
public class MotorEletrico implements Motor {

    /** Liga o motor elétrico e retorna um status legível. */
    @Override
    public String ligar() {
        return "Motor elétrico ligado!";
    }
}

/*
 * Como escolher explicitamente o Motor elétrico no Carrinho (quando há várias implementações):
 *
 * import org.springframework.beans.factory.annotation.Qualifier;
 *
 * @Service
 * public class Carrinho {
 *     private final Motor motor;
 *
 *     // Se existir um @Primary em outra implementação (ex.: MotorAGasolina),
 *     // use @Qualifier para pedir o "motorEletrico" (o nome padrão do bean é o nome da classe em camelCase).
 *     public Carrinho(@Qualifier("motorEletrico") Motor motor) {
 *         this.motor = motor;
 *     }
 *
 *     public String andar() { return motor.ligar() + " e o carrinho está andando."; }
 * }
 */