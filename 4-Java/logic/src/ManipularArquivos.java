import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ManipularArquivos {
    public static void main(String[] args) throws IOException {
        Path arquivo = Paths.get("arquivo.txt");
//
//        try {
//            if(!Files.exists(arquivo)) {
//                Files.createFile(arquivo);
//            }
//
//            String conteudo = "Olá, o Grêmio ganhou!";
//            Files.write(arquivo, conteudo.getBytes());
//
//            System.out.println("Arquivo criado e escrito com sucesso!");
//        } catch (IOException error) {
//            error.printStackTrace();
//        }

//        try {
//            List<String> linhas = Files.readAllLines(arquivo);
//            for (String linha : linhas) {
//                System.out.println(linha);
//            }
//        } catch (IOException error) {
//            error.printStackTrace();
//        }

//        String conteudo = Files.readString(arquivo);
//        System.out.println(conteudo);

//        Path diretorio = Paths.get("Olimpico");
//
//        try {
//            Files.list(diretorio).forEach(path -> {
//                System.out.println(path);
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Path arquivo = Paths.get("arquivo.txt");
//
//        Files.delete(arquivo);

//        Path origem = Paths.get("arquivo.txt");
//        Path destino = Paths.get("arquivoAlterado.txt");
//
//        Files.move(origem, destino);

//Implemente, em Java 21, um utilitário de linha de comando chamado CopyInfo que recebe dois caminhos: origem e destino. Se a origem não existir ou não for um arquivo regular, mostre uma mensagem de erro amigável. Se o destino for um diretório, salve o arquivo com o mesmo nome da origem dentro dele; se o arquivo de destino já existir, só permita sobrescrever quando a opção --force for passada (caso contrário, encerre informando que o arquivo já existe).
    }
}
