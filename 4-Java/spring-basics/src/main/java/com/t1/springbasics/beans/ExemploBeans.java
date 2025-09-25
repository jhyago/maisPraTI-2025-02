package com.t1.springbasics.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

/* =================================================================================================
   IDEIA GERAL
   - Pense no Spring como uma "fábrica" (o CONTAINER) que:
       (1) cria as peças (objetos/beans),
       (2) entrega as dependências certas para cada peça (Injeção de Dependência - DI),
       (3) liga/desliga as peças em ordem (ciclo de vida: @PostConstruct / @PreDestroy),
       (4) controla quantas cópias de cada peça existem (ESCOPO: singleton, prototype, ...).

   O QUE VOCÊ VAI VER:
   1) @Configuration + @Bean → "método fábrica" que cria um bean simples (String prefixo).
   2) @Service Greeter → depende de "prefixo" e usa @PostConstruct / @PreDestroy.
   3) @Component MusicBox → mostra claramente o ciclo de vida com prints.
   4) Escopos: SingletonProbe (uma instância) e PrototypeProbe (nova instância a cada pedido).
   5) CommandLineRunner → ponto onde rodamos o "programa" após o contexto estar pronto.
   6) Encerramento do contexto → dispara @PreDestroy em singletons.

   COMO LER:
   - Siga os comentários "PASSO X" no código e observe as mensagens no console.
   ================================================================================================ */

@SpringBootApplication // PASSO 0: marca a classe principal e liga o "component scan"
public class ExemploBeans {

    public static void main(String[] args) {
        // PASSO 1: subir o contexto do Spring (ligar a fábrica)
        //   - Aqui o Spring: escaneia as classes, cria beans, injeta dependências e executa @PostConstruct.
        ConfigurableApplicationContext ctx = SpringApplication.run(ExemploBeans.class, args);

        // PASSO 6 (no final): ao encerrar o contexto, o Spring chama @PreDestroy nos beans singleton.
        //   (Veja o close() no fim do CommandLineRunner abaixo.)
        //   Se você fechasse aqui, perderia o CommandLineRunner. Então fechamos lá.
    }

    /* =============================================================================================
       PASSO 2 — CONFIGURAÇÃO EXPLÍCITA: @Configuration + @Bean
       - @Bean define um "método fábrica" que devolve um objeto a ser gerenciado pelo Spring.
       - Aqui, criamos um bean simples de String chamado "prefixo".
       ============================================================================================= */
    @Configuration
    static class AppConfig {

        @Bean
        public String prefixo() {
            System.out.println("[AppConfig] Criando bean 'prefixo'");
            return ">>> "; // qualquer lógica de criação/configuração caberia aqui
        }
    }

    /* =============================================================================================
       PASSO 3 — BEAN via @Service: Greeter
       - Depende do "prefixo" (String) → o Spring injeta no construtor.
       - @PostConstruct: roda depois que TUDO foi injetado (bean pronto).
       - @PreDestroy: roda quando o contexto é fechado (limpeza).
       ============================================================================================= */
    @Service
    static class Greeter {
        private final String prefixo;

        // DI por construtor: o Spring descobre que precisa fornecer o bean "prefixo"
        Greeter(String prefixo) {
            System.out.println("[Greeter] Construtor (ainda sem @PostConstruct)");
            this.prefixo = prefixo;
        }

        @PostConstruct
        void init() {
            System.out.println("[Greeter] @PostConstruct → prefixo recebido: '" + prefixo + "'");
        }

        public String say(String nome) {
            return prefixo + "Olá, " + nome + "!";
        }

        @PreDestroy
        void onClose() {
            System.out.println("[Greeter] @PreDestroy → limpando recursos (se houver)...");
        }
    }

    /* =============================================================================================
       PASSO 4 — BEAN via @Component: MusicBox
       - Também mostra @PostConstruct e @PreDestroy claramente.
       ============================================================================================= */
    @Component
    static class MusicBox {

        @PostConstruct
        void init() {
            System.out.println("[MusicBox] @PostConstruct → MusicBox pronta para tocar");
        }

        public void play(String track) {
            System.out.println("[MusicBox] Tocando: " + track);
        }

        @PreDestroy
        void close() {
            System.out.println("[MusicBox] @PreDestroy → desligando MusicBox");
        }
    }

    /* =============================================================================================
       PASSO 5 — ESCOPO DOS BEANS
       - Singleton (padrão): o mesmo objeto para todo o contexto.
       - Prototype: cada getBean(...) cria uma nova instância (o Spring NÃO chama @PreDestroy aqui).
       ============================================================================================= */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    static class SingletonProbe {
        private final String id = Integer.toHexString(System.identityHashCode(this));
        public String getId() { return id; }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    static class PrototypeProbe {
        private final String id = Integer.toHexString(System.identityHashCode(this));
        public String getId() { return id; }

        @PostConstruct
        void init() {
            System.out.println("[PrototypeProbe] @PostConstruct → criado id=" + id);
        }
        // @PreDestroy NÃO é chamado automaticamente para prototype
    }

    /* =============================================================================================
       PASSO 6 — PONTO DE EXECUÇÃO APÓS O CONTEXTO PRONTO: CommandLineRunner
       - Aqui todas as injeções e @PostConstruct já ocorreram.
       - Demonstramos:
         (a) uso do Greeter (depende do prefixo @Bean),
         (b) uso da MusicBox,
         (c) comparação de escopos (singleton vs prototype),
         (d) encerramento do contexto para disparar @PreDestroy em singletons.
       ============================================================================================= */
    @Component
    static class DemoRunner implements CommandLineRunner {

        private final Greeter greeter;             // @Service injetado
        private final MusicBox musicBox;           // @Component injetado
        private final ConfigurableApplicationContext context; // para fechar o contexto ao final

        DemoRunner(Greeter greeter, MusicBox musicBox, ConfigurableApplicationContext context) {
            this.greeter = greeter;
            this.musicBox = musicBox;
            this.context  = context;
        }

        @Override
        public void run(String... args) {
            System.out.println("\n========= DEMONSTRAÇÃO DIDÁTICA =========");

            // (a) Usando o Greeter (note que ele já passou por @PostConstruct)
            System.out.println("[Demo] Greeter diz: " + greeter.say("Ana"));

            // (b) Usando a MusicBox (também já passou por @PostConstruct)
            musicBox.play("Lo-fi hip hop");

            // (c) Escopos
            System.out.println("\n--- ESCOPOS ---");
            SingletonProbe s1 = context.getBean(SingletonProbe.class);
            SingletonProbe s2 = context.getBean(SingletonProbe.class);
            System.out.println("[Demo] SingletonProbe IDs: s1=" + s1.getId() + " | s2=" + s2.getId()
                    + "  (mesma instância)");

            PrototypeProbe p1 = context.getBean(PrototypeProbe.class);
            PrototypeProbe p2 = context.getBean(PrototypeProbe.class);
            System.out.println("[Demo] PrototypeProbe IDs: p1=" + p1.getId() + " | p2=" + p2.getId()
                    + "  (instâncias diferentes)");

            System.out.println("\n========= FIM DA DEMONSTRAÇÃO =========\n");

            // (d) Encerrar o contexto → dispara @PreDestroy nos BEANS SINGLETON
            //     (Veja as mensagens do Greeter e da MusicBox ao fechar)
            context.close();
        }
    }
}