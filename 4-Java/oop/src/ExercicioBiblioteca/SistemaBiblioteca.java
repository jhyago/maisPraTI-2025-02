package ExercicioBiblioteca;

//Crie um sistema simples de biblioteca utilizando conceitos básicos de classes em Java.
//O sistema deve ser composto por uma classe que represente um livro e uma classe para a biblioteca.

//A classe ExercicioBiblioteca.Livro deve ter os atributos título, autor, ano de publicação e disponibilidade (se o livro está disponível ou emprestado).
//Além disso, ela deve permitir a alteração da disponibilidade e a exibição das informações do livro.

//A classe ExercicioBiblioteca.Biblioteca deve conter um conjunto de livros e permitir adicionar novos livros, além de listar todos os livros cadastrados.
//O sistema deve possibilitar também a alteração da disponibilidade de um livro.

//Implemente uma classe principal para testar a funcionalidade do sistema, criando instâncias de livros e de uma biblioteca, e alterando as disponibilidades dos livros.

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);
        Livro livro2 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro livro3 = new Livro("A Revolução dos Bichos", "George Orwell", 1945);
        Livro livro4 = new Livro("Crime e Castigo", "Fiódor Dostoiévski", 1866);
        Livro livro5 = new Livro("A República", "Platão", 380);
        Livro livro6 = new Livro("Os Miseráveis", "Victor Hugo", 1862);
        Livro livro7 = new Livro("Dom Quixote", "Miguel de Cervantes", 1605);
        Livro livro8 = new Livro("Vidas Secas", "Graciliano Ramos", 1938);
        Livro livro9 = new Livro("O Hobbit", "J. R. R. Tolkien", 1937);
        Livro livro10 = new Livro("Em Busca de Sentido", "Viktor Frankl", 1946);

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
        biblioteca.adicionarLivro(livro4);
        biblioteca.adicionarLivro(livro5);
        biblioteca.adicionarLivro(livro6);
        biblioteca.adicionarLivro(livro7);
        biblioteca.adicionarLivro(livro8);
        biblioteca.adicionarLivro(livro9);
        biblioteca.adicionarLivro(livro10);

        biblioteca.alterarDisponibilidade("O Hobbit", false);
        biblioteca.listarLivros();
    }
}
