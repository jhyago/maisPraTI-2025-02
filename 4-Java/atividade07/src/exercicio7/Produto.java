package exercicio7;                                        // Pacote onde a classe está definida.

import java.math.BigDecimal;                               // BigDecimal: recomendado para valores monetários.
import java.math.RoundingMode;                             // Estratégia de arredondamento (HALF_UP é comum).
import java.util.Objects;                                  // Utilitário para checagens de nulidade.

/**
 * Entidade Produto imutável e identificável por Integer.
 * - Valida id/nome/preço
 * - Preço em BigDecimal (2 casas, HALF_UP)
 * - equals/hashCode por id
 */
public final class Produto implements Identificavel<Integer> { // 'final' evita heranças acidentais.

    private final Integer id;                                // Identificador único (não nulo).
    private final String nome;                               // Nome do produto (não nulo/nem em branco).
    private final BigDecimal preco;                          // Preço normalizado (2 casas decimais).

    /**
     * Construtor principal com BigDecimal.
     * @param id identificador (não pode ser nulo)
     * @param nome nome do produto (não pode ser nulo ou em branco)
     * @param preco preço (não pode ser nulo; deve ser >= 0)
     */
    public Produto(Integer id, String nome, BigDecimal preco) {
        Objects.requireNonNull(id, "id não pode ser nulo");              // Valida o id.
        if (nome == null || nome.isBlank()) {                            // Valida o nome.
            throw new IllegalArgumentException("nome não pode ser nulo ou em branco");
        }
        Objects.requireNonNull(preco, "preço não pode ser nulo");         // Preço não nulo.
        if (preco.compareTo(BigDecimal.ZERO) < 0) {                       // Preço >= 0.
            throw new IllegalArgumentException("preço deve ser >= 0");
        }

        this.id = id;                                                     // Atribui id.
        this.nome = nome.trim();                                          // Normaliza nome (remove espaços nas pontas).
        this.preco = preco.setScale(2, RoundingMode.HALF_UP);             // Normaliza preço: 2 casas, HALF_UP.
    }

    /**
     * Construtor de conveniência usando double.
     * @param id identificador (não pode ser nulo)
     * @param nome nome (não pode ser nulo ou em branco)
     * @param preco preço em double (será convertido para BigDecimal com escala 2)
     */
    public Produto(Integer id, String nome, double preco) {
        this(id, nome, BigDecimal.valueOf(preco));                        // Converte double → BigDecimal de forma segura.
    }

    @Override
    public Integer getId() {                                              // Implementa Identificavel<Integer>.
        return this.id;
    }

    public String getNome() {                                             // Getter do nome.
        return this.nome;
    }

    public BigDecimal getPreco() {                                        // Getter do preço (BigDecimal).
        return this.preco;                                                // BigDecimal é imutável → retorno é seguro.
    }

    @Override
    public boolean equals(Object o) {                                     // Igualdade baseada em id.
        if (this == o) return true;                                       // Mesma referência.
        if (!(o instanceof Produto other)) return false;                  // Tipo diferente → falso.
        return id.equals(other.id);                                       // Compara apenas o id (identidade de negócio).
    }

    @Override
    public int hashCode() {                                               // Hash consistente com equals (usa id).
        return id.hashCode();
    }

    @Override
    public String toString() {                                            // Representação legível (útil para logs).
        return "Produto{id=" + id + ", nome='" + nome + "', preco=" + preco.toPlainString() + "}";
    }
}