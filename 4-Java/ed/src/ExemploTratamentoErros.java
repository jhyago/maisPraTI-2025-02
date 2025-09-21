import java.io.FileReader;
import java.io.IOException;

public class ExemploTratamentoErros {
    public static void main(String[] args) {
//        try {
//            int [] numeros = {1, 2, 3};
//            System.out.println(numeros[5]);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            System.out.println("Ocorrerá, independemente de haver ou não uma exceção.");
//        }

        try {
            throw new MyException("Testando erros personalizados");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lerArquivo() throws IOException {
        FileReader arquivo = new FileReader("arquivo.txt");
    }

//Implemente um sistema de vendas de ingressos para um evento. Crie uma classe Evento que tenha um número limitado de ingressos. Quando um ingresso for comprado, o número de ingressos disponíveis deve ser decrementado. Caso o número de ingressos disponíveis seja insuficiente para uma nova compra, lance uma exceção personalizada chamada IngressosIndisponiveisException. Além disso, se um usuário tentar comprar um número de ingressos maior que o permitido (máximo de 10 ingressos por compra), lance uma exceção personalizada chamada LimiteCompraExcedidoException.
}
