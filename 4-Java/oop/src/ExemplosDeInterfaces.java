public class ExemplosDeInterfaces {

    @FunctionalInterface
    interface InterfaceFuncional {
        void executar();
    }

    interface InterfaceTradicional {
        void metodo1();
        void metodo2(String mensagem);
    }

    interface InterfaceComDefault {
        void metodoObrigatorio();

        default void metodoDefault() {
            metodoObrigatorio();
        }
    }

    interface InterfaceComStatic {
        static void metodoEstatico() {
            System.out.println("Metodo Estático");
        }
    }

    interface InterfaceComConstante {
        String COSNTANTE = "Valor";

        void usarConstante();
    }

    interface InterfaceBase {
        void metodoBase();
    }

    interface InterfaceHerdada extends InterfaceBase {
        void metodoHerdado();
    }

    public static void main(String[] args) {
        InterfaceFuncional funcional = () -> System.out.println("Interface Funcional!");
        funcional.executar();

        InterfaceTradicional tradicional = new InterfaceTradicional() {
            @Override
            public void metodo1() {
                System.out.println("Método 1");
            }

            @Override
            public void metodo2(String mensagem) {
                System.out.println(mensagem);
            }
        };

        tradicional.metodo1();
        tradicional.metodo2("Teste do método tradicional 2");
    }
}
