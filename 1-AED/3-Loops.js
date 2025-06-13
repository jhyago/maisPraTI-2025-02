// Importa prompt-sync para receber entradas do usuário (Node.js)
const prompt = require('prompt-sync')();

// =========================
// 1. Tabuada de um número fixo (5)
// =========================
console.log("Tabuada do 5 (hard-coded):");
for (let i = 1; i <= 10; i++) {
  // Loop de 1 a 10, imprimindo 5 * i
  console.log(`5 × ${i} =`, 5 * i);
}

console.log(); // linha em branco

// =========================
// 2. Tabuada dinâmica via prompt
// =========================
const multiplicador = Number(prompt("Digite o número para ver a tabuada: "));
console.log(`Tabuada do ${multiplicador}:`);
for (let contador = 0; contador <= 10; contador++) {
  // Para cada valor de 0 a 10, exibe a multiplicação
  console.log(`${multiplicador} × ${contador} =`, multiplicador * contador);
}

console.log();

// =========================
// 3. Valores pares de 0 a 20
// =========================
console.log("Números pares entre 0 e 20:");
for (let num = 0; num <= 20; num++) {
  // Verifica se o resto da divisão por 2 é zero
  if (num % 2 === 0) {
    console.log(num);
  }
}

console.log();

// =========================
// 4. Soma dos números de 1 a 100 (for e while)
// =========================
let somaFor = 0;
for (let j = 1; j <= 100; j++) {
  // Acumula cada j em somaFor
  somaFor += j;
}
console.log("Soma (for) de 1 a 100 =", somaFor);

let somaWhile = 0;
let k = 1;
while (k <= 100) {
  // Acumula k em somaWhile e incrementa
  somaWhile += k;
  k++;
}
console.log("Soma (while) de 1 a 100 =", somaWhile);

console.log();

// =========================
// 5. Contagem decrescente
// =========================
console.log("Contagem decrescente de 10 a 1:");
for (let d = 10; d >= 1; d--) {
  // Decrementa d até chegar em 1
  console.log(d);
}

console.log();

// =========================
// 6. Contagem regressiva com while
// =========================
let cw = 10;
console.log("Contagem regressiva (while) de 10 a 1:");
while (cw > 0) {
  console.log(cw);
  cw--;
}

console.log();

// =========================
// 7. Média aritmética até entrada 0
// =========================
let entrada = Number(prompt("Digite um número (0 para encerrar): "));
let soma = 0;
let quantidade = 0;

while (entrada !== 0) {
  // Soma e conta apenas se não for zero
  soma += entrada;
  quantidade++;
  entrada = Number(prompt("Digite outro número (0 para encerrar): "));
}

if (quantidade > 0) {
  console.log("Média aritmética:", soma / quantidade);
} else {
  console.log("Nenhum número válido foi inserido.");
}

console.log();

// =========================
// 8. Os 50 primeiros primos > 100
// =========================
console.log("50 primeiros números primos maiores que 100:");
let countPrimos = 0;
let numPrimo = 101;

while (countPrimos < 50) {
  // Supõe que é primo até encontrar divisor
  let ehPrimo = true;
  // Testa divisores até raiz quadrada
  for (let div = 2; div <= Math.sqrt(numPrimo); div++) {
    if (numPrimo % div === 0) {
      ehPrimo = false;
      break;
    }
  }
  if (ehPrimo) {
    console.log(numPrimo);
    countPrimos++;
  }
  numPrimo++;
}

console.log();

// =========================
// 9. do...while: pede nº até negativo
// =========================
let valor;
do {
  valor = Number(prompt("Digite um número (negativo para parar): "));
  console.log("Você digitou:", valor);
} while (valor >= 0);

console.log("Loop encerrado por valor negativo.");

// =========================
// 10. for...of e break/continue
// =========================
const lista = [3, 7, 12, 18, 5, 21];
console.log("Percorrendo lista com for...of:");
for (const n of lista) {
  if (n > 20) {
    console.log("Valor muito grande, interrompendo:", n);
    break; // sai do loop completamente
  }
  if (n % 2 !== 0) {
    // pula números ímpares
    continue;
  }
  console.log("Número par:", n);
}

console.log();

// =========================
// 11. Nested loops: tabelas de multiplicação 1 a 3
// =========================
console.log("Tabelas de multiplicação de 1 a 3:");
for (let base = 1; base <= 3; base++) {
  console.log(`--- Tabuada do ${base} ---`);
  for (let m = 1; m <= 5; m++) {
    // para cada base, multiplica de 1 a 5
    console.log(`${base} × ${m} =`, base * m);
  }
}

console.log();

// =========================
// 12. forEach em array
// =========================
const nomes = ["Ana", "Bruno", "Carla"];
console.log("Saudando nomes com forEach:");
nomes.forEach((nome, idx) => {
  // idx = índice atual, nome = valor atual
  console.log(`${idx + 1}. Olá, ${nome}!`);
});
