/*
 * Mini-programa para demonstrar Inversão de Controle (IoC) e Injeção de Dependência (DI)
 *
 * Ideia:
 * 1) Definimos DUAS “peças” (interfaces), que a regra precisa conhecer:
 *    - TotalGateway: sabe LER e GRAVAR o total
 *    - MessageGateway: sabe EXIBIR mensagens
 *
 * 2) Definimos a REGRA (CounterService): “somar um valor ao total e anunciar o novo total”.
 *    - A regra NÃO cria dependências (não dá new). Ela recebe as dependências no construtor.
 *
 * 3) No ponto de entrada (main), FORA da regra, escolhemos as IMPLEMENTAÇÕES CONCRETAS,
 *    instanciamos e INJETAMOS na regra — provando a IoC.
 *
 * 4) Rodamos somando dois valores e observamos as mensagens.
 *
 * 5) Para provar a IoC, TROCAMOS SOMENTE no ponto de entrada a implementação de TotalGateway,
 *    sem tocar na regra, e executamos de novo.
 */

// =========================
// 1) ABSTRAÇÕES (contratos)
// =========================

/** Sabe ler e gravar o total. */
interface TotalGateway {
    int load();            // Lê o total atual
    void save(int total);  // Persiste o novo total
}

/** Sabe exibir mensagens para quem estiver usando o sistema. */
interface MessageGateway {
    void show(String message);
}

// ===============================
// 2) REGRA DE NEGÓCIO (sem “new”)
// ===============================

/**
 * Regra: somar um valor ao total e anunciar o novo total.
 * Não conhece classes concretas; só conhece os contratos (interfaces) acima.
 */
final class CounterService {
    private final TotalGateway totals;
    private final MessageGateway messages;

    public CounterService(TotalGateway totals, MessageGateway messages) {
        this.totals = totals;
        this.messages = messages;
    }

    /** Soma 'value' ao total, persiste e anuncia. */
    public void add(int value) {
        int current = totals.load();
        int updated = current + value;
        totals.save(updated);
        messages.show("Somado " + value + ". Novo total = " + updated);
    }
}

// ========================================
// 3) IMPLEMENTAÇÕES CONCRETAS (adaptadores)
// ========================================

/** Implementação 1 (default): total em memória simples. */
final class InMemoryTotalGateway implements TotalGateway {
    private int total;

    public InMemoryTotalGateway(int initial) {
        this.total = initial;
    }

    @Override public int load() { return total; }

    @Override public void save(int total) { this.total = total; }
}

/**
 * Implementação 2 (equivalente) para provar a IoC:
 * também guarda em memória, mas com “log” para diferenciar o comportamento visivelmente.
 */
final class VerboseInMemoryTotalGateway implements TotalGateway {
    private int total;

    public VerboseInMemoryTotalGateway(int initial) {
        this.total = initial;
        System.out.println("[VerboseTotal] inicial = " + total);
    }

    @Override public int load() {
        System.out.println("[VerboseTotal] load -> " + total);
        return total;
    }

    @Override public void save(int total) {
        System.out.println("[VerboseTotal] save " + this.total + " -> " + total);
        this.total = total;
    }
}

/** Implementação simples: escreve mensagens no console. */
final class ConsoleMessageGateway implements MessageGateway {
    @Override public void show(String message) {
        System.out.println("[MSG] " + message);
    }
}

// =============================
// 4) PONTO DE ENTRADA (IoC/DI)
// =============================

public class AppIoC {
    public static void main(String[] args) {
        // Escolha das implementações CONCRETAS (fora da regra):
        // ----------------------------------------------------
        // Versão A (padrão): total em memória simples
        TotalGateway totalGateway = new InMemoryTotalGateway(0);

        // // >>> Descomente esta linha e comente a de cima para "provar" a IoC:
        // TotalGateway totalGateway = new VerboseInMemoryTotalGateway(0);

        MessageGateway messageGateway = new ConsoleMessageGateway();

        // A regra recebe as dependências prontas (sem “dar new” internamente):
        CounterService contador = new CounterService(totalGateway, messageGateway);

        // 5) Rodar somando dois valores e observar as mensagens
        contador.add(10); // Somar 10
        contador.add(5);  // Somar 5

        // Se você alternar para VerboseInMemoryTotalGateway no ponto de entrada,
        // verá logs extras SEM mudar a regra CounterService.
    }
}