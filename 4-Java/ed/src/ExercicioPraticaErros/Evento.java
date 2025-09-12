package ExercicioPraticaErros;

public class Evento {
    private int ingessosDisponiveis;
    private final int limiteCompra = 60;

    public Evento(int ingressosIniciais) {
        this.ingessosDisponiveis = ingressosIniciais;
    }

    public void comprarIngressos(int quantidade) throws LimiteCompraExcedidoException, IngressosDisponiveisException {
        if(quantidade > limiteCompra) {
            throw new LimiteCompraExcedidoException("Você não pode comprar mais de 10 ingressos.");
        }

        if(quantidade > this.ingessosDisponiveis) {
            throw new IngressosDisponiveisException("Não há ingressos disponíveis!");
        }

        this.ingessosDisponiveis -= quantidade;
        System.out.println("Compra Realizada com Sucesso! Ingressos Restantes: " + this.ingessosDisponiveis);
    }
}
