public class ExemplosDeHeranca {

    static class Animal {
        void fazerSom() {
            System.out.println("Animal fazendo som");
        }
    }

    static class Cachorro extends Animal {
        @Override
        void fazerSom() {
            System.out.println("Au au au au au au au");
        }
    }

    static class Mamifero extends Animal {
        void amamentar() {
            System.out.println("Mamífero está amamentando");
        }
    }

    static class Gato extends Mamifero {
        @Override
        void fazerSom() {
            System.out.println("Miau miau miau miau");
        }

        void fazerSom(String som) {
            System.out.println(som);
        }
    }

    static final class Peixe extends Animal {
        @Override
        void fazerSom() {
            System.out.println("Peixe está fazendo bolhas");
        }
    }

    public static void main(String[] args) {
        System.out.println("==== Herança Simples ====");
        Cachorro cachorro = new Cachorro();
        cachorro.fazerSom();

        System.out.println("==== Herança Multinível ====");
        Gato gato = new Gato();
        gato.fazerSom();
        gato.amamentar();

        gato.fazerSom("Gato Mia e Cachorro Late");
    }
}

//Enunciado
//
//Implemente um pequeno catálogo de formas geométricas.
//Cada forma sabe calcular área e perímetro. Você terá:
//
//Uma classe abstrata Forma.
//
//Subclasses: Circulo, Retangulo e Quadrado (sendo Quadrado um tipo especial de Retangulo).
//
//Faça funções utilitárias que:
//
//Somem a área total de uma lista de formas.
//
//Imprimam um resumo polimórfico (mesmo método chamado em objetos diferentes).
//
//(Opcional) Usem switch/instanceof para tratar detalhes específicos.
