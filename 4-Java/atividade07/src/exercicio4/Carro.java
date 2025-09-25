package exercicio4;                                      // Pacote da classe.

import java.util.Objects;                                // Para validações simples (se precisar no futuro).

/**
 * Implementação de um meio de transporte do tipo Carro.
 * Possui velocidade atual e um limite de velocidade (capado).
 */
public class Carro implements IMeioTransporte {          // Implementa a interface IMeioTransporte (acelerar/frear).

    // --- Constantes de configuração ---
    private static final int INCREMENTO_PADRAO = 10;     // Quanto o carro acelera/freia por chamada.
    private static final int LIMITE_PADRAO = 180;        // Limite padrão de velocidade (km/h).

    // --- Estado do objeto ---
    private int velocidade;                              // Velocidade atual (nunca negativa).
    private final int limiteVelocidade;                  // Limite máximo (sempre > 0).

    /**
     * Construtor padrão: velocidade inicia em 0 e limite em 180 km/h.
     */
    public Carro() {
        this.velocidade = 0;                             // Começa parado.
        this.limiteVelocidade = LIMITE_PADRAO;           // Usa o limite padrão.
    }

    /**
     * Construtor com limite customizável.
     * @param limiteVelocidade Limite máximo (deve ser > 0).
     */
    public Carro(int limiteVelocidade) {
        if (limiteVelocidade <= 0) {                     // Validação do limite informado.
            throw new IllegalArgumentException("O limite de velocidade deve ser maior que zero.");
        }
        this.velocidade = 0;                             // Começa parado.
        this.limiteVelocidade = limiteVelocidade;        // Usa o limite informado.
    }

    @Override
    public void acelerar() {                             // Aumenta a velocidade respeitando o limite.
        if (velocidade >= limiteVelocidade) {            // Já está no limite?
            System.out.println("Carro já está no limite de " + limiteVelocidade + " km/h."); // Mensagem informativa.
            return;                                      // Não faz nada além de informar.
        }
        // Calcula a nova velocidade sem ultrapassar o limite (clamping com Math.min).
        int novaVelocidade = Math.min(velocidade + INCREMENTO_PADRAO, limiteVelocidade);
        velocidade = novaVelocidade;                     // Atualiza o estado.
        System.out.println("Carro acelerando, velocidade atual: " + velocidade + " km/h"); // Feedback.
    }

    @Override
    public void frear() {                                // Diminui a velocidade respeitando o piso zero.
        if (velocidade <= 0) {                           // Já está parado?
            System.out.println("Carro já está parado."); // Mensagem informativa.
            return;                                      // Não há o que frear.
        }
        // Calcula a nova velocidade sem ficar negativa (clamping com Math.max).
        int novaVelocidade = Math.max(velocidade - INCREMENTO_PADRAO, 0);
        velocidade = novaVelocidade;                     // Atualiza o estado.
        System.out.println("Carro freando, velocidade atual: " + velocidade + " km/h"); // Feedback.
    }

    // --- Utilidades/encapsulamento ---

    /** Velocidade atual em km/h. */
    public int getVelocidade() {
        return velocidade;                                // Retorna o valor atual.
    }

    /** Limite máximo de velocidade em km/h. */
    public int getLimiteVelocidade() {
        return limiteVelocidade;                          // Retorna o limite configurado.
    }

    @Override
    public String toString() {                            // Útil para logs/depuração.
        return "Carro{velocidade=" + velocidade +
               ", limiteVelocidade=" + limiteVelocidade + '}';
    }
}