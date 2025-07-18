const ContaBancaria = require('../src/ContaBancaria');

describe('Testando a classe ContaBancaria', () => {
  let conta;

  beforeEach(() => {
    conta = new ContaBancaria('Geromel');
  });

  test('deve permitir realizar um depósito válido', () => {
    conta.depositar(500);
    expect(conta.consultarSaldo()).toBe(500);
  });

  test('não deve permitir depósito com valor negativo ou zero', () => {
    expect(() => conta.depositar(-100)).toThrow('Valor do depósito deve ser positivo');
    expect(() => conta.depositar(0)).toThrow('Valor do depósito deve ser positivo');
  });

  test('deve permitir realizar um saque válido', () => {
    conta.depositar(500);
    conta.sacar(200);
    expect(conta.consultarSaldo()).toBe(300);
  });

  test('não deve permitir saque com valor maior que o saldo ou negativo', () => {
    conta.depositar(500);
    expect(() => conta.sacar(600)).toThrow('Saque inválido ou saldo insuficiente');
    expect(() => conta.sacar(-100)).toThrow('Saque inválido ou saldo insuficiente');
  });

  test('deve consultar o saldo corretamente', () => {
    conta.depositar(1000);
    expect(conta.consultarSaldo()).toBe(1000);
  });
});
