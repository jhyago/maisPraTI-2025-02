package ExercicioPraticaStreams;                      // Pacote do exercício.

import java.util.Objects;                             // Para validações simples (null-safety).

/**
 * Classe simples de domínio: Funcionario.
 * Atributos: nome, salario, cargo e idade.
 * Inclui: construtor, getters e setters (como pede o enunciado) e validações básicas.
 */
public class Funcionario {

    // --- Atributos privados (encapsulados) ---
    private String nome;                              // Nome do funcionário (não nulo / não vazio).
    private double salario;                           // Salário (>= 0). Em produção, prefira BigDecimal.
    private String cargo;                             // Cargo (não nulo / não vazio).
    private int idade;                                // Idade (>= 0).

    // --- Construtor principal com validações simples ---
    public Funcionario(String nome, double salario, String cargo, int idade) {
        setNome(nome);                                // Usa setters para reaproveitar validações.
        setSalario(salario);
        setCargo(cargo);
        setIdade(idade);
    }

    // --- Getters (acesso de leitura) ---
    public String getNome() { return nome; }
    public double getSalario() { return salario; }
    public String getCargo() { return cargo; }
    public int getIdade() { return idade; }

    // --- Setters (com validações, conforme enunciado pede setters) ---
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou em branco.");
        }
        this.nome = nome.trim();
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("Salário deve ser >= 0.");
        }
        this.salario = salario;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("Cargo não pode ser nulo ou em branco.");
        }
        this.cargo = cargo.trim();
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade deve ser >= 0.");
        }
        this.idade = idade;
    }

    @Override
    public String toString() {
        // Útil para depuração; não é usado diretamente no enunciado.
        return "Funcionario{nome='" + nome + "', salario=" + salario +
               ", cargo='" + cargo + "', idade=" + idade + "}";
    }
}