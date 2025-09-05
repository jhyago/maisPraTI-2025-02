public class ExemplosDeMetodos {

    public static void metodoEstatico() {
        System.out.println("Método Estático");
    }

    public void metodoDeInstancia() {
        System.out.println("Método tradicional");
    }

    public abstract static class ClasseComMetodoAbstrato {
        public abstract void metodoAbstrato();
    }

    public static class SubClasseComMetodoAbstrato extends ClasseComMetodoAbstrato {
        @Override
        public void metodoAbstrato() {
            System.out.println("Método Abstrato");
        }
    }

    public static void main(String[] args) {
        ExemplosDeMetodos.metodoEstatico();

        ExemplosDeMetodos metodoDeInstancia = new ExemplosDeMetodos();
        metodoDeInstancia.metodoDeInstancia();

        SubClasseComMetodoAbstrato metodoAbstrato = new SubClasseComMetodoAbstrato();
        metodoAbstrato.metodoAbstrato();

    }
}
