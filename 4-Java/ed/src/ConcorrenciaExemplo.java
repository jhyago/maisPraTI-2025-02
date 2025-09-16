public class ConcorrenciaExemplo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Thread Principal Iniciada");
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Tarefa 1 - Passo " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Tarefa 2 - Passo " + i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Threads finalizaram, programa encerrou.");

        ThreadTask task = new ThreadTask();

        task.executarTarefa();

        System.out.println("Thread Principal Continua Executando");
    }
}
