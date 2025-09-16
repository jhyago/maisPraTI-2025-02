import java.util.concurrent.*;
import java.io.*;

public class ExemploParalelismo {

    private static int contador = 0; // Variável compartilhada

    public static void main(String[] args) {

        // Exemplo 1: Paralelismo com ExecutorService e múltiplas threads
        System.out.println("Iniciando Exemplo 1: Paralelismo com ExecutorService");

        // ExecutorService com número de threads igual ao número de núcleos do processador
        int numeroDeNucleos = Runtime.getRuntime().availableProcessors(); // Obtém o número de núcleos do processador
        ExecutorService executor = Executors.newFixedThreadPool(numeroDeNucleos); // Cria o pool de threads

        // Submete 5 tarefas para serem executadas no pool de threads
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executando tarefa.");
                try {
                    Thread.sleep(1000); // Simula o trabalho com uma pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.out.println("Thread interrompida.");
                }
            });
        }
        executor.shutdown(); // Finaliza o executor quando todas as tarefas terminarem

        // Exemplo 2: Paralelismo com Processos Externos
        System.out.println("\nIniciando Exemplo 2: Paralelismo com Processos Externos");

        try {
            // Criamos um processo externo para executar o comando "ping" no sistema operacional
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "google.com");
            processBuilder.redirectErrorStream(true); // Redireciona o erro para o fluxo padrão de saída

            // Inicia o processo
            Process process = processBuilder.start();

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

        // Exemplo 3: Paralelismo com Contagem de Variável Compartilhada
        System.out.println("\nIniciando Exemplo 3: Paralelismo com Contagem de Variável Compartilhada");

        // Vamos incrementar o contador com múltiplas threads em paralelo
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                contador++; // Incrementa a variável compartilhada
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                contador++; // Incrementa a variável compartilhada
            }
        });

        thread1.start(); // Inicia a thread 1
        thread2.start(); // Inicia a thread 2

        try {
            thread1.join(); // Aguarda a thread 1 finalizar
            thread2.join(); // Aguarda a thread 2 finalizar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final do contador (paralelismo): " + contador);

        // Exemplo 4: Paralelismo com Múltiplos Processos
        System.out.println("\nIniciando Exemplo 4: Paralelismo com Múltiplos Processos");

        // Criando dois processos externos que podem ser executados em paralelo
        ProcessBuilder process1 = new ProcessBuilder("ping", "google.com");
        ProcessBuilder process2 = new ProcessBuilder("ping", "yahoo.com");

        try {
            Process p1 = process1.start(); // Inicia o primeiro processo
            Process p2 = process2.start(); // Inicia o segundo processo

            // Lê a saída do primeiro processo
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            String line1;
            while ((line1 = reader1.readLine()) != null) {
                System.out.println("Processo 1: " + line1); // Exibe a saída do primeiro processo
            }

            // Lê a saída do segundo processo
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                System.out.println("Processo 2: " + line2); // Exibe a saída do segundo processo
            }

            int exitCode1 = p1.waitFor(); // Aguarda o primeiro processo terminar
            int exitCode2 = p2.waitFor(); // Aguarda o segundo processo terminar

            System.out.println("Processo 1 finalizado com código de saída: " + exitCode1);
            System.out.println("Processo 2 finalizado com código de saída: " + exitCode2);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// - **Exemplo 1**: Usamos o `ExecutorService` para criar um pool de threads com um número de threads igual ao número de núcleos de CPU do sistema. Isso permite que as threads sejam executadas em paralelo, aproveitando o poder de múltiplos núcleos.
// - **Exemplo 2**: Executamos processos externos em paralelo. Isso simula como podemos executar programas do sistema operacional em paralelo, usando múltiplos processos ao mesmo tempo.
// - **Exemplo 3**: A contagem de variáveis compartilhadas é feita em paralelo por duas threads diferentes. Se o sistema tiver múltiplos núcleos, essas threads podem ser executadas simultaneamente em núcleos diferentes.
// - **Exemplo 4**: Demonstramos o uso de múltiplos processos para execução paralela, onde dois processos do sistema operacional (`ping` para google.com e yahoo.com) são executados ao mesmo tempo.