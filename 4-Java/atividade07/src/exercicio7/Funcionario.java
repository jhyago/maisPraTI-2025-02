package exercicio7;                                                  // Pacote onde as classes/enum estão.

import java.util.Objects;                                            // Utilitário para checagens de nulidade.

/** Enum para representar cargos de forma segura e legível. */
enum Cargo {                                                         // Enum evita "números mágicos" e erros de digitação.
    ESTAGIARIO, JUNIOR, PLENO, SENIOR, GERENTE, DIRETOR
}

/**
 * Entidade Funcionario imutável, identificável por String.
 * - Campos finais (imutabilidade)
 * - Validações de id/nome/cargo
 * - equals/hashCode baseados em id (identidade de negócio)
 */
public final class Funcionario implements Identificavel<String> {    // 'final' evita heranças acidentais.

    private final String id;                                         // Identificador único (não nulo / não em branco).
    private final String nome;                                       // Nome do funcionário (não nulo / não em branco).
    private final Cargo cargo;                                       // Cargo tipado por enum (não nulo).

    /**
     * Construtor com validações e normalizações simples.
     * @param id identificador único (não pode ser nulo ou em branco)
     * @param nome nome do funcionário (não pode ser nulo ou em branco)
     * @param cargo cargo do funcionário (não pode ser nulo)
     */
    public Funcionario(String id, String nome, Cargo cargo) {        // Construtor principal.
        if (id == null || id.isBlank()) {                            // Valida id: nulo ou só espaços? inválido.
            throw new IllegalArgumentException("id não pode ser nulo ou em branco.");
        }
        if (nome == null || nome.isBlank()) {                        // Valida nome: nulo ou só espaços? inválido.
            throw new IllegalArgumentException("nome não pode ser nulo ou em branco.");
        }
        Objects.requireNonNull(cargo, "cargo não pode ser nulo.");   // Valida cargo: não pode ser nulo.

        this.id = id.trim();                                         // Normaliza id removendo espaços nas pontas.
        this.nome = nome.trim();                                     // Normaliza nome.
        this.cargo = cargo;                                          // Atribui o cargo (enum).
    }

    @Override
    public String getId() {                                          // Implementa contrato de Identificavel<String>.
        return this.id;                                              // Retorna id imutável.
    }

    public String getNome() {                                        // Getter do nome.
        return this.nome;                                            // Retorna nome imutável.
    }

    public Cargo getCargo() {                                        // Getter do cargo (enum).
        return this.cargo;                                           // Retorna cargo imutável.
    }

    @Override
    public boolean equals(Object o) {                                // Igualdade por identidade de negócio (id).
        if (this == o) return true;                                  // Mesma referência: iguais.
        if (!(o instanceof Funcionario other)) return false;         // Tipos diferentes: não é igual.
        return id.equals(other.id);                                  // Compara apenas o id.
    }

    @Override
    public int hashCode() {                                          // Hash consistente com equals (usa id).
        return id.hashCode();
    }

    @Override
    public String toString() {                                       // Representação legível para logs/depuração.
        return "Funcionario{id='" + id + "', nome='" + nome + "', cargo=" + cargo + "}";
    }
}