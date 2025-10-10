import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;     // Charset explícito evita problemas de acentuação
import java.nio.file.*;                      // Path, Paths, Files, StandardOpenOption
import java.util.List;

/*
 * TRABALHANDO COM ARQUIVOS (NIO.2) — EXEMPLOS DIDÁTICOS E CORRIGIDOS
 *
 * O objetivo é mostrar operações essenciais com a API NIO.2 do Java:
 * - Criar arquivo, escrever (sobrescrever e append), ler (linha a linha e arquivo inteiro)
 * - Listar diretório, mover/renomear, copiar, deletar
 * - Usar try-with-resources para fechar recursos automaticamente
 * - Usar Charset explícito (UTF-8) e opções de abertura (StandardOpenOption)
 *
 * OBSERVAÇÕES SOBRE O CÓDIGO ORIGINAL (o seu):
 * - Abria o mesmo arquivo para leitura e escrita ao mesmo tempo: perigoso (risco de bloqueio/corrupção).
 * - No while de leitura não atualizava a variável `linha` → loop infinito. É obrigatório reler no final do laço.
 * - Prefira try-with-resources (menos vazamento de recurso).
 */
public class TrabalhandoComArquivos {
    public static void main(String[] args) {
        // --------------------------------------------------------------------
        // 1) Criar arquivo se não existir e escrever conteúdo (sobrescrever)
        // --------------------------------------------------------------------
        Path arquivo = Paths.get("teste.txt");
        try {
            if (!Files.exists(arquivo)) {
                Files.createFile(arquivo); // cria o arquivo vazio
            }
            String conteudo = "Olá, eu sou o conteúdo do arquivo de teste\n";
            // write com charset e opção de TRUNCATE_EXISTING para sobrescrever por completo
            Files.write(
                arquivo,
                conteudo.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.TRUNCATE_EXISTING
            );
            System.out.println("Arquivo criado/sobrescrito com sucesso: " + arquivo.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Falha ao criar/escrever: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 2) Acrescentar (append) texto ao final do arquivo
        // --------------------------------------------------------------------
        try {
            String extra = "Linha adicional (append)\n";
            Files.write(
                arquivo,
                extra.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.APPEND
            );
            System.out.println("Conteúdo acrescentado com APPEND.");
        } catch (IOException e) {
            System.out.println("Falha ao fazer append: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 3) Ler arquivo — duas maneiras: todas as linhas e arquivo inteiro
        // --------------------------------------------------------------------
        try {
            // a) Todas as linhas (lista de strings)
            List<String> linhas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
            System.out.println("\nLendo com readAllLines:");
            for (String l : linhas) {
                System.out.println(l);
            }

            // b) Arquivo inteiro como String (para arquivos pequenos)
            String tudo = Files.readString(arquivo, StandardCharsets.UTF_8);
            System.out.println("\nLendo com readString:");
            System.out.println(tudo);
        } catch (IOException e) {
            System.out.println("Falha ao ler arquivo: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 4) Listar conteúdo de um diretório
        // --------------------------------------------------------------------
        Path diretorio = Paths.get("diretorioExemplo");
        try {
            if (Files.notExists(diretorio)) {
                Files.createDirectories(diretorio);
            }
            // Cria alguns arquivos de exemplo
            Files.writeString(diretorio.resolve("a.txt"), "A", StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.writeString(diretorio.resolve("b.txt"), "B", StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("\nListando arquivos em: " + diretorio.toAbsolutePath());
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(diretorio)) {
                for (Path p : stream) {
                    System.out.println("- " + p.getFileName());
                }
            }
        } catch (IOException e) {
            System.out.println("Falha ao listar/criar diretório: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 5) Mover/renomear arquivo (move) e copiar (copy)
        // --------------------------------------------------------------------
        Path origem = Paths.get("Arquivo.txt");
        Path destino = Paths.get("mudeiNome.txt");
        try {
            // Garante um arquivo de origem para o exemplo
            Files.writeString(origem, "Arquivo de origem.\n", StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // Move/renomeia (se já existir o destino, REPLACE_EXISTING substitui)
            Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nArquivo movido/renomeado para: " + destino.toAbsolutePath());

            // Copia para outro nome
            Path copia = Paths.get("copia_mudeiNome.txt");
            Files.copy(destino, copia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo copiado para: " + copia.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Falha ao mover/copiar: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 6) Leitura linha a linha com BufferedReader (try-with-resources)
        //    CORREÇÃO: atualizar 'linha' no final do laço para evitar loop infinito.
        // --------------------------------------------------------------------
        Path pathLeitura = Paths.get("mudeiNome.txt");
        System.out.println("\nLendo linha a linha com BufferedReader:");
        try (BufferedReader br = Files.newBufferedReader(pathLeitura, StandardCharsets.UTF_8)) {
            String linha = br.readLine();    // lê a primeira linha
            while (linha != null) {          // enquanto não chegar ao fim do arquivo
                System.out.println(linha);   // processa a linha
                linha = br.readLine();       // LÊ A PRÓXIMA LINHA (ESSENCIAL!)
            }
        } catch (IOException e) {
            System.out.println("Falha na leitura linha a linha: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 7) Escrita com BufferedWriter (em arquivo separado para não conflitar com leitura)
        // --------------------------------------------------------------------
        Path pathEscrita = Paths.get("saida.txt");
        System.out.println("\nEscrevendo com BufferedWriter em: " + pathEscrita.toAbsolutePath());
        try (BufferedWriter bw = Files.newBufferedWriter(
                pathEscrita,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,           // cria se não existir
                StandardOpenOption.TRUNCATE_EXISTING // limpa conteúdo anterior
        )) {
            bw.write("Linha 1\n");
            bw.write("Linha 2\n");
            bw.write("Linha 3\n");
        } catch (IOException e) {
            System.out.println("Falha na escrita com BufferedWriter: " + e.getMessage());
        }

        // --------------------------------------------------------------------
        // 8) Exclusão segura (opcional): deleteIfExists não lança exceção se não existir
        // --------------------------------------------------------------------
        try {
            boolean deletado = Files.deleteIfExists(Paths.get("copia_mudeiNome.txt"));
            System.out.println("\nArquivo copia_mudeiNome.txt deletado? " + deletado);
        } catch (IOException e) {
            System.out.println("Falha ao deletar: " + e.getMessage());
        }

        System.out.println("\nFim dos exemplos de arquivos (NIO.2).");
    }
}