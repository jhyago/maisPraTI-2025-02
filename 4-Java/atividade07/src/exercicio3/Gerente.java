package exercicio3;                              // Pacote onde a classe está organizada.

import java.math.BigDecimal;                     // BigDecimal para operações monetárias com precisão.
import java.math.RoundingMode;                   // Estratégia de arredondamento (HALF_UP é comum em moeda).

/**
 * Representa um funcionário do tipo Gerente.
 * Regra: bônus fixo de 20% sobre o salário base.
 */
public final class Gerente extends Funcionario { // 'final' evita heranças acidentais (opcional).

    // Constante para a taxa de bônus (evita "número mágico" no código e facilita manutenção).
    private static final BigDecimal BONUS_RATE = new BigDecimal("0.20"); // 20%

    /**
     * Construtor do Gerente.
     * @param nome Nome do funcionário (validado na superclasse).
     * @param salario Salário base (validado e normalizado na superclasse).
     */
    public Gerente(String nome, BigDecimal salario) {
        super(nome, salario);                    // Delega validações/normalizações para Funcionario.
    }

    /**
     * Calcula o bônus do gerente (20% do salário).
     * @return Bônus com 2 casas decimais e arredondamento HALF_UP.
     */
    @Override
    public BigDecimal calcularBonus() {
        // Usa o getter para respeitar o encapsulamento da superclasse (campos são privados/finais).
        BigDecimal salarioAtual = getSalario();

        // Calcula: salário * 0.20 e ajusta para 2 casas decimais (padrão de moeda).
        return salarioAtual
                .multiply(BONUS_RATE)            // Multiplica pelo fator de 20%.
                .setScale(2, RoundingMode.HALF_UP); // Normaliza o resultado para 2 casas com HALF_UP.
    }
}