package exercicio3;                                  // Pacote onde a classe está organizada.

import java.math.BigDecimal;                         // BigDecimal: recomendado para valores monetários (precisão).
import java.math.RoundingMode;                      // Estratégia de arredondamento para normalizar casas decimais.
import java.util.Objects;                           // Utilitário para checagens de nulidade de forma clara.

/**
 * Classe base (abstrata) para funcionários.
 * Responsável por: validar e manter nome e salário, e expor o contrato de cálculo de bônus.
 */
public abstract class Funcionario {                 // 'public' permite uso da classe em outros pacotes/projetos.

    // Torna os campos privados e finais: encapsulados e imutáveis após construção.
    private final String nome;                      // Nome do funcionário (não nulo, não vazio).
    private final BigDecimal salario;               // Salário positivo, normalizado com 2 casas decimais.

    /**
     * Construtor com validações e normalização.
     * @param nome Nome do funcionário (não pode ser nulo ou em branco).
     * @param salario Salário base (não pode ser nulo e deve ser > 0).
     */
    public Funcionario(String nome, BigDecimal salario) {
        // Validação do nome: nulo ou em branco não é permitido.
        if (nome == null || nome.isBlank()) {       // 'isBlank' cobre vazio e apenas espaços.
            throw new IllegalArgumentException("Nome não pode ser nulo ou em branco.");
        }

        // Garante que salario não é nulo (mensagem clara).
        Objects.requireNonNull(salario, "Salário não pode ser nulo.");

        // Salário deve ser estritamente positivo (> 0).
        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário deve ser positivo.");
        }

        // Atribuições após validação:
        this.nome = nome.trim();                    // Normaliza o nome removendo espaços nas pontas.
        // Normaliza o salário para 2 casas decimais com HALF_UP (padrão comum para moeda).
        this.salario = salario.setScale(2, RoundingMode.HALF_UP);
    }

    /** Retorna o nome já validado e normalizado. */
    public String getNome() {
        return nome;                                // Campo é imutável (final).
    }

    /** Retorna o salário já validado e normalizado (2 casas decimais, HALF_UP). */
    public BigDecimal getSalario() {
        return salario;                             // BigDecimal é imutável; retorno é seguro.
    }

    /**
     * Contrato para cálculo de bônus.
     * Cada subclasse define sua própria regra (ex.: 10% para Desenvolvedor).
     * @return valor do bônus como BigDecimal (idealmente já normalizado a 2 casas).
     */
    public abstract BigDecimal calcularBonus();     // Método abstrato: força subclasses a implementar a regra.
}