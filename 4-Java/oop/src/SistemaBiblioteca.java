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
