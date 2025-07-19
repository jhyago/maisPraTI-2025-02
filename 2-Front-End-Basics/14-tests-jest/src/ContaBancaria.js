/**
 * Classe que representa uma conta bancária com operações básicas,
 * extrato de transações, transferências e aplicação de juros.
 */
class ContaBancaria {
  /**
   * Cria uma nova conta com número único.
   * @param {string} numeroConta - Identificador da conta
   */
  constructor(numeroConta) {
    this.numero = numeroConta;
    this.saldo = 0;
    this.extrato = [];
  }

  /**
   * Retorna o saldo atual da conta.
   * @returns {number}
   */
  consultarSaldo() {
    return this.saldo;
  }

  /**
   * Deposita um valor positivo na conta e registra no extrato.
   * @param {number} valor
   * @throws {Error} se valor <= 0
   */
  depositar(valor) {
    if (typeof valor !== 'number' || valor <= 0) {
      throw new Error('O valor do depósito deve ser número e maior que zero');
    }
    this.saldo += valor;
    this.extrato.push({ tipo: 'depósito', valor, data: new Date() });
  }

  /**
   * Saca um valor positivo da conta, se houver saldo, e registra no extrato.
   * @param {number} valor
   * @throws {Error} se valor <= 0 ou saldo insuficiente
   */
  sacar(valor) {
    if (typeof valor !== 'number' || valor <= 0) {
      throw new Error('O valor do saque deve ser número e maior que zero');
    }
    if (valor > this.saldo) {
      throw new Error('Saldo insuficiente para saque');
    }
    this.saldo -= valor;
    this.extrato.push({ tipo: 'saque', valor, data: new Date() });
  }

  /**
   * Transfere valor para outra ContaBancaria, registrando em ambos extratos.
   * @param {number} valor
   * @param {ContaBancaria} contaDestino
   * @throws {Error} se contaDestino inválida ou valor <=0 ou saldo insuficiente
   */
  transferir(valor, contaDestino) {
    if (!(contaDestino instanceof ContaBancaria)) {
      throw new Error('Conta de destino inválida');
    }
    // Usa sacar e depositar para aproveitar validações e extrato interno
    this.sacar(valor);
    contaDestino.depositar(valor);
    // Registra transferências específicas
    const agora = new Date();
    this.extrato.push({ tipo: 'transferência_saida', valor, destino: contaDestino.numero, data: agora });
    contaDestino.extrato.push({ tipo: 'transferência_entrada', valor, origem: this.numero, data: agora });
  }

  /**
   * Aplica rendimento percentual ao saldo atual.
   * @param {number} percentual - Por exemplo, 5 para 5%
   * @throws {Error} se percentual < 0 ou não for número
   */
  aplicarJuros(percentual) {
    if (typeof percentual !== 'number' || percentual < 0) {
      throw new Error('Percentual de juros deve ser número não negativo');
    }
    const rendimento = this.saldo * (percentual / 100);
    this.saldo += rendimento;
    this.extrato.push({ tipo: 'juros', valor: rendimento, data: new Date() });
  }

  /**
   * Retorna uma cópia do extrato de transações.
   * @returns {{tipo: string, valor: number, data: Date, [chave: string]: any}[]}
   */
  obterExtrato() {
    return [...this.extrato]; // devolve array imutável externamente
  }
}

module.exports = ContaBancaria;