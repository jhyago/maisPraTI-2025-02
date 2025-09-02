import java.time.LocalDateTime;
import java.util.*;

public class GerenciadorFilaAtendimento {
//        Exercício 9 — Implemente um gerenciador de fila de atendimento com prioridades “normal” e “preferencial”.
//        Simule chegadas e atendimentos por comandos digitados; a cada atendimento, atenda duas senhas preferenciais
//        para cada normal, controlando isso com contadores e if para alternância.
//        Permita encerrar o programa e imprimir o histórico de chamadas, garantindo que casos de fila
//        vazia sejam tratados.

    enum Tipo { PREFERENCIAL, NORMAL }

    static final class Senha {
        final Tipo tipo;
        final String codigo;
        final LocalDateTime chegada;
        LocalDateTime atendimento;

        Senha(Tipo tipo, String codigo, LocalDateTime chegada) {
            this.tipo = tipo;
            this.codigo = codigo;
            this.chegada = chegada;
            this.atendimento = LocalDateTime.now();
        }
    }

    private static void imprimir() {
        System.out.println(
                "=== Gerenciador de Fila (2 Preferenciais : 1 Normal) ==="
                + "\n Comandos:"
                + "\n- Chegar P : adiciona senha preferencial"
                + "\n- Chegar N : Adiciona senha normal"
                + "\n- Atender : Chama a próxima senha (Regra 2P : 1N)"
                + "\n- Histórico : Mostra o histórico de chamadas"
                + "\n- Sair : Encerra imprimindo o histórico");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Deque<Senha> filaPref = new ArrayDeque<>();
        Deque<Senha> filaNormal = new ArrayDeque<>();
        List<Senha> historico = new ArrayList<>();

        int seqPref = 1;
        int seqNormal = 1;

        int preferenciaisAtendidasSeguidas = 0;

        imprimir();

        while(true) {
            String linha = sc.nextLine().trim();

            if(linha.isEmpty()) continue;

            String in = linha.toLowerCase(Locale.ROOT);

            switch(in) {
                case "atender" -> {
                    if (filaPref.isEmpty() && filaNormal.isEmpty()) {
                        System.out.println("Nenhuma senha aguardando. (Filas vazias");
                        continue;
                    }

                    if (preferenciaisAtendidasSeguidas < 2 && !filaPref.isEmpty()) {
                        atender(filaPref, historico);
                        preferenciaisAtendidasSeguidas++;
                    } else if (!filaNormal.isEmpty()) {
                        atender(filaNormal, historico);
                        preferenciaisAtendidasSeguidas = 0;
                    } else if (!filaPref.isEmpty()) {
                        atender(filaPref, historico);

                        if (preferenciaisAtendidasSeguidas < 2) preferenciaisAtendidasSeguidas++;
                    } else {
                        System.out.println("Nenhuma senha disponível para atendimento.");
                    }
                }
                case "historico" -> {
                    imprimirHistorico(historico);
                }
                default -> {
                    if(in.startsWith("chegar ")) {
                        String qual = in.substring("chegar ".length()).trim();
                        if(qual.equals("p") || qual.equals("pref")) {
                            String codigo = "p" + String.format("%03d", seqPref++);
                            var s = new Senha(Tipo.PREFERENCIAL, codigo, LocalDateTime.now());
                            filaPref.addLast(s);
                            System.out.println("Chegado registrada: " + codigo + "(Preferencial). Em espera: " + filaPref.size());
                        } else if(qual.equals("n") || qual.equals("normal")) {
                            String codigo = "n" + String.format("%03d", seqNormal++);
                            var s = new Senha(Tipo.NORMAL, codigo, LocalDateTime.now());
                            filaNormal.addLast(s);
                            System.out.println("Chegada registrada : " + codigo + "(Normal). Em espera: " + filaNormal.size());
                        } else {
                            System.out.println("Comando não reconhecido.");
                        }
                    }
                }
            }
        }
    }

    private static void atender(Deque<Senha> fila, List<Senha> historico) {
        Senha s = fila.pollFirst();
        if(s == null) {
            System.out.println("Fila vazia!");
            return;
        }

        s.atendimento = LocalDateTime.now();
        historico.add(s);
        System.out.println("Chamando: " + s.codigo + "(" + (s.tipo == Tipo.PREFERENCIAL ? "preferencial" : "normal") + ")");
    }

    private static void imprimirHistorico (List<Senha> historico) {
        if(historico.isEmpty()) {
            System.out.println("Histórico vazio!");
            return;
        }

        for(Senha s: historico) {
            System.out.println(s);
        }
    }
}