package InterfaceExamples.ExercicioNotificacao;

public class EmailNotificador implements EnviadorNotificacao {
    @Override
    public void enviar(String destino, String mensagem) {
        System.out.println("E-mail para: "+ destino + ": " + mensagem);
    }
}