package exercicio3;                          // Define o pacote onde a classe está organizada.

import java.math.BigDecimal;                 // BigDecimal para valores monetários (precisão exata).
import java.math.RoundingMode;               // Define a estratégia de arredondamento (HALF_UP é comum em moeda).

/**
 * Representa um funcionário do tipo Desenvolvedor.
 * Regras: bônus fixo de 10% sobre o salário.
 */
public final class Desenvolvedor extends Funcionario { // 'final' evita heranças acidentais.

    // Constante para a taxa de bônus (evita "número mágico" no código).
    private static final BigDecimal BONUS_RATE = new BigDecimal("0.10"); // 10%

    /**
     * Construtor do Desenvolvedor.
     * @param nome Nome do funcionário.
     * @param salario Salário base (BigDecimal, recomendado para dinheiro).
     */
    public Desenvolvedor(String nome, BigDecimal salario) {
        super(nome, salario);                // Delega validação e atribuições à superclasse Funcionario.
    }

    /**
     * Calcula o bônus do desenvolvedor (10% do salário).
     * @return Bônus com 2 casas decimais, arredondamento HALF_UP.
     */
    @Override
    public BigDecimal calcularBonus() {
        // Usa o getter para respeitar encapsulamento (em vez de acessar um campo protegido diretamente).
        // Pressupõe que Funcionario expõe getSalario(); se não expuser, considere adicioná-lo.
        BigDecimal salarioAtual = getSalario();

        // Calcula 10% do salário e normaliza para 2 casas com HALF_UP (padrão bancário comum no BR).
        return salarioAtual
                .multiply(BONUS_RATE)        // salário * 0.10
                .setScale(2, RoundingMode.HALF_UP); // Ajusta para 2 casas decimais (moeda).
    }
}