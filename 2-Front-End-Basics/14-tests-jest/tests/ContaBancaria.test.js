// Agrupa todos os testes relacionados à Classe ContaBancaria Avançada
describe('Classe ContaBancaria Avançada', () => {
  // Declara variáveis que apontarão para instâncias de ContaBancaria
  let contaA;  // Conta de origem para operações de saque, transferência, juros
  let contaB;  // Conta de destino para operações de transferência

  // beforeEach é executado antes de cada bloco de teste (cada test) abaixo
  beforeEach(() => {
    // Instancia uma nova ContaBancaria com número único 'A-001'
    contaA = new ContaBancaria('A-001');
    // Instancia uma nova ContaBancaria com número único 'B-002'
    contaB = new ContaBancaria('B-002');
  });

  // Caso de teste: verifica estado inicial ao criar nova conta
  test('Saldo inicial deve ser zero', () => {
    // consultaSaldo() deve retornar 0 em uma conta recém-criada
    expect(contaA.consultarSaldo()).toBe(0);
  });

  // Agrupa testes relacionados a depósitos
  describe('Depósito', () => {
    // Teste para depósito de valor válido
    test('Depositar valor positivo atualiza saldo e extrato', () => {
      // Ação: deposita 200 unidades monetárias
      contaA.depositar(200);
      // Verificação: o saldo da conta deve agora ser 200
      expect(contaA.consultarSaldo()).toBe(200);
      // Extrai o extrato de transações da conta
      const extrato = contaA.obterExtrato();
      // O extrato deve ter exatamente 1 entrada após um único depósito
      expect(extrato).toHaveLength(1);
      // A primeira entrada do extrato deve indicar um depósito de 200
      expect(extrato[0]).toMatchObject({ tipo: 'depósito', valor: 200 });
    });

    // Testa múltiplos valores inválidos de depósito usando test.each
    test.each([0, -50, '100'])(
      // Descrição dinâmica do teste para cada valor inválido (%p mostra o valor)
      'Depositando valor inválido %p deve lançar erro apropriado',
      (valorInvalido) => {
        // A ação de depositar valor inválido deve lançar erro com mensagem específica
        expect(() => contaA.depositar(valorInvalido))
          .toThrow('O valor do depósito deve ser número e maior que zero');
      }
    );
  });

  // Agrupa testes relacionados a saques
  describe('Saque', () => {
    // Garante que a conta tenha saldo antes de cada teste de saque
    beforeEach(() => {
      // Depósito inicial de 150 para possibilitar saques
      contaA.depositar(150);
    });

    // Teste para saque de valor válido
    test('Sacar valor válido reduz saldo e registra no extrato', () => {
      // Ação: saca 50 unidades da conta
      contaA.sacar(50);
      // Verificação: saldo deve diminuir de 150 para 100
      expect(contaA.consultarSaldo()).toBe(100);
      // Extrai o último evento do extrato (slice(-1)[0])
      const ultimo = contaA.obterExtrato().slice(-1)[0];
      // Verifica que o evento seja um saque de 50
      expect(ultimo).toMatchObject({ tipo: 'saque', valor: 50 });
    });

    // Testa saques inválidos (0, negativo, string) usando test.each
    test.each([0, -30, '30'])(
      'Saque de valor inválido %p deve lançar erro',
      (valorInvalido) => {
        // Função sacar() deve lançar erro para valores não positivos
        expect(() => contaA.sacar(valorInvalido))
          .toThrow('O valor do saque deve ser número e maior que zero');
      }
    );

    // Teste para saque maior que o saldo disponível
    test('Sacar valor maior que saldo lança erro de saldo insuficiente', () => {
      // Tenta sacar 200 quando o saldo disponível é apenas 150
      expect(() => contaA.sacar(200)).toThrow('Saldo insuficiente');
    });
  });

  // Agrupa testes relacionados a transferências entre contas
  describe('Transferência', () => {
    // Antes de cada teste de transferência, garante saldo suficiente em contaA
    beforeEach(() => {
      contaA.depositar(300);
    });

    // Teste do fluxo completo de transferência
    test('Transferir valor move fundos e registra extratos em ambas contas', () => {
      // Ação: transfere 100 de contaA para contaB
      contaA.transferir(100, contaB);
      // Verificações de saldo após a transferência
      expect(contaA.consultarSaldo()).toBe(200); // contaA reduz
      expect(contaB.consultarSaldo()).toBe(100); // contaB aumenta

      // Verifica registro no extrato de saída da contaA
      const eventoSaida = contaA.obterExtrato()
        .find(e => e.tipo === 'transferência_saida');
      expect(eventoSaida).toMatchObject({
        tipo: 'transferência_saida',
        valor: 100,
        destino: 'B-002'
      });

      // Verifica registro no extrato de entrada da contaB
      const eventoEntrada = contaB.obterExtrato()
        .find(e => e.tipo === 'transferência_entrada');
      expect(eventoEntrada).toMatchObject({
        tipo: 'transferência_entrada',
        valor: 100,
        origem: 'A-001'
      });
    });

    // Teste de erro ao transferir para objeto inválido
    test('Transferir para objeto inválido lança erro de conta de destino', () => {
      expect(() => contaA.transferir(50, {}))
        .toThrow('Conta de destino inválida');
    });

    // Teste de erro ao transferir valor acima do saldo disponível
    test('Transferir valor maior que saldo lança erro de saldo insuficiente', () => {
      expect(() => contaA.transferir(400, contaB))
        .toThrow('Saldo insuficiente');
    });
  });

  // Agrupa testes relacionados ao cálculo de juros
  describe('Juros', () => {
    // Antes de cada teste de juros, garante saldo inicial em contaA
    beforeEach(() => {
      contaA.depositar(1000);
    });

    // Teste do fluxo de aplicação de juros
    test('Aplicar juros positivos aumenta saldo e registra no extrato', () => {
      // Ação: aplica 5% de juros sobre 1000 (resultado 50)
      contaA.aplicarJuros(5);
      // Verificação do novo saldo (1000 + 50)
      expect(contaA.consultarSaldo()).toBeCloseTo(1050);
      // Verifica registro do evento de juros no extrato
      const jurosEvento = contaA.obterExtrato().slice(-1)[0];
      expect(jurosEvento).toMatchObject({ tipo: 'juros', valor: 50 });
    });

    // Testa percentuais inválidos (negativo ou não numérico)
    test.each([-10, '5'])(
      'Aplicar juros inválido %p lança erro apropriado',
      (jurosInvalido) => {
        expect(() => contaA.aplicarJuros(jurosInvalido))
          .toThrow('Percentual de juros deve ser número não negativo');
      }
    );
  });

  // Teste sobre imutabilidade do extrato retornado
  describe('Extrato', () => {
    test('obterExtrato retorna cópia imutável do extrato interno', () => {
      // Gera um evento de extrato com depósito de 100
      contaA.depositar(100);
      // Recebe uma cópia do extrato
      const extCopy = contaA.obterExtrato();
      // Tenta alterar a cópia (pop remove último elemento)
      extCopy.pop();
      // Nova chamada a obterExtrato() deve refletir o extrato original intacto
      expect(contaA.obterExtrato().length).toBe(1);
    });
  });
});