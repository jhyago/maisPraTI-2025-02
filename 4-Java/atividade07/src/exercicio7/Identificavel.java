package exercicio7;                           // Pacote onde a interface está declarada.

import java.util.Objects;                     // Utilitário para comparações null-safe (Objects.equals).

/**
 * Contrato para entidades que possuem uma identidade (ID) única.
 *
 * <p>Convém que o tipo {@code ID} seja:
 * <ul>
 *   <li>Imutável (ex.: String, UUID);</li>
 *   <li>Com {@code equals}/{@code hashCode} bem definidos;</li>
 *   <li>Estável durante todo o ciclo de vida do objeto.</li>
 * </ul>
 *
 * <p>Boas práticas:
 * <ul>
 *   <li>Implementações podem definir {@code equals}/{@code hashCode} baseados apenas em {@code getId()}.</li>
 *   <li>Evite IDs mutáveis: alterar o ID pode quebrar coleções (ex.: HashSet/HashMap).</li>
 * </ul>
 *
 * @param <ID> tipo do identificador único (ex.: String, UUID)
 */
@FunctionalInterface                      // Garante um único método abstrato → uso com lambdas é permitido.
public interface Identificavel<ID> {

    /**
     * Retorna o identificador único da entidade.
     * @return o ID (nunca deve ser nulo em implementações bem formadas)
     */
    ID getId();                           // Método abstrato principal do contrato.

    /**
     * Compara, de forma null-safe, a identidade desta instância com a de outra.
     * Implementações não precisam sobrescrever; a comparação usa {@link Objects#equals(Object, Object)}.
     *
     * @param other outra instância Identificavel (pode ser nula)
     * @return {@code true} se ambos forem não nulos e seus IDs forem iguais; caso contrário, {@code false}.
     */
    default boolean hasSameId(Identificavel<ID> other) {
        // Se 'other' for nulo, retorna false. Caso contrário, compara IDs com Objects.equals (null-safe).
        return other != null && Objects.equals(this.getId(), other.getId());
    }

    /**
     * Utilitário estático para comparar identidades de duas instâncias (null-safe).
     * Útil em utilitários/validadores sem precisar instanciar nada.
     *
     * @param a primeira instância (pode ser nula)
     * @param b segunda instância (pode ser nula)
     * @return {@code true} se ambas não forem nulas e seus IDs forem iguais; caso contrário, {@code false}.
     */
    static boolean equalsById(Identificavel<?> a, Identificavel<?> b) {
        // Retorna true somente se 'a' e 'b' não forem nulos e seus IDs forem iguais (Objects.equals é null-safe).
        return a != null && b != null && Objects.equals(a.getId(), b.getId());
    }
}