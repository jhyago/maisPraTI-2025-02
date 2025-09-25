import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.concurrent.*;                   // ExecutorService, Future, TimeUnit, etc.
import java.util.concurrent.atomic.AtomicInteger; // Tipos atômicos (ex.: AtomicInteger)

/*
 * ======================== CONCEITOS FUNDAMENTAIS (ANTES DO CÓDIGO) ========================
 *
 * Processo x Thread
 * - Processo: um programa em execução, com seu próprio espaço de memória.
 * - Thread: linha de execução dentro do processo; threads do mesmo processo compartilham memória.
 *
 * Concorrência x Paralelismo
 * - Concorrência: estruturar o programa para lidar com múltiplas tarefas “ao mesmo tempo” (intercaladas).
 * - Paralelismo: executar de fato ao mesmo tempo, em múltiplos núcleos de CPU.
 *   (Todo programa paralelo é concorrente, mas nem todo concorrente é realmente paralelo.)
 *
 * Thread pool (pool de threads)
 * - Em vez de criar/destruir threads a cada tarefa (caro), mantemos um “pool” (piscina) de threads reutilizáveis.
 * - Benefícios: melhor desempenho, controle de quantas tarefas rodam simultaneamente, shutdown ordenado.
 * - Em Java: ExecutorService (ex.: Executors.newFixedThreadPool(N)).
 *
 * Thread-safe
 * - Um código é “thread-safe” quando funciona corretamente mesmo com várias threads simultâneas.
 * - Problemas comuns sem segurança:
 *   • Race condition (condição de corrida): o resultado depende da ordem de interleaving das threads;
 *     ex.: contador++ em duas threads pode “perder” incrementos.
 *   • Seções críticas: trechos que acessam/modificam estado compartilhado e precisam de proteção.
 *   • Visibilidade: alterações feitas por uma thread podem não ser vistas por outra sem garantias de memória.
 *
 * Como garantir thread-safety (algumas técnicas)
 * - Imutabilidade (objetos que não mudam são naturalmente thread-safe).
 * - Sincronização (synchronized, locks): garante exclusão mútua e relação de “happens-before” (visibilidade).
 * - Tipos atômicos (AtomicInteger, AtomicLong…): operações atômicas sem bloqueio para casos simples.
 * - Estruturas prontas e thread-safe (ex.: ConcurrentHashMap).
 *
 * Processos externos
 * - Podemos executar programas do sistema (ex.: “ping”) via ProcessBuilder.
 * - É importante ler stdout/stderr para não bloquear, e esperar o término (waitFor).
 * - Em multiplataforma, parâmetros podem variar (Windows x Unix-like).
 *
 * Boas práticas com ExecutorService
 * - Sempre chamar shutdown() e, se necessário, awaitTermination(...).
 * - Em caso de interrupção, restabeleça o status com Thread.currentThread().interrupt().
 *
 * A seguir, aplicamos esses conceitos em quatro exemplos práticos.
 * ==========================================================================================
 */
public class ExemploParalelismo {

    // Exemplo de estado compartilhado protegido de forma thread-safe:
    // AtomicInteger realiza incrementos atômicos (sem necessidade de synchronized para esse caso simples).
    private static final AtomicInteger contador = new AtomicInteger(0);

    public static void main(String[] args) {

        /* =========================================================================================
         * EXEMPLO 1 — Pool de Threads (ExecutorService)
         * Ideia: disparar várias tarefas “simultâneas” reutilizando um conjunto fixo de threads.
         * ========================================================================================= */
        System.out.println("Exemplo 1: Pool de threads com ExecutorService");

        int nucleos = Runtime.getRuntime().availableProcessors(); // núcleos lógicos disponíveis
        ExecutorService pool = Executors.newFixedThreadPool(nucleos);

        for (int i = 1; i <= 5; i++) {
            final int tarefaId = i;
            pool.submit(() -> {
                String nome = Thread.currentThread().getName();
                System.out.println("[Tarefa " + tarefaId + "] executando em " + nome);
                try {
                    Thread.sleep(800); // simula trabalho (apenas didático)
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("[Tarefa " + tarefaId + "] interrompida");
                }
                System.out.println("[Tarefa " + tarefaId + "] concluída");
            });
        }

        pool.shutdown(); // sinaliza que não haverá novas tarefas
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) { // aguarda término das atuais
                pool.shutdownNow(); // força se necessário
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        /* =========================================================================================
         * EXEMPLO 2 — Processo externo único (ping) + leitura de saída
         * Notas:
         * - Windows usa "ping -n <N>", Unix-like usa "ping -c <N>".
         * - Redirecionamos stderr para stdout e lemos linha a linha.
         * ========================================================================================= */
        System.out.println("\nExemplo 2: Processo externo (ping único)");

        String[] pingGoogle = comandoPing("google.com", 3);
        try {
            ProcessBuilder pb = new ProcessBuilder(pingGoogle).redirectErrorStream(true);
            Process proc = pb.start();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    System.out.println("[PING] " + linha);
                }
            }

            int exit = proc.waitFor();
            System.out.println("Processo 'ping google.com' terminou com código: " + exit);
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        /* =========================================================================================
         * EXEMPLO 3 — Variável compartilhada com AtomicInteger (thread-safe)
         * Duas threads incrementam a mesma variável; AtomicInteger evita condição de corrida.
         * Valor esperado: 10_000.
         * ========================================================================================= */
        System.out.println("\nExemplo 3: Contagem concorrente com AtomicInteger");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5_000; i++) contador.incrementAndGet();
        }, "contador-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5_000; i++) contador.incrementAndGet();
        }, "contador-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Valor final do contador (esperado 10000): " + contador.get());

        /* =========================================================================================
         * EXEMPLO 4 — Dois processos externos em paralelo
         * - Disparamos dois pings e lemos as saídas em tarefas separadas (não bloqueia um pelo outro).
         * - Usamos um pool só para os leitores das saídas.
         * ========================================================================================= */
        System.out.println("\nExemplo 4: Dois processos externos em paralelo");

        String[] pingGoogle2 = comandoPing("google.com", 2);
        String[] pingYahoo2  = comandoPing("yahoo.com",  2);

        ExecutorService leitores = Executors.newFixedThreadPool(2);
        try {
            Process p1 = new ProcessBuilder(pingGoogle2).redirectErrorStream(true).start();
            Process p2 = new ProcessBuilder(pingYahoo2).redirectErrorStream(true).start();

            Future<?> f1 = leitores.submit(() -> lerSaidaProcesso("PROC-1", p1));
            Future<?> f2 = leitores.submit(() -> lerSaidaProcesso("PROC-2", p2));

            int ec1 = p1.waitFor();
            int ec2 = p2.waitFor();

            // Garante que a leitura terminou
            f1.get();
            f2.get();

            System.out.println("Processo 1 finalizado com código: " + ec1);
            System.out.println("Processo 2 finalizado com código: " + ec2);
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            leitores.shutdownNow();
        }

        System.out.println("\nFim.");
    }

    // ============================== Funções auxiliares ==============================

    /**
     * Monta o comando de ping compatível com o sistema operacional.
     *  - Windows: ping -n <contagem> host
     *  - Unix-like: ping -c <contagem> host
     */
    private static String[] comandoPing(String host, int contagem) {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        boolean windows = os.contains("win");
        if (windows) {
            return new String[]{"ping", "-n", String.valueOf(contagem), host};
        } else {
            return new String[]{"ping", "-c", String.valueOf(contagem), host};
        }
    }

    /** Lê stdout do processo e imprime com prefixo; requer redirectErrorStream(true) no ProcessBuilder. */
    private static void lerSaidaProcesso(String prefixo, Process p) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println("[" + prefixo + "] " + linha);
            }
        } catch (IOException e) {
            System.out.println("Falha ao ler saída (" + prefixo + "): " + e.getMessage());
        }
    }
}