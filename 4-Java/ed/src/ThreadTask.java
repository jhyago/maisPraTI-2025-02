public class ThreadTask {
    public void executarTarefa() {
        Thread t = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                System.out.println("Executando tarefa - Passo " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        t.start();
    }
}
