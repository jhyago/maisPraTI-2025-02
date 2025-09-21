package exercicio7;

import java.util.List;
import java.util.Optional;

public interface IRepository<T extends Identificavel<ID>, ID> {
    void salvar(T entidade);
    Optional<T> getId(ID id);
    List<T> getAll();
//    void remover(ID id);
}
