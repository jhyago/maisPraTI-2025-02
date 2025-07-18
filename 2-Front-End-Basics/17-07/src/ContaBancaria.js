class ContaBancaria {
  constructor(titular) {
    this.titular = titular;
    this.saldo = 0; 
  }

  depositar(valor) {
    if (valor <= 0) {
      throw new Error("Valor do depósito deve ser positivo");
    }
    this.saldo += valor;
  }

  sacar(valor) {
    if (valor <= 0 || valor > this.saldo) {
      throw new Error("Saque inválido ou saldo insuficiente");
    }
    this.saldo -= valor;
  }

  consultarSaldo() {
    return this.saldo;
  }
}

module.exports = ContaBancaria;
