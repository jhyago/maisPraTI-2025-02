package ExercicioPraticaErros;                                      // Pacote da classe (organização do projeto).

/**
 * Modela um evento com controle de ingressos disponíveis e limite por compra.
 */
public class Evento {

    // ------------------- Estado interno -------------------

    private int ingressosDisponiveis;                                // Quantidade atual de ingressos disponíveis (nunca negativa).
    private final int limiteCompra;                                  // Limite máximo de ingressos por compra (fixo por instância).

    // ------------------- Construtores ---------------------

    /**
     * Construtor padrão: define o limite por compra como 10 (coerente com a mensagem).
     * @param ingressosIniciais quantidade inicial de ingressos (deve ser >= 0)
     * @throws IllegalArgumentException se ingressosIniciais < 0
     */
    public Evento(int ingressosIniciais) {
        this(ingressosIniciais, 10);                                 // Delegação para o construtor completo com limite = 10.
    }

    /**
     * Construtor com limite customizável.
     * @param ingressosIniciais quantidade inicial de ingressos (>= 0)
     * @param limiteCompra limite de ingressos permitidos por compra (>= 1)
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public Evento(int ingressosIniciais, int limiteCompra) {
        if (ingressosIniciais < 0) {                                 // Validação: não faz sentido começar com negativo.
            throw new IllegalArgumentException("Ingressos iniciais devem ser >= 0.");
        }
        if (limiteCompra < 1) {                                      // Validação: precisa permitir pelo menos 1 ingresso por compra.
            throw new IllegalArgumentException("O limite por compra deve ser >= 1.");
        }
        this.ingressosDisponiveis = ingressosIniciais;               // Inicializa o estoque de ingressos.
        this.limiteCompra = limiteCompra;                            // Define o limite por compra para esta instância.
    }

    // ------------------- Comportamento --------------------

    /**
     * Realiza a compra de ingressos respeitando o limite por compra e a disponibilidade.
     *
     * @param quantidade quantidade de ingressos desejada (deve ser > 0)
     * @throws IllegalArgumentException se quantidade <= 0
     * @throws LimiteCompraExcedidoException se quantidade > limiteCompra
     * @throws IngressosDisponiveisException se quantidade > ingressosDisponiveis
     */
    public synchronized void comprarIngressos(int quantidade)                   // synchronized: evita corrida simples em cenários multi-thread.
            throws LimiteCompraExcedidoException, IngressosDisponiveisException {

        if (quantidade <= 0) {                                                  // Compra de 0 ou negativo não é válida.
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (quantidade > limiteCompra) {                                        // Regra de limite por compra.
            throw new LimiteCompraExcedidoException(
                    "Você não pode comprar mais de " + limiteCompra + " ingressos por compra.");
        }

        if (quantidade > this.ingressosDisponiveis) {                           // Checa disponibilidade suficiente.
            throw new IngressosDisponiveisException(
                    "Não há ingressos suficientes. Disponíveis: " + this.ingressosDisponiveis + ".");
        }

        this.ingressosDisponiveis -= quantidade;                                // Debita os ingressos do estoque.
        System.out.println("Compra realizada com sucesso! Ingressos restantes: " + this.ingressosDisponiveis);
    }

    // ------------------- Acesso/Utilidades ----------------

    /** Retorna a quantidade atual de ingressos disponíveis. */
    public int getIngressosDisponiveis() {
        return ingressosDisponiveis;                                            // Getter simples para o estoque atual.
    }

    /** Retorna o limite máximo de ingressos por compra desta instância. */
    public int getLimiteCompra() {
        return limiteCompra;                                                    // Getter para o limite configurado.
    }

    @Override
    public String toString() {                                                  // Representação útil para logs/depuração.
        return "Evento{ingressosDisponiveis=" + ingressosDisponiveis +
               ", limiteCompra=" + limiteCompra + '}';
    }
}