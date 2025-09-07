package InterfaceExamples.ExercicioNotificacao;

public class Alerta {
    private final EnviadorNotificacao notificador;

    public Alerta(EnviadorNotificacao notificador) {
        this.notificador = notificador;
    }

    public void disparar(String destino) {
        notificador.enviar(destino, "Seu relatório está pronto!");
    }

    public static void main(String[] args) {
        Alerta alertaEmail = new Alerta(new EmailNotificador());
        Alerta alertaSms = new Alerta(new SmsNotificador());
        alertaSms.disparar("Jorge@daOficina.com");
        alertaEmail.disparar("TiaoDoBerrante@bol.com");
    }
}

