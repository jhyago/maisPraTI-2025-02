package exercicio7;                                      // Pacote onde a interface está.

import java.util.List;                                   // Lista de entidades para retornos em massa.
import java.util.NoSuchElementException;                 // Exceção para consultas obrigatórias sem resultado.
import java.util.Objects;                                // Utilitário para validações null-safe.
import java.util.Optional;                               // Retorno sem nulos para busca por ID.

/**
 * Contrato genérico para repositórios em memória ou persistentes.
 *
 * @param <T>  tipo da entidade, que deve expor um identificador via {@link Identificavel}
 * @param <ID> tipo do identificador (ex.: String, UUID, Long)
 *
 * Regras gerais:
 * - {@link #salvar(Object)} realiza inclusão ou atualização (upsert).
 * - {@link #getId(Object)} retorna {@link Optional} para evitar retorno nulo.
 * - {@link #getAll()} retorna um snapshot atual das entidades (de preferência imutável).
 *
 * Observação: Métodos utilitários {@code default} são oferecidos para tarefas comuns,
 * sem obrigar implementações a reescreverem lógica repetitiva.
 */
public interface IRepository<T extends Identificavel<ID>, ID> {

    /**
     * Salva (inclui/atualiza) a entidade.
     * Implementações devem rejeitar entidade nula e ID nulo.
     *
     * @param entidade entidade a ser salva (não pode ser nula; seu ID também não pode ser nulo)
     */
    void salvar(T entidade);                              // Método principal de escrita (upsert).

    /**
     * Busca uma entidade pelo seu identificador.
     *
     * @param id identificador da entidade (não deve ser nulo)
     * @return Optional com a entidade encontrada; ou Optional.empty() se não houver
     */
    Optional<T> getId(ID id);                             // Busca por ID, sem retorno nulo.

    /**
     * Retorna todas as entidades.
     * Recomenda-se devolver uma coleção imutável (ou cópia) para proteger o estado interno.
     *
     * @return lista (de preferência imutável) com as entidades atuais
     */
    List<T> getAll();                                     // Leitura em massa (snapshot).

    // ------------------------------------------------------------------------
    // Métodos utilitários DEFAULT (opcionais) — já vêm prontos para uso.
    // São implementados em termos dos três métodos acima, então não quebram nada.
    // ------------------------------------------------------------------------

    /**
     * Verifica se existe uma entidade mapeada para o ID informado.
     *
     * @param id identificador (não pode ser nulo)
     * @return true se existir; false caso contrário
     */
    default boolean existePorId(ID id) {
        Objects.requireNonNull(id, "id não pode ser nulo"); // Validação simples.
        return getId(id).isPresent();                       // Reaproveita getId.
    }

    /**
     * Retorna a quantidade de entidades.
     *
     * @return número de entidades atualmente armazenadas
     */
    default long contar() {
        return getAll().size();                             // Conta via snapshot.
    }

    /**
     * Salva um conjunto de entidades (conveniência).
     *
     * @param entidades coleção/iterável de entidades (não pode ser nulo; elementos não podem ser nulos)
     */
    default void salvarTodos(Iterable<? extends T> entidades) {
        Objects.requireNonNull(entidades, "entidades não pode ser nulo");
        for (T e : entidades) {
            Objects.requireNonNull(e, "entidade da coleção não pode ser nula");
            salvar(e);                                      // Reutiliza o upsert individual.
        }
    }

    /**
     * Obtém uma entidade por ID e lança exceção se não existir (útil em fluxos que exigem a presença).
     *
     * @param id identificador (não pode ser nulo)
     * @return a entidade encontrada
     * @throws NoSuchElementException se não existir entidade para o ID informado
     */
    default T getOrThrow(ID id) {
        Objects.requireNonNull(id, "id não pode ser nulo");
        return getId(id).orElseThrow(() ->
                new NoSuchElementException("Entidade não encontrada para id: " + id));
    }

//    // Se desejar padronizar remoção no contrato, descomente e implemente nas classes concretas:
//    /**
//     * Remove a entidade pelo ID.
//     * @param id identificador da entidade (não pode ser nulo)
//     * @return true se removeu; false se não havia entidade para o ID
//     */
//    boolean remover(ID id);
}