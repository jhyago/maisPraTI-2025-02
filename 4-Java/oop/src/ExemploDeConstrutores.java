public class ExemploDeConstrutores {

    static {
        System.out.println("Inicializando");
    }

    public ExemploDeConstrutores() {
        System.out.println("Construtor Padrão");
    }

    private int idade;
    private String nome;

    public ExemploDeConstrutores(int idade, String nome) {
        this.idade = idade;
        this.nome = nome;
        System.out.println("Construtor parametrizado: Nome " + nome + " idade " + idade + " idade");
    }

    public ExemploDeConstrutores(String nome) {
        this(0, nome );
        System.out.println();
    }

    public ExemploDeConstrutores(ExemploDeConstrutores outro) {
        this.nome = outro.nome;
        this.idade = outro.idade;
        System.out.println("Construtor Cópia: Nome " + nome + " idade " + idade + " idade");
    }

    private ExemploDeConstrutores(boolean flag) {
        System.out.println("Construtor flag: " + flag);
    }

    public static ExemploDeConstrutores criarInstancia() {
        return new ExemploDeConstrutores(true);
    }

    public static void main(String[] args) {
        ExemploDeConstrutores exemplo1 = new ExemploDeConstrutores();
        ExemploDeConstrutores exemplo2 = new ExemploDeConstrutores(20, "Joelinton");
        ExemploDeConstrutores exemplo3 = new ExemploDeConstrutores("Miguel");
        ExemploDeConstrutores exemplo4 = new ExemploDeConstrutores(exemplo2);
    }
}
