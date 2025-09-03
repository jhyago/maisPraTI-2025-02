public class Pessoa {
    private String nome;
    int idade;
    String sexo;

    public Pessoa(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public void apresentar() {
        System.out.println("Meu nome é " + nome + " tenho " + idade + " anos e sou do sexo " + sexo);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

class Main {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa("Pedro", 20, "masculino");
        p1.apresentar();
        System.out.println(p1.getNome());

        p1.setNome("Patinhas");
        p1.apresentar();

        Pessoa p2 = new Pessoa("Maísa", 21, "feminino");
        p2.apresentar();
    }
}
