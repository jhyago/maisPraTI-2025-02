package ExercicioPraticaErros;

//Implemente um sistema de vendas de ingressos para um evento. Crie uma classe Evento que tenha um número limitado de ingressos. Quando um ingresso for comprado, o número de ingressos disponíveis deve ser decrementado. Caso o número de ingressos disponíveis seja insuficiente para uma nova compra, lance uma exceção personalizada chamada IngressosIndisponiveisException. Além disso, se um usuário tentar comprar um número de ingressos maior que o permitido (máximo de 10 ingressos por compra), lance uma exceção personalizada chamada LimiteCompraExcedidoException.

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
