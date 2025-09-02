////
//////A partir do console, crie um simulador de caixa eletrônico que exiba um menu
//////com as opções “saldo”, “depósito”, “saque”, “extrato” e “sair”. Use um laço
//////para manter o menu ativo até que a opção de saída seja escolhida, switch para
//////ramificar a operação selecionada e condicionais para validar valores
//////Registre as transações em uma estrutura simples na memória e mostre o extrato quando solicitado.
////
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CaixaEletronicoSimulator {
    enum TipoTransacao { DEPOSITO, SAQUE }

    static final class Transacao {
        final LocalDateTime dataHora;
        final TipoTransacao tipo;
        final BigDecimal valor;
        final BigDecimal saldoApos;
        final String descricao;

        Transacao(LocalDateTime dataHora,
                  TipoTransacao tipo,
                  BigDecimal valor,
                  BigDecimal saldoApos,
                  String descricao) {
            this.dataHora = dataHora;
            this.tipo = tipo;
            this.valor = valor;
            this.saldoApos = saldoApos;
            this.descricao = descricao;

        }
    }

    static final class Conta {
        private BigDecimal saldo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        private final ArrayList<Transacao> extrato = new ArrayList<>();

        public BigDecimal getSaldo() {
            return this.saldo;
        }

        public ArrayList<Transacao> getExtrato() {
            return this.extrato;
        }

        public void depositar (BigDecimal valor) {
            if(valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "Valor de Depósito deve ser Positivo"
                );
            }

            this.saldo = saldo.add(valor);
            this.registrar(TipoTransacao.DEPOSITO, valor, "Depósito em conta");
        }

        public void saque(BigDecimal valor) {
            if(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException(
                        "Valor de Saque deve ser Positivo"
                );
            }

            if (this.saldo.compareTo(valor) < 0) {
                throw new IllegalArgumentException(
                        "Valor de Saque deve ser Negativo"
                );
            }

            this.saldo = saldo.subtract(valor);
            registrar(TipoTransacao.SAQUE, valor, "Saque em caixa");

        }

        private void registrar(TipoTransacao tipo, BigDecimal valor, String descricao) {
            this.extrato.add(new Transacao(LocalDateTime.now(), tipo, valor, this.saldo, descricao));
        }

        private static void mostrarMenu() {
            String menu = "=== CAIXA ELETRÔNICO === \n 1) Saldo \n 2)Depósito \n 3)Saque em caixa \n 4) Extrato \n 5) Sair";
            System.out.println(menu);
        }

        private static void pausar(Scanner in) {
            System.out.println("Pressione ENTER para continuar...");
            in.nextLine();
        }

        private static void telaSaldo(Conta conta) {
            System.out.println("--- SALDO ---");
            System.out.println("Saldo atual: " + conta.saldo);
        }

        private static void telaDeposito(Scanner in, Conta conta) {
            System.out.println("--- DEPOSITO ---");
            BigDecimal valor = in.nextBigDecimal();
            try {
                conta.depositar(valor);
                System.out.println("Depositado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Não foi possível realizar o depósito" + e.getMessage());
            }
        }

        private static void telaSaque(Scanner in, Conta conta) {
            System.out.println("--- SAQUE ---");
            BigDecimal valor = in.nextBigDecimal();
            try {
                conta.saque(valor);
                System.out.println("Saque realizado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Não foi possível realizar o saque " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            try (Scanner in = new Scanner(System.in)) {
                Conta conta = new Conta();

                boolean executando = true;
                while (executando) {
                    mostrarMenu();

                    int opcao = in.nextInt();
                    switch (opcao) {
                        case 1:
                            telaSaldo(conta);
                            pausar(in);
                        case 2:
                            telaDeposito(in, conta);
                            pausar(in);
                        case 3:
                            telaSaque(in, conta);
                            pausar(in);
                        default:
                            System.out.println("Opcao invalida. Tente Novamente");
                    }
                }
            }
        }
    }
}