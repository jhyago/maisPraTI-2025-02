package InterfaceExamples.ExercicioNotificacao;

public class SmsNotificador implements EnviadorNotificacao {
    @Override
    public void enviar(String destino, String mensagem) {
        System.out.println("SMS para: "+ destino + ": " + mensagem);
    }
}