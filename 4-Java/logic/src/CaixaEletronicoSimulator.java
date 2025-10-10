////
//// A partir do console, este programa simula um **caixa eletrônico** com menu de opções
//// (saldo, depósito, saque, extrato e sair). Mantém o menu ativo até a saída,
//// usa switch-case para ramificar as ações e valida os valores digitados.
//// As transações ficam registradas em memória e são exibidas no extrato.
//// Cada linha está comentada de forma didática.
////

import java.math.BigDecimal; // Importa BigDecimal para precisão em valores monetários
import java.math.RoundingMode; // Importa modo de arredondamento para padronizar casas decimais
import java.text.NumberFormat; // Importa formatador de moeda para exibir valores amigavelmente
import java.time.LocalDateTime; // Importa data/hora para carimbar transações
import java.time.format.DateTimeFormatter; // Importa formatador de data/hora
import java.util.ArrayList; // Importa ArrayList para armazenar as transações (extrato)
import java.util.Locale; // Importa Locale para formatação conforme pt-BR
import java.util.Scanner; // Importa Scanner para ler entradas do usuário

public class CaixaEletronicoSimulator { // Declara a classe pública principal (o arquivo deve se chamar igual)

    // =================== TIPOS E MODELOS ===================

    enum TipoTransacao { // Enumeração para categorizar uma transação
        DEPOSITO, // Depósito realizado na conta
        SAQUE     // Saque realizado da conta
    } // Fim do enum TipoTransacao

    static final class Transacao { // Classe para representar uma transação individual
        final LocalDateTime dataHora; // Momento exato da transação
        final TipoTransacao tipo; // Tipo (depósito/saque)
        final BigDecimal valor; // Valor movimentado
        final BigDecimal saldoApos; // Saldo da conta após efetivar a transação
        final String descricao; // Descrição amigável da movimentação

        Transacao(LocalDateTime dataHoraParametro, // Construtor com todos os campos
                  TipoTransacao tipoParametro,
                  BigDecimal valorParametro,
                  BigDecimal saldoAposParametro,
                  String descricaoParametro) {
            this.dataHora = dataHoraParametro; // Atribui data/hora
            this.tipo = tipoParametro; // Atribui tipo
            this.valor = valorParametro; // Atribui valor movimentado
            this.saldoApos = saldoAposParametro; // Atribui saldo pós-transação
            this.descricao = descricaoParametro; // Atribui descrição informativa
        } // Fim do construtor
    } // Fim da classe Transacao

    static final class Conta { // Classe que modela a conta bancária do cliente
        private BigDecimal saldo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN); // Saldo com 2 casas decimais
        private final ArrayList<Transacao> extrato = new ArrayList<>(); // Lista em memória com as transações

        public BigDecimal getSaldo() { // Getter do saldo atual
            return this.saldo; // Retorna o saldo
        } // Fim do getter

        public ArrayList<Transacao> getExtrato() { // Getter do extrato (lista de transações)
            return this.extrato; // Retorna a lista
        } // Fim do getter

        public void depositar(BigDecimal valorParametro) { // Efetua um depósito no saldo
            if (valorParametro == null || valorParametro.compareTo(BigDecimal.ZERO) <= 0) { // Valida valor positivo
                throw new IllegalArgumentException("Valor de depósito deve ser positivo."); // Lança erro se inválido
            } // Fim do if de validação
            BigDecimal valorNormalizado = valorParametro.setScale(2, RoundingMode.HALF_EVEN); // Normaliza para 2 casas
            this.saldo = this.saldo.add(valorNormalizado); // Soma o valor ao saldo atual
            registrar(TipoTransacao.DEPOSITO, valorNormalizado, "Depósito em conta"); // Registra transação
        } // Fim do método depositar

        public void sacar(BigDecimal valorParametro) { // Efetua um saque do saldo
            if (valorParametro == null || valorParametro.compareTo(BigDecimal.ZERO) <= 0) { // Valida valor positivo
                throw new IllegalArgumentException("Valor de saque deve ser positivo."); // Lança erro se inválido
            } // Fim do if de validação
            BigDecimal valorNormalizado = valorParametro.setScale(2, RoundingMode.HALF_EVEN); // Normaliza para 2 casas
            if (this.saldo.compareTo(valorNormalizado) < 0) { // Verifica se há saldo suficiente
                throw new IllegalArgumentException("Saldo insuficiente para saque."); // Lança erro de saldo insuficiente
            } // Fim do if de saldo
            this.saldo = this.saldo.subtract(valorNormalizado); // Subtrai o valor do saldo
            registrar(TipoTransacao.SAQUE, valorNormalizado, "Saque em caixa"); // Registra transação
        } // Fim do método sacar

        private void registrar(TipoTransacao tipoParametro, BigDecimal valorParametro, String descricaoParametro) { // Adiciona ao extrato
            this.extrato.add(new Transacao(LocalDateTime.now(), // Usa data/hora atual
                    tipoParametro, // Tipo da transação
                    valorParametro, // Valor movimentado
                    this.saldo, // Saldo após movimentação
                    descricaoParametro)); // Descrição
        } // Fim do método registrar
    } // Fim da classe Conta

    // =================== FORMATAÇÃO E UTILITÁRIOS ===================

    private static final Locale PT_BR = new Locale("pt", "BR"); // Define locale pt-BR
    private static final NumberFormat MOEDA = NumberFormat.getCurrencyInstance(PT_BR); // Formatador de moeda para pt-BR
    private static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Formato de data e hora

    private static void mostrarMenu() { // Imprime o menu principal na tela
        System.out.println("=== CAIXA ELETRÔNICO ==="); // Título do menu
        System.out.println("1) Saldo"); // Opção 1
        System.out.println("2) Depósito"); // Opção 2
        System.out.println("3) Saque"); // Opção 3
        System.out.println("4) Extrato"); // Opção 4
        System.out.println("5) Sair"); // Opção 5
        System.out.print("> "); // Prompt para digitação
    } // Fim do método mostrarMenu

    private static void pausar(Scanner leitorParametro) { // Aguarda ENTER para continuar
        System.out.println("\nPressione ENTER para continuar..."); // Mensagem de pausa
        leitorParametro.nextLine(); // Consome a linha pendente até ENTER
    } // Fim do método pausar

    private static void telaSaldo(Conta contaParametro) { // Exibe o saldo atual
        System.out.println("\n--- SALDO ---"); // Cabeçalho
        System.out.println("Saldo atual: " + MOEDA.format(contaParametro.getSaldo())); // Mostra saldo formatado em moeda
    } // Fim de telaSaldo

    private static void telaDeposito(Scanner leitorParametro, Conta contaParametro) { // Fluxo de depósito
        System.out.println("\n--- DEPÓSITO ---"); // Cabeçalho
        BigDecimal valorLido = lerValorMonetario(leitorParametro, "Informe o valor do depósito (ENTER para cancelar): "); // Lê valor com validação
        if (valorLido == null) { // Se o usuário cancelou
            System.out.println("Depósito cancelado."); // Informa cancelamento
            return; // Sai da tela
        } // Fim do if de cancelamento
        try { // Tenta efetuar o depósito
            contaParametro.depositar(valorLido); // Chama método de depósito
            System.out.println("Depósito realizado com sucesso! Novo saldo: " + MOEDA.format(contaParametro.getSaldo())); // Mensagem de sucesso
        } catch (IllegalArgumentException erroDeposito) { // Captura erros de validação
            System.out.println("Não foi possível realizar o depósito: " + erroDeposito.getMessage()); // Mensagem de erro
        } // Fim do try/catch
    } // Fim de telaDeposito

    private static void telaSaque(Scanner leitorParametro, Conta contaParametro) { // Fluxo de saque
        System.out.println("\n--- SAQUE ---"); // Cabeçalho
        BigDecimal valorLido = lerValorMonetario(leitorParametro, "Informe o valor do saque (ENTER para cancelar): "); // Lê valor com validação
        if (valorLido == null) { // Se o usuário cancelou
            System.out.println("Saque cancelado."); // Informa cancelamento
            return; // Sai da tela
        } // Fim do if de cancelamento
        try { // Tenta efetuar o saque
            contaParametro.sacar(valorLido); // Chama método de saque
            System.out.println("Saque realizado com sucesso! Novo saldo: " + MOEDA.format(contaParametro.getSaldo())); // Mensagem de sucesso
        } catch (IllegalArgumentException erroSaque) { // Captura erros (valor inválido / saldo insuficiente)
            System.out.println("Não foi possível realizar o saque: " + erroSaque.getMessage()); // Mensagem de erro
        } // Fim do try/catch
    } // Fim de telaSaque

    private static void telaExtrato(Conta contaParametro) { // Exibe o extrato de transações
        System.out.println("\n--- EXTRATO ---"); // Cabeçalho
        ArrayList<Transacao> listaExtrato = contaParametro.getExtrato(); // Obtém lista de transações
        if (listaExtrato.isEmpty()) { // Verifica se não há transações
            System.out.println("Nenhuma transação registrada até o momento."); // Mensagem de extrato vazio
            return; // Sai da tela
        } // Fim do if de extrato vazio
        System.out.printf("%-19s  %-10s  %12s  %12s  %s\n", "Data/Hora", "Tipo", "Valor", "Saldo", "Descrição"); // Cabeçalho de colunas
        for (Transacao transacaoAtual : listaExtrato) { // Percorre cada transação
            String textoTipo = (transacaoAtual.tipo == TipoTransacao.DEPOSITO ? "Depósito" : "Saque"); // Converte enum em texto
            System.out.printf("%-19s  %-10s  %12s  %12s  %s\n",
                    transacaoAtual.dataHora.format(HORA), // Formata data/hora
                    textoTipo, // Tipo textual
                    MOEDA.format(transacaoAtual.valor), // Valor movimentado
                    MOEDA.format(transacaoAtual.saldoApos), // Saldo após
                    transacaoAtual.descricao); // Descrição
        } // Fim do for
        System.out.println("Saldo atual: " + MOEDA.format(contaParametro.getSaldo())); // Mostra saldo final
    } // Fim de telaExtrato

    private static int lerOpcao(Scanner leitorParametro) { // Lê a opção do menu de forma resiliente
        String linhaLida = leitorParametro.nextLine().trim(); // Lê a linha e remove espaços
        if (linhaLida.isEmpty()) { // Se o usuário não digitou nada
            return -1; // Retorna código de opção inválida
        } // Fim do if
        try { // Tenta converter para inteiro
            return Integer.parseInt(linhaLida); // Converte e retorna
        } catch (NumberFormatException e) { // Em caso de erro de conversão
            return -1; // Retorna inválido
        } // Fim do try/catch
    } // Fim do método lerOpcao

    private static BigDecimal lerValorMonetario(Scanner leitorParametro, String promptParametro) { // Lê valor monetário como BigDecimal
        System.out.print(promptParametro); // Mostra mensagem de entrada
        String linhaValor = leitorParametro.nextLine().trim(); // Lê a linha do usuário
        if (linhaValor.isEmpty()) { // Se vazio, tratamos como cancelamento
            return null; // Retorna nulo para indicar cancelamento
        } // Fim do if de cancelamento
        String normalizado = linhaValor.replace(',', '.'); // Permite digitar com vírgula (pt-BR) convertendo para ponto
        try { // Tenta construir o BigDecimal
            BigDecimal valor = new BigDecimal(normalizado); // Converte String em BigDecimal
            if (valor.compareTo(BigDecimal.ZERO) <= 0) { // Garante que seja positivo
                System.out.println("Valor deve ser positivo."); // Mensagem de validação
                return null; // Indica cancelamento/erro para a tela chamar novamente se quiser
            } // Fim do if de validação
            return valor.setScale(2, RoundingMode.HALF_EVEN); // Normaliza casas e retorna
        } catch (NumberFormatException e) { // Se a string não for número válido
            System.out.println("Entrada inválida: informe um número. Ex.: 100 ou 99,90"); // Mensagem de erro
            return null; // Retorna nulo para indicar erro
        } // Fim do try/catch
    } // Fim do método lerValorMonetario

    // =================== PROGRAMA PRINCIPAL ===================

    public static void main(String[] args) { // Ponto de entrada da aplicação
        try (Scanner leitorPrincipal = new Scanner(System.in)) { // Cria Scanner com try-with-resources para fechar ao final
            Conta contaDoCliente = new Conta(); // Instancia a conta usada na sessão

            boolean executandoMenu = true; // Flag para manter o menu ativo
            while (executandoMenu) { // Laço principal do menu
                mostrarMenu(); // Exibe as opções
                int opcaoSelecionada = lerOpcao(leitorPrincipal); // Lê a opção do usuário

                switch (opcaoSelecionada) { // Direciona o fluxo conforme a escolha
                    case 1 -> { // Caso 1: Saldo
                        telaSaldo(contaDoCliente); // Mostra saldo atual
                        pausar(leitorPrincipal); // Pausa para leitura
                    } // Fim do caso 1
                    case 2 -> { // Caso 2: Depósito
                        telaDeposito(leitorPrincipal, contaDoCliente); // Executa depósito
                        pausar(leitorPrincipal); // Pausa
                    } // Fim do caso 2
                    case 3 -> { // Caso 3: Saque
                        telaSaque(leitorPrincipal, contaDoCliente); // Executa saque
                        pausar(leitorPrincipal); // Pausa
                    } // Fim do caso 3
                    case 4 -> { // Caso 4: Extrato
                        telaExtrato(contaDoCliente); // Exibe extrato de transações
                        pausar(leitorPrincipal); // Pausa
                    } // Fim do caso 4
                    case 5 -> { // Caso 5: Sair
                        System.out.println("\nEncerrando. Obrigado por utilizar o Caixa Eletrônico!"); // Mensagem de saída
                        executandoMenu = false; // Atualiza flag para sair do laço
                    } // Fim do caso 5
                    default -> { // Qualquer outra entrada é inválida
                        System.out.println("Opção inválida. Tente novamente."); // Mensagem de orientação
                        pausar(leitorPrincipal); // Pausa breve
                    } // Fim do default
                } // Fim do switch
            } // Fim do while do menu
        } // Fim do try-with-resources (Scanner é fechado automaticamente)
    } // Fim do main
} // Fim da classe CaixaEletronicoSimulator