import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// O que é uma thread?
// Uma thread é como uma linha de execução dentro de um programa. Imagine que o programa seja uma fábrica com várias esteiras (threads), onde cada esteira trabalha ao mesmo tempo em tarefas diferentes.

// O que é concorrência?
// Concorrência acontece quando várias threads tentam executar ao mesmo tempo, mas o processador alterna rapidamente entre elas. Elas não precisam estar rodando ao mesmo tempo, mas estão "concorrendo" pelo tempo do processador.

// O que é paralelismo?
// Paralelismo é quando várias threads estão realmente rodando ao mesmo tempo, em processadores diferentes ou núcleos. É como se várias esteiras estivessem funcionando ao mesmo tempo, sem esperar uma pela outra.

public class ExemploThreadsConcorrencia {

    private static int contador = 0; // Variável compartilhada

    public static void main(String[] args) {
        // Exemplo 1: Thread Básica
        System.out.println("Iniciando Exemplo 1: Thread Básica");
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " executando: " + i);
                try {
                    Thread.sleep(500); // Pausa de 500ms
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 interrompida");
                }
            }
        });
        thread1.start(); // Inicia a thread

        // Thread Principal
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread principal executando " + i);
            try {
                Thread.sleep(300); // Pausa de 300ms
            } catch (InterruptedException e) {
                System.out.println("Thread principal interrompida");
            }
        }
        System.out.println("Thread principal finalizada");

        // Exemplo 2: Sincronização de Threads
        System.out.println("\nIniciando Exemplo 2: Sincronização de Threads");
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                incrementaContador(); // Incremento sincronizado
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                incrementaContador(); // Incremento sincronizado
            }
        });
        thread2.start(); // Inicia a thread 2
        thread3.start(); // Inicia a thread 3

        try {
            thread2.join(); // Aguarda a thread 2 finalizar
            thread3.join(); // Aguarda a thread 3 finalizar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Valor final do contador após sincronização: " + contador);

        // Exemplo 3: ExecutorService
        System.out.println("\nIniciando Exemplo 3: ExecutorService");
        ExecutorService executor = Executors.newFixedThreadPool(2); // Cria um pool de threads com 2 threads
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executando tarefa.");
                try {
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.out.println("Thread interrompida.");
                }
            });
        }
        executor.shutdown(); // Finaliza o executor quando todas as tarefas terminarem

        // Exemplo 4: CountDownLatch
        System.out.println("\nIniciando Exemplo 4: CountDownLatch");
        CountDownLatch latch = new CountDownLatch(3); // Espera por 3 threads
        Thread thread4 = new Thread(() -> {
            try {
                System.out.println("Thread 1 começando...");
                Thread.sleep(1000);
                System.out.println("Thread 1 finalizada.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); // Decrementa o contador do latch
            }
        });
        Thread thread5 = new Thread(() -> {
            try {
                System.out.println("Thread 2 começando...");
                Thread.sleep(1500);
                System.out.println("Thread 2 finalizada.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); // Decrementa o contador do latch
            }
        });
        Thread thread6 = new Thread(() -> {
            try {
                System.out.println("Thread 3 começando...");
                Thread.sleep(2000);
                System.out.println("Thread 3 finalizada.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); // Decrementa o contador do latch
            }
        });

        thread4.start();
        thread5.start();
        thread6.start();

        try {
            latch.await(); // Aguarda até que todas as threads decrementem o latch
            System.out.println("Todas as threads finalizaram! Continuando com o processamento...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exemplo 5: Processos Externos com ProcessBuilder
        System.out.println("\nIniciando Exemplo 5: Processos Externos");
        try {
            // Criamos um processo externo para executar o comando "ping" no sistema operacional
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "google.com");
            processBuilder.redirectErrorStream(true); // Redireciona o erro para o fluxo padrão de saída
            Process process = processBuilder.start(); // Inicia o processo

            // Lê a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Exibe cada linha da saída do comando
            }

            int exitCode = process.waitFor(); // Aguarda o processo terminar e captura o código de saída
            System.out.println("Processo finalizado com código de saída: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    // Exemplo de método sincronizado
    public static synchronized void incrementaContador() {
        contador++; // Incrementa a variável compartilhada
    }
}

// - **Exemplo 1**: Aqui vemos uma thread simples que é executada de forma concorrente com a thread principal.
// - **Exemplo 2**: Utilizamos o `synchronized` para garantir que a variável `contador` não seja acessada simultaneamente por várias threads, evitando erros de concorrência.
// - **Exemplo 3**: O `ExecutorService` permite gerenciar um grupo de threads (pool). O código cria um pool de 2 threads e executa 5 tarefas em paralelo.
// - **Exemplo 4**: O `CountDownLatch` é usado para sincronizar a execução de várias threads. O programa principal espera até que todas as threads finais tenham terminado.
// - **Exemplo 5**: O `ProcessBuilder` executa um processo externo (no caso, o comando `ping` no sistema operacional), permitindo que o programa interaja com o sistema fora da JVM.

