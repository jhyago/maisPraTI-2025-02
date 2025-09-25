package exercicio4;                       // Pacote da interface.

/**
 * Contrato para meios de transporte que podem acelerar e frear.
 *
 * Regras de contrato recomendadas para implementações:
 * - {@link #acelerar()} não deve ultrapassar o limite máximo de velocidade
 *   definido pela implementação (ex.: "capar" no limite).
 * - {@link #frear()} não deve reduzir a velocidade abaixo de 0.
 *
 * Observação sobre exceções:
 * - Implementações podem lançar {@link IllegalStateException} em estados inválidos
 *   (ex.: tentar acelerar além do limite), mas como é uma exceção unchecked,
 *   não é necessário declará-la no "throws". Prefira manter o método idempotente
 *   nos limites (apenas não passar do limite) e documentar o comportamento.
 */
public interface IMeioTransporte {        // Interface pública: outras classes/pacotes podem implementá-la.

    /**
     * Aumenta a velocidade respeitando o limite máximo definido pela implementação.
     * Implementações podem optar por "capar" no limite ou lançar IllegalStateException.
     */
    void acelerar();                      // Método de ação (sem retorno).

    /**
     * Reduz a velocidade respeitando o piso zero (nunca ficar negativo).
     * Implementações podem optar por "capar" em 0 ou lançar IllegalStateException.
     */
    void frear();                          // Método de ação (sem retorno).
}