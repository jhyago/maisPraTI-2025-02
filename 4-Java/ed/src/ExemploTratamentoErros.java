import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * ============================================================
 * TRATAMENTO DE ERROS E EXCEÇÕES EM JAVA — CONCEITOS RÁPIDOS
 * ============================================================
 * Exceção (Exception): evento anômalo que interrompe o fluxo normal do programa.
 *
 * Tipos:
 * - Checked (verificadas): o compilador exige tratar ou declarar (ex.: IOException).
 * - Unchecked (não verificadas): herdam de RuntimeException; podem ocorrer em tempo de execução
 *   (ex.: NullPointerException, ArrayIndexOutOfBoundsException).
 *
 * Blocos:
 * - try: onde o código arriscado é executado.
 * - catch: como lidar com a exceção.
 * - finally: sempre executa (ocorrendo exceção ou não); ideal para liberar recursos.
 *
 * Boas práticas:
 * - Mensagens claras e úteis.
 * - Preferir try-with-resources para fechar arquivos/streams automaticamente.
 * - Não engolir exceções silenciosamente.
 * - Lançar exceções específicas (customizadas) quando fizer sentido no domínio.
 */

public class ExemploTratamentoErros {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // Exemplo 1 — Capturando uma exceção não verificada (unchecked)
        // ArrayIndexOutOfBoundsException: acesso fora dos limites do array
        // ------------------------------------------------------------
        System.out.println("Exemplo 1: ArrayIndexOutOfBoundsException");
        try {
            int[] numeros = {1, 2, 3};
            System.out.println(numeros[5]); // índice inválido (vai lançar exceção)
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Acesso inválido ao array: " + e.getMessage());
        } finally {
            System.out.println("finally (Exemplo 1): sempre executa.");
        }

        // ------------------------------------------------------------
        // Exemplo 2 — Exceção personalizada (unchecked) e multi-catch
        // MyException herda de RuntimeException → unchecked
        // ------------------------------------------------------------
        System.out.println("\nExemplo 2: Exceção personalizada e multi-catch");
        try {
            validaIdadeParaCadastro(-3);     // dispara nossa MyException
        } catch (MyException | IllegalArgumentException e) {
            // multi-catch: trata mais de um tipo com o mesmo bloco
            System.out.println("Falha de validação: " + e.getMessage());
        }

        // ------------------------------------------------------------
        // Exemplo 3 — Checked exception: leitura de arquivo
        // - Usamos try-with-resources, que fecha o arquivo automaticamente.
        // - O método lerArquivoRetornandoPrimeiraLinha lança IOException (checked).
        // ------------------------------------------------------------
        System.out.println("\nExemplo 3: Leitura de arquivo (IOException — checked)");
        try {
            String linha1 = lerArquivoRetornandoPrimeiraLinha("arquivo.txt");
            System.out.println("Primeira linha: " + linha1);
        } catch (IOException e) {
            // Obrigatório tratar ou propagar (checked)
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        // ------------------------------------------------------------
        // Exemplo 4 — finally em recurso manual (apenas ilustrativo)
        // (prefira sempre try-with-resources do Exemplo 3)
        // ------------------------------------------------------------
        System.out.println("\nExemplo 4: finally para liberar recurso manualmente");
        FileReader fr = null;
        try {
            fr = new FileReader("arquivo.txt"); // pode lançar IOException (checked)
            // ... processaria aqui ...
            System.out.println("Arquivo aberto com sucesso (Exemplo 4).");
        } catch (IOException e) {
            System.out.println("Falha ao abrir arquivo (Exemplo 4): " + e.getMessage());
        } finally {
            // Liberar o recurso manualmente (se foi aberto)
            if (fr != null) {
                try { fr.close(); } catch (IOException e) {
                    System.out.println("Falha ao fechar arquivo (Exemplo 4): " + e.getMessage());
                }
            }
            System.out.println("finally (Exemplo 4): limpeza concluída.");
        }

        System.out.println("\nFim dos exemplos.");
    }

    // ============================================================
    // Exemplo de método que lança exceção personalizada (unchecked)
    // ============================================================
    private static void validaIdadeParaCadastro(int idade) {
        if (idade < 0) {
            // Lança a nossa exceção de domínio com mensagem clara
            throw new MyException("Idade não pode ser negativa: " + idade);
        }
        if (idade < 18) {
            // Outro tipo comum do JDK (também unchecked)
            throw new IllegalArgumentException("Cadastro permitido apenas para maiores de idade.");
        }
        System.out.println("Idade válida para cadastro: " + idade);
    }

    // ============================================================
    // Exemplo de método que LÊ ARQUIVO e lança IOException (checked)
    // Try-with-resources garante fechamento automático do arquivo.
    // ============================================================
    public static String lerArquivoRetornandoPrimeiraLinha(String caminho) throws IOException {
        try (FileReader arquivo = new FileReader(caminho);
             BufferedReader br = new BufferedReader(arquivo)) {
            String linha = br.readLine();
            if (linha == null) {
                throw new IOException("Arquivo vazio: " + caminho);
            }
            return linha;
        }
    }

    // ============================================================
    // Exceção personalizada (unchecked)
    // - Use quando quiser comunicar um erro específico do seu domínio.
    // - Poderia herdar de Exception para ser checked; aqui herdamos de RuntimeException.
    // ============================================================
    static class MyException extends RuntimeException {
        public MyException(String mensagem) { super(mensagem); }
        public MyException(String mensagem, Throwable causa) { super(mensagem, causa); }
    }
}