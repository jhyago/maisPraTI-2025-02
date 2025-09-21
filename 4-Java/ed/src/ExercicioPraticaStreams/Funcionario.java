package ExercicioPraticaStreams;

public class Funcionario {
    private String nome;
    private double salario;
    private String cargo;
    private int idade;

    public Funcionario(String nome, double salario, String cargo, int idade) {
        this.nome = nome;
        this.salario = salario;
        this.cargo = cargo;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
