// Comparações simples usando operadores relacionais.
console.log(10 > 5);  // true, 10 é maior que 5
console.log(10 < 5);  // false, 10 não é menor que 5
console.log(10 <= 5); // false, 10 não é menor ou igual a 5
console.log(10 >= 5); // true, 10 é maior ou igual a 5
console.log(10 == 5); // false, 10 não é igual a 5
console.log(10 == '10'); // true, compara apenas valores (não o tipo)
console.log(10 === '10'); // false, compara valor e tipo (10 é número, '10' é string)
console.log(10 != '10'); // false, valores são iguais (apenas tipos diferentes)
console.log(10 !== '10'); // true, valores iguais mas tipos diferentes (número e string)

// Operadores lógicos.
console.log((10 < 5) && (10 > 2)); // false, ambos os lados precisam ser verdadeiros para && (E)
console.log((10 > 5) || (10 < 2)); // true, apenas um lado precisa ser verdadeiro para || (OU)
console.log((10 > 5) || !(10 < 2)); // true, negação da condição (10 < 2) torna a expressão verdadeira
console.log(!(10 > 5) || (10 < 2)); // false, negação da condição verdadeira torna-a falsa

// Exemplo de autenticação simples usando operadores lógicos.
let usuario = "Jaques";
let senha = "1234";
let autenticacao = usuario === 'Jaques' && senha === '1234'; // Verdadeiro se ambos forem corretos

console.log(autenticacao); // true, pois usuario e senha estão corretos

// Estruturas condicionais (if-else).
const nota = 60;
if(nota >= 60){
    console.log('APROVADO!'); // Se nota for 60 ou mais, imprime APROVADO!
} else {
    console.log('REPROVADO!'); // Se nota for menos que 60, imprime REPROVADO!
}

// Verifica a faixa etária usando if-else.
let idade = 50;

if((idade >= 18) && (idade <= 32)){
    console.log('Pode se inscrever no concurso!'); // Imprime se a idade estiver entre 18 e 32
} else {
    console.log('Não pode se inscrever no concurso.'); // Fora dessa faixa etária, imprime esta mensagem
}

// Operador ternário para decisões simples.
let resultadoTernario = (18 >= idade && idade <= 32) ? 
    console.log('Pode se inscrever no concurso!') : 
    console.log('Não pode se inscrever no concurso.');

// Exercício 1: Verifica se um número é par ou ímpar.
let numeroParImpar = 17;

numeroParImpar % 2 === 0 ? console.log('PAR!') : console.log('ÍMPAR!'); // Verifica se o resto da divisão por 2 é 0

// Exercício 2: Encontra o maior de três números.
let numero1 = 2;
let numero2 = 3;
let numero3 = 1;

if((numero1 > numero2) && (numero1 > numero3)){
    console.log('O primeiro valor é o maior!'); // numero1 é o maior
} else if((numero2 > numero1) && (numero2 > numero3)) {
    console.log('O segundo valor é o maior!'); // numero2 é o maior
} else if((numero3 > numero1) && (numero3 > numero2)){
    console.log('O terceiro número é o maior'); // numero3 é o maior
} else {
    console.log('Os números são iguais ou há um empate entre dois ou mais números.');
}

// Exercício 3: Calculadora simples usando entrada do usuário.
const prompt = require('prompt-sync')(); // Necessário para receber entrada do usuário no Node.js

let valor1 = Number(prompt("Insira o primeiro valor: ")); // Recebe o primeiro número do usuário
let valor2 = Number(prompt("Insira o segundo valor: "));  // Recebe o segundo número do usuário
let operacao = prompt("Informe a operação desejada (+, -, /, *): "); // Recebe a operação desejada
let resultado = 0;

if(operacao === '+'){
    resultado = valor1 + valor2; // Soma
} else if(operacao === '-'){
    resultado = valor1 - valor2; // Subtração
} else if(operacao === '*'){
    resultado = valor1 * valor2; // Multiplicação
} else if(operacao === '/'){
    if(valor2 !== 0){
        resultado = valor1 / valor2; // Divisão, com verificação de divisão por zero
    } else {
        console.log("Erro: divisão por zero!"); // Mensagem de erro se valor2 for 0
        resultado = undefined;
    }
} else {
    console.log('Operação inválida!'); // Se a operação não for uma das especificadas
    resultado = undefined;
}

if(resultado !== undefined){
    console.log("Resultado: ", resultado); // Imprime o resultado se não for undefined
}

// Estrutura switch para múltiplas condições.
let opcao = 1;

switch(opcao) {
    case 1: 
        console.log('Você selecionou a primeira opção'); // Se opcao for 1
        break;
    case 2: 
        console.log('Você selecionou a segunda opção'); // Se opcao for 2
        break;
    default:
        console.log('Você não escolheu nenhuma das opções'); // Qualquer outro valor
        break;
}

// Nullish Coalescing (??): usa valor padrão se for null ou undefined
let entrada = null;
let valorPadrao = entrada ?? "Valor Padrão";
console.log("Nullish Coalescing:", valorPadrao); // "Valor Padrão"

// Exemplos adicionais

// 1. Verifica se um valor está dentro de um intervalo (inclusive)
let idadeUsuario = 25;
if (idadeUsuario >= 18 && idadeUsuario <= 65) {
  console.log("Usuário em idade produtiva");
} else {
  console.log("Fora da faixa de idade produtiva");
}

// 2. Define frete grátis para compras acima de R$ 200, senão cobra R$ 20
let valorCompra = 180;
if (valorCompra > 200) {
  console.log("Frete grátis!");
} else {
  console.log("Frete: R$ 20,00");
}

// 3. Desconto por tipo de cliente usando if / else if / else
let tipoCliente = "premium"; // pode ser "vip", "premium", "regular"
if (tipoCliente === "vip") {
  console.log("Desconto de 20%");
} else if (tipoCliente === "premium") {
  console.log("Desconto de 10%");
} else {
  console.log("Sem desconto");
}

// 4. Verifica dia da semana e imprime mensagem com switch(true)
let dia = new Date().getDay(); // 0=domingo, 1=segunda...
switch (dia) {
  case 0:
    console.log("Domingo: dia de descanso");
    break;
  case 6:
    console.log("Sábado: fim de semana!");
    break;
  default:
    console.log("Dia de semana: hora de trabalhar");
    break;
}

// 5. Determina saudação pelo horário usando if / else if
let hora = new Date().getHours();
if (hora < 12) {
  console.log("Bom dia!");
} else if (hora < 18) {
  console.log("Boa tarde!");
} else {
  console.log("Boa noite!");
}

// 6. Verifica se string contém uma palavra-chave
let mensagem = "O pedido foi entregue com sucesso";
if (mensagem.includes("entregue")) {
  console.log("Status: entregue");
} else {
  console.log("Status desconhecido");
}

// 7. Operador ternário com expressão inline
let saldo = 50;
console.log(saldo >= 0 ? "Saldo positivo" : "Saldo negativo");

// 8. Condição de acesso múltiplo com switch
let nivelAcesso = 2; // 1=admin,2=editor,3=visitante
switch (nivelAcesso) {
  case 1:
    console.log("Acesso total");
    break;
  case 2:
    console.log("Acesso restrito");
    break;
  case 3:
    console.log("Acesso somente leitura");
    break;
  default:
    console.log("Nível de acesso inválido");
    break;
}

// 9. Verifica elemento em array com includes e if
const coresDisponiveis = ["vermelho", "verde", "azul"];
let corEscolhida = "amarelo";
if (coresDisponiveis.includes(corEscolhida)) {
  console.log("Cor disponível!");
} else {
  console.log("Cor indisponível");
}

// 10. Usando switch para categorizar a idade
let idadePessoa = 25;

switch(true) {
    case (idadePessoa >= 0 && idadePessoa <= 12):
        console.log("Criança");
        break;
    case (idadePessoa >= 13 && idadePessoa <= 17):
        console.log("Adolescente");
        break;
    case (idadePessoa >= 18 && idadePessoa <= 59):
        console.log("Adulto");
        break;
    case (idadePessoa >= 60):
        console.log("Idoso");
        break;
    default:
        console.log("Idade inválida");
        break;
}

//11. Verifica se uma string é vazia ou não
let texto = "";

if(texto){
    console.log("A string não está vazia."); // Se a string não for vazia
} else {
    console.log("A string está vazia."); // Se a string estiver vazia
}
