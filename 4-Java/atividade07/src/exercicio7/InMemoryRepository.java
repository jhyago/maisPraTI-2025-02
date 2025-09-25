package exercicio7;                                                  // Pacote onde a classe está.

import java.util.*;                                                 // Importa coleções e Optional.
import java.util.concurrent.ConcurrentHashMap;                      // Mapa thread-safe para cenários concorrentes.

/**
 * Implementação em memória de um repositório genérico.
 *
 * @param <T>  tipo da entidade que implementa Identificavel<ID>
 * @param <ID> tipo do identificador da entidade
 *
 * Características:
 * - Armazena entidades em um Map<ID, T>.
 * - {@link #salvar(Object)} faz inclusão ou atualização (put sobrescreve valores com o mesmo ID).
 * - {@link #getId(Object)} busca por ID e retorna Optional<T>.
 * - {@link #getAll()} devolve um snapshot imutável das entidades no momento da chamada.
 *
 * Observação: Por usar um mapa em memória, os dados se perdem ao encerrar a aplicação.
 */
public class InMemoryRepository<T extends Identificavel<ID>, ID>
        implements IRepository<T, ID> {                              // Implementa o contrato do repositório.

    // Mapa subjacente: ConcurrentHashMap oferece segurança básica em cenários multi-thread.
    private final Map<ID, T> repositorio = new ConcurrentHashMap<>();

    /**
     * Salva a entidade no repositório.
     * Se já existir uma entidade com o mesmo ID, ela será sobrescrita (sem erro).
     *
     * @param entidade entidade a ser salva (não pode ser nula e deve ter ID não nulo)
     * @throws NullPointerException se entidade ou seu ID forem nulos
     */
    @Override
    public void salvar(T entidade) {
        Objects.requireNonNull(entidade, "entidade não pode ser nula"); // Valida a referência.
        ID id = Objects.requireNonNull(entidade.getId(), "ID da entidade não pode ser nulo"); // Valida o ID.
        repositorio.put(id, entidade);                                  // Inclui/atualiza no mapa (put é upsert).
    }

    /**
     * Recupera uma entidade pelo ID.
     *
     * @param id identificador da entidade (não pode ser nulo)
     * @return Optional contendo a entidade, ou Optional.empty() se não encontrada
     * @throws NullPointerException se o id for nulo
     */
    @Override
    public Optional<T> getId(ID id) {
        Objects.requireNonNull(id, "id não pode ser nulo");             // Evita consulta com ID nulo.
        return Optional.ofNullable(repositorio.get(id));                 // Retorna Optional com a entidade (ou vazio).
    }

    /**
     * Retorna todas as entidades do repositório em um snapshot imutável.
     * Modificações futuras no repositório não afetam a lista retornada.
     *
     * @return lista imutável com as entidades atuais
     */
    @Override
    public List<T> getAll() {
        // Cria uma cópia para snapshot e devolve como lista imutável (protege o estado interno).
        return Collections.unmodifiableList(new ArrayList<>(repositorio.values()));
        // Em Java 10+, poderia usar: return List.copyOf(repositorio.values());
    }

    // --- Métodos utilitários opcionais (não fazem parte do contrato da interface) ---
    // Se a sua interface IRepository tiver esses métodos, você pode promovê-los ao @Override.

    /** Remove uma entidade pelo ID (retorna true se algo foi removido). */
    public boolean removerPorId(ID id) {
        Objects.requireNonNull(id, "id não pode ser nulo");
        return repositorio.remove(id) != null;
    }

    /** Verifica se existe entidade com o ID informado. */
    public boolean existePorId(ID id) {
        Objects.requireNonNull(id, "id não pode ser nulo");
        return repositorio.containsKey(id);
    }

    /** Quantidade de entidades armazenadas. */
    public int tamanho() {
        return repositorio.size();
    }

    /** Remove todas as entidades (útil para testes). */
    public void limpar() {
        repositorio.clear();
    }
}