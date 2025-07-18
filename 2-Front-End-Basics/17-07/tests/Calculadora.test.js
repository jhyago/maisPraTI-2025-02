const Calculadora = require("../src/Calculadora");

describe("Testando a classe Calculadora", () => {
  let calc;

  beforeEach(() => {
    calc = new Calculadora();
  });

  test("Deve somar dois números corretamente: ", () => {
    expect(calc.somar(2, 3)).toBe(5);
    expect(calc.somar(-1, 1)).toBe(0);
  });

  test("Deve subtrair dois números corretamente: ", () => {
    expect(calc.subtrair(2, 3)).toBe(-1);
    expect(calc.subtrair(-1, 1)).toBe(-2);
  });

  test("Deve multiplicar dois números corretamente: ", () => {
    expect(calc.multiplicar(2, 3)).toBe(6);
    expect(calc.multiplicar(2, 1)).toBe(2);
  });

  test("Deve dividir dois números corretamente: ", () => {
    expect(calc.dividir(10, 2)).toBe(5);
    expect(calc.dividir(4, 2)).toBe(2);
  });
});

// Parte 1: Desenvolvimento da Classe ContaBancaria
// Crie uma classe chamada ContaBancaria que possua as seguintes propriedades e métodos:

// Propriedades:
// saldo: um número que representa o saldo da conta. Inicie com o valor 0 ao criar uma nova conta.

// titular: um string que representa o nome do titular da conta.

// Métodos:
// depositar(valor): Este método deve adicionar o valor informado ao saldo da conta.

// O valor do depósito deve ser positivo.

// Se o valor for negativo ou zero, o método deve lançar um erro com a mensagem: "Valor do depósito deve ser positivo".

// sacar(valor): Este método deve subtrair o valor informado do saldo da conta.

// O valor do saque deve ser positivo e menor ou igual ao saldo disponível.

// Se o valor for negativo ou o saldo for insuficiente, o método deve lançar um erro com a mensagem: "Saque inválido ou saldo insuficiente".

// consultarSaldo(): Este método deve retornar o saldo atual da conta.

// Parte 2: Desenvolvimento de Testes Automatizados com Jest
// Agora, escreva testes automatizados usando Jest para validar o funcionamento da classe ContaBancaria. Para isso, crie um arquivo de teste (ContaBancaria.test.js) com os seguintes casos de teste:

// Casos de Teste:
// Testar depósito válido:

// Crie uma conta com titular "João" e deposite 500 reais.

// Verifique se o saldo da conta após o depósito é 500 reais.

// Testar depósito inválido (valor negativo ou zero):

// Tente realizar um depósito com valor negativo (-100) ou zero.

// O sistema deve lançar um erro com a mensagem: "Valor do depósito deve ser positivo".

// Testar saque válido:

// Crie uma conta, deposite 500 reais e depois saque 200 reais.

// Verifique se o saldo final é 300 reais.

// Testar saque inválido (valor maior que o saldo ou negativo):

// Tente realizar um saque de 600 reais (saldo insuficiente).

// O sistema deve lançar um erro com a mensagem: "Saque inválido ou saldo insuficiente".

// Testar consulta de saldo:

// Crie uma conta com 1000 reais de saldo e verifique se o método consultarSaldo() retorna o valor correto (1000).
