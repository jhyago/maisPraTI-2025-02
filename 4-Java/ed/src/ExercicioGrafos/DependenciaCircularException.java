package ExercicioGrafos;

public class DependenciaCircularException extends Exception {
    public DependenciaCircularException(String mensagem) {
        super(mensagem);
    }
}
