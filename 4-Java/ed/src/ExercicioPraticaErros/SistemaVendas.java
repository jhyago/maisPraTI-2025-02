package ExercicioPraticaErros;

public class SistemaVendas {
    public static void main(String[] args) {
        try {
            Evento evento = new Evento(50);

            evento.comprarIngressos(5);
            evento.comprarIngressos(12);
        } catch(IngressosDisponiveisException | LimiteCompraExcedidoException ex) {
            System.out.println("Erro: "+ ex.getMessage());
        }

        try {
            Evento evento = new Evento(50);

            evento.comprarIngressos(60);
        } catch(IngressosDisponiveisException | LimiteCompraExcedidoException ex) {
            System.out.println("Erro: "+ ex.getMessage());
        }
    }
}
