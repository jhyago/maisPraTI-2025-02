package exercicio1e2; // Define o pacote do projeto (organização de classes).

import java.math.BigDecimal;             // BigDecimal é indicado para valores monetários (precisão exata).
import java.math.RoundingMode;          // Define como será feito o arredondamento (p.ex. HALF_UP).

class DescontoInvalidoException extends IllegalArgumentException { // Exceção específica para descontos inválidos.
    public DescontoInvalidoException(String mensagem) { // Construtor que recebe a mensagem de erro.
        super(mensagem);                                 // Encaminha a mensagem para a superclasse.
    }
}

public class Produto {                // Classe pública Produto.
    private String nome;              // Nome do produto.
    private BigDecimal preco;         // Preço em BigDecimal (evita erros de ponto flutuante).
    private int quantidade;           // Quantidade em estoque.

    // Construtor principal usando BigDecimal para preço.
    public Produto(String nome, BigDecimal preco, int quantidade) {
        setNome(nome);                // Valida e define o nome.
        setPreco(preco);              // Valida e define o preço (BigDecimal).
        setQuantidade(quantidade);    // Valida e define a quantidade.
    }

    // Construtor de conveniência que aceita double e converte para BigDecimal de forma segura.
    public Produto(String nome, double preco, int quantidade) {
        this(nome, BigDecimal.valueOf(preco), quantidade); // Converte double -> BigDecimal com valor exato possível.
    }

    public String getNome() {         // Getter do nome.
        return nome;                  // Retorna o nome atual.
    }

    public void setNome(String nome) {                     // Setter do nome com validação.
        if (nome == null || nome.isEmpty()) {              // Usa '||' (curto-circuito) para evitar NPE em isEmpty().
            throw new IllegalArgumentException("O nome não pode ser vazio ou nulo."); // Erro se inválido.
        }
        this.nome = nome;                                   // Atribui o valor validado.
    }

    public BigDecimal getPreco() {    // Getter do preço (BigDecimal).
        return preco;                 // Retorna o preço atual.
    }

    public void setPreco(BigDecimal preco) {                // Setter do preço com validação.
        if (preco == null) {                                // Não pode ser nulo.
            throw new IllegalArgumentException("O preço não pode ser nulo.");
        }
        if (preco.compareTo(BigDecimal.ZERO) < 0) {         // compareTo < 0 => negativo.
            throw new IllegalArgumentException("O preço deve ser maior ou igual a zero.");
        }
        // Normaliza para 2 casas decimais com arredondamento padrão para dinheiro.
        this.preco = preco.setScale(2, RoundingMode.HALF_UP);
    }

    public int getQuantidade() {       // Getter da quantidade.
        return quantidade;             // Retorna a quantidade atual.
    }

    public void setQuantidade(int quantidade) {             // Setter da quantidade com validação.
        if (quantidade < 0) {                               // Não permite quantidade negativa.
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a zero.");
        }
        this.quantidade = quantidade;                       // Atribui o valor validado.
    }

    public void aplicarDesconto(double porcentagem) {       // Aplica um desconto percentual ao preço.
        // Validação: desconto deve ser > 0 e <= 50 (evita desconto 0% sem efeito, e limita exageros).
        if (porcentagem <= 0 || porcentagem > 50) {
            throw new DescontoInvalidoException("A porcentagem do desconto deve ser maior que 0% e no máximo 50%.");
        }

        // Converte porcentagem double para BigDecimal com precisão adequada.
        BigDecimal perc = BigDecimal.valueOf(porcentagem);

        // Calcula fator de desconto: (porcentagem / 100).
        BigDecimal fator = perc.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
        // Calcula valor do desconto: preco * fator, arredondado a 2 casas.
        BigDecimal valorDesconto = this.preco.multiply(fator).setScale(2, RoundingMode.HALF_UP);
        // Aplica desconto ao preço atual, garantindo escala 2.
        this.preco = this.preco.subtract(valorDesconto).setScale(2, RoundingMode.HALF_UP);

        // Opcional: evitar que, por algum motivo de arredondamento extremo, o preço fique negativo.
        if (this.preco.compareTo(BigDecimal.ZERO) < 0) {
            this.preco = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void exibirInformacoes() {  // Imprime informações do produto.
        System.out.println("Nome: " + this.nome);                       // Nome.
        System.out.println("Preço: " + this.preco.toPlainString());     // Preço formatado sem notação científica.
        System.out.println("Quantidade: " + this.quantidade);           // Quantidade.
    }

    public static void main(String[] args) { // Método de teste.
        // Exemplos comentados de criação e validação:
        // try {
        //     Produto produto = new Produto("Creme de Rosto", new BigDecimal("1200.00"), 3);
        //     produto.exibirInformacoes();
        // } catch (IllegalArgumentException ex) {
        //     ex.printStackTrace();
        // }
        //
        // try {
        //     Produto produtoInvalido = new Produto("", 600, 1); // Nome vazio -> lança exceção.
        // } catch (IllegalArgumentException ex) {
        //     System.out.println(ex.getMessage());
        // }

        try {
            // Cria um produto válido (usando double no construtor de conveniência).
            Produto produto = new Produto("Skate", 12.99, 23);
            System.out.println("Antes do desconto:");
            produto.exibirInformacoes(); // Mostra o estado inicial.

            produto.aplicarDesconto(50); // Aplica 50% de desconto (válido).
            System.out.println("Depois do desconto:"); // Mensagem corrigida (antes dizia "Quantidade...").
            produto.exibirInformacoes();  // Mostra o efeito do desconto no preço.
        } catch (DescontoInvalidoException e) { // Captura desconto fora do intervalo permitido.
            System.out.println(e.getMessage());  // Exibe a mensagem amigável.
        }
    }
}