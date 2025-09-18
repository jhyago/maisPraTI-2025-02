package exercicio7;

public class Produto implements Identificavel<Integer> {
    private final Integer id;
    private final String nome;
    private final double preco;

    public Produto(Integer id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }
}
