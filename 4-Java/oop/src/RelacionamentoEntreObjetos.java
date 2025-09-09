import java.util.ArrayList;
import java.util.List;

public class RelacionamentoEntreObjetos {
    static class Motorista {
        private String nome;

        public Motorista(String nome) {
            this.nome = nome;
        }

        public void dirigir(Carro carro) {
            System.out.println("Motorista " + nome + " dirigindo o " + carro.getModelo());
        }
    }

    static class Carro {
        private String modelo;

        public Carro(String modelo) {
            this.modelo = modelo;
        }

        public String getModelo() {
            return this.modelo;
        }
    }

    static class Curso {
        private String nome;
        private List<Aluno> alunos;

        public Curso(String nome) {
            this.nome = nome;
            this.alunos = new ArrayList<>();
        }

        public void adicionarAluno(Aluno aluno) {
            this.alunos.add(aluno);
            System.out.println(aluno.getNome() + " foi matriculado no curso de " + nome);
        }
    }

    static class Aluno {
        private String nome;

        public Aluno(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return this.nome;
        }
    }

    static class Computador {
        private CPU processador;

        public Computador(String modeloCPU) {
            this.processador = new CPU(modeloCPU);
        }

        public void ligar() {
            System.out.println("Ligando o computador com a CPU " + processador.getModelo());
        }
    }

    private static class CPU {
        private String modelo;

        public CPU(String modelo) {
            this.modelo = modelo;
        }

        public String getModelo() {
            return this.modelo;
        }
    }

    public static void main(String[] args) {
        Motorista motorista1 = new Motorista("Michelle");
        Carro carro1 = new Carro("Fusca");
        motorista1.dirigir(carro1);

        Curso curso1 = new Curso("Crochê");
        Aluno aluno1 = new Aluno("Paulo");
        curso1.adicionarAluno(aluno1);

        Computador computador1 = new Computador("Intel Core I7");
        computador1.ligar();
    }
}

//Implemente um sistema de gerenciamento de veículos e seus proprietários. O sistema deve utilizar os conceitos de associação, agregação, composição, herança e polimorfismo.

//O sistema deve ter uma classe abstrata chamada Veiculo, com atributos básicos como marca, modelo e ano, e um método para exibir essas informações. Além disso, o método tipoDeVeiculo() deverá ser implementado nas subclasses Carro e Moto, que devem herdar de Veiculo. Cada tipo de veículo deve exibir seu tipo específico.

//Crie uma classe Proprietario, que possui informações como nome e endereço, e que pode ter múltiplos veículos associados a ele. A relação entre proprietário e veículos deve ser uma associação.

//Implemente uma classe Servico, que tem informações sobre a descrição e o preço dos serviços realizados, e uma classe Oficina, que é responsável por adicionar e listar serviços realizados.

//Entre as classes, estabeleça as relações necessárias, como agregação entre oficina e serviços e associação entre proprietário e veículos. Utilize herança e polimorfismo para organizar e expandir o sistema de forma eficiente.
