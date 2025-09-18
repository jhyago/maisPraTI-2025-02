package exercicio7;

public class Funcionario implements Identificavel<String> {
    private final String id;
    private final String nome;
    private final double cargo;

    public Funcionario(String id, String nome, double cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public double getCargo() {
        return this.cargo;
    }
}
