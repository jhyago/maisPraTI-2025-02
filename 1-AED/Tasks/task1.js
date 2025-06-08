const prompt = require('prompt-sync')()

// 1. Verifica se um número é par ou ímpar
const num1 = parseInt(prompt("1) Digite um número inteiro:")); // lê uma string do usuário e converte para inteiro
if (num1 % 2 === 0) {                                   // verifica se o resto da divisão por 2 é zero (número par)
  console.log("O número é par.");                       // exibe no console que o número é par
} else {                                                // caso contrário (resto diferente de zero)
  console.log("O número é ímpar.");                     // exibe no console que o número é ímpar
}


// 2. Classifica idade em categorias
const idade = parseInt(prompt("2) Digite a idade:"));   // lê a idade do usuário e converte para inteiro
if (idade < 0) {                                        // verifica se a idade é negativa (inválida)
  console.log("Idade inválida.");                       // informa idade inválida
} else if (idade <= 12) {                               // idade entre 0 e 12 anos
  console.log("Criança");                               // classify como criança
} else if (idade <= 17) {                               // idade entre 13 e 17 anos
  console.log("Adolescente");                          // classify como adolescente
} else if (idade <= 59) {                               // idade entre 18 e 59 anos
  console.log("Adulto");                                // classify como adulto
} else {                                                 // idade igual ou acima de 60 anos
  console.log("Idoso");                                 // classify como idoso
}


// 3. Classifica nota de 0 a 10
const nota = parseFloat(prompt("3) Digite a nota (0 a 10):")); // lê nota como número decimal
if (nota < 0 || nota > 10) {                              // verifica se ficou fora do intervalo válido
  console.log("Nota inválida.");                          // exibe mensagem de erro para nota inválida
} else if (nota >= 7) {                                   // verifica se nota é maior ou igual a 7
  console.log("Aprovado");                                // aprova automático
} else if (nota >= 5) {                                   // verifica se nota está entre 5 e 6.9
  console.log("Recuperação");                             // fica em recuperação
} else {                                                  // nota menor que 5
  console.log("Reprovado");                               // reprova
}


// 4. Menu interativo com switch-case
const opcao = prompt(                                     
  "4) Escolha uma opção:\n1 – Dizer Olá\n2 – Mostrar data atual\n3 – Sair"
);                                                        // apresenta opções e lê escolha do usuário
switch (opcao) {                                          // inicia seleção de casos
  case "1":                                              
    console.log("Olá! Tudo bem?");                       // se escolhida opção 1, exibe saudação
    break;                                                // interrompe o switch após executar
  case "2":                                              
    console.log("Data atual:", new Date().toLocaleString()); // se 2, exibe data e hora local
    break;                                               
  case "3":                                              
    console.log("Saindo...");                            // se 3, informa que está saindo
    break;                                               
  default:                                               
    console.log("Opção inválida.");                      // para qualquer outro valor, informa inválido
    break;                                               
}


// 5. Cálculo de IMC e categoria de peso
const peso = parseFloat(prompt("5) Digite seu peso em kg:"));    // lê peso em kg
const altura = parseFloat(prompt("Digite sua altura em metros:")); // lê altura em metros
const imc = peso / (altura * altura);                            // calcula IMC: peso dividido por altura ao quadrado
console.log("Seu IMC é:", imc.toFixed(2));                        // exibe IMC formatado com duas casas decimais
if (imc < 18.5) {                                                 // IMC abaixo de 18.5
  console.log("Baixo peso");                                      // classifica como baixo peso
} else if (imc < 25) {                                             // IMC entre 18.5 e 24.9
  console.log("Peso normal");                                     // classifica como peso normal
} else if (imc < 30) {                                             // IMC entre 25 e 29.9
  console.log("Sobrepeso");                                       // classifica como sobrepeso
} else {                                                          // IMC 30 ou acima
  console.log("Obesidade");                                       // classifica como obesidade
}


// 6. Verifica triângulo e tipo
const A = parseFloat(prompt("6) Lado A:"));               // lê comprimento do lado A
const B = parseFloat(prompt("Lado B:"));                  // lê comprimento do lado B
const C = parseFloat(prompt("Lado C:"));                  // lê comprimento do lado C
if (A < B + C && B < A + C && C < A + B) {                // testa a condição de existência de triângulo
  // Se for triângulo, agora identifica o tipo
  if (A === B && B === C) {                               // todos os lados iguais
    console.log("Triângulo equilátero");                  // imprime equilátero
  } else if (A === B || A === C || B === C) {             // exatamente dois lados iguais
    console.log("Triângulo isósceles");                   // imprime isósceles
  } else {                                                // todos os lados diferentes
    console.log("Triângulo escaleno");                    // imprime escaleno
  }
} else {                                                  // se não satisfaz a condição de triângulo
  console.log("Não forma um triângulo.");                 // informa que não forma triângulo
}


// 7. Cálculo do valor de maçãs
const qtdMacas = parseInt(prompt("7) Quantas maçãs deseja comprar?")); // lê a quantidade de maçãs
let precoUnit = qtdMacas < 12 ? 0.30 : 0.25;                            // aplica preço por unidade conforme quantidade
let total = qtdMacas * precoUnit;                                       // calcula valor total da compra
console.log(
  `Total a pagar: R$ ${total.toFixed(2)} (${qtdMacas} maçãs a R$ ${precoUnit.toFixed(2)} cada)`
);                                                                      // exibe resumo do cálculo no console


// 8. Ordena dois valores em ordem crescente
const valor1 = parseFloat(prompt("8) Digite o primeiro valor:"));      // lê primeiro valor (decimal)
const valor2 = parseFloat(prompt("Digite o segundo valor (diferente do primeiro):")); // lê segundo valor
if (valor1 < valor2) {                                                 // compara os valores
  console.log("Ordem crescente:", valor1, valor2);                     // exibe valor1 primeiro, depois valor2
} else {                                                               // se valor1 >= valor2
  console.log("Ordem crescente:", valor2, valor1);                     // exibe valor2 primeiro, depois valor1
}


// 9. Contagem regressiva de 10 até 1
console.log("9) Contagem regressiva:");                                // indica início da contagem
for (let i = 10; i >= 1; i--) {                                        // inicia loop for de i=10 até i=1
  console.log(i);                                                      // imprime valor atual de i
}


// 10. Escreve um número 10 vezes
const num10 = parseInt(prompt("10) Digite um número inteiro:"));       // lê um número inteiro
console.log(`Escrevendo o número ${num10} dez vezes:`);                // informa o que será exibido
for (let i = 1; i <= 10; i++) {                                        // loop de 1 a 10
  console.log(num10);                                                  // imprime o número em cada iteração
}


// 11. Soma de 5 números
let soma11 = 0;                                                        // inicializa variável que acumula soma
for (let i = 1; i <= 5; i++) {                                         // loop para 5 entradas
  const n = parseFloat(prompt(`11) Digite o ${i}º número:`));         // lê cada número decimal
  soma11 += n;                                                         // acumula na variável soma11
}
console.log("Soma total dos 5 números:", soma11);                      // exibe o resultado da soma


// 12. Tabuada de 1 a 10
const tabuada = parseInt(prompt("12) Digite o número para ver a tabuada (1–10):")); // lê número inteiro para tabuada
console.log(`Tabuada do ${tabuada}:`);                                           // cabeçalho da tabuada
for (let i = 1; i <= 10; i++) {                                                  // loop de 1 a 10
  console.log(`${tabuada} x ${i} = ${tabuada * i}`);                             // imprime cada linha: número x i = produto
}


// 13. Média de decimais até digitar 0
let soma13 = 0;                               // inicializa soma dos valores
let count13 = 0;                             // inicializa contador de entradas
while (true) {                               // loop infinito até break
  const val = parseFloat(prompt("13) Digite um número decimal (0 para parar):")); // lê valor decimal
  if (val === 0) break;                     // se for zero, sai do loop
  soma13 += val;                            // soma valor em soma13
  count13++;                                // incrementa contador
}
if (count13 > 0) {                          // verifica se entrou pelo menos um valor
  console.log("Média aritmética:", (soma13 / count13).toFixed(2)); // calcula e exibe média
} else {                                    // se nenhum valor foi somado
  console.log("Nenhum valor para calcular média.");               // informa ausência de dados
}


// 14. Cálculo de fatorial
const nFat = parseInt(prompt("14) Digite um número inteiro para fatorial:")); // lê número para fatorial
let fat = 1;                                                               // inicializa acumulador do fatorial
for (let i = 1; i <= nFat; i++) {                                          // loop de 1 até nFat
  fat *= i;                                                                 // multiplica fat pelo contador i
}
console.log(`${nFat}! = ${fat}`);                                          // exibe o resultado do fatorial


// 15. Primeiros 10 números da sequência de Fibonacci
console.log("15) Sequência de Fibonacci (10 primeiros termos):");          // cabeçalho da sequência
let a = 0, b = 1;                                                         // inicializa os dois primeiros termos
console.log(a);                                                           // imprime primeiro termo (0)
console.log(b);                                                           // imprime segundo termo (1)
for (let i = 3; i <= 10; i++) {                                           // loop para gerar do 3º ao 10º termo
  const next = a + b;                                                     // calcula próximo termo como soma dos dois anteriores
  console.log(next);                                                      // imprime o termo calculado
  a = b;                                                                  // atualiza a para o próximo cálculo
  b = next;                                                               // atualiza b para o próximo cálculo
}
