// Importa prompt-sync para receber entradas do usuário (Node.js)
const prompt = require('prompt-sync')();

// =========================
// 1. CATEGORIAS COM ARRAYS E FUNÇÃO DE LISTAGEM
// =========================
let hardware = ['Notebook', 'Mouse', 'Teclado'];      // array de hardware
let fruits   = ['Laranja', 'Maçã'];                  // array de frutas
let people   = ['Aristóteles', 'Tolkien'];           // array de pessoas

// Função que recebe um array e imprime seus itens com cabeçalho
function listarCategoria(arr, nomeCategoria) {
  console.log(`\nItens de ${nomeCategoria}:`);       // cabeçalho da categoria
  for (let i = 0; i < arr.length; i++) {             // percorre índices de 0 até arr.length-1
    console.log(`- ${arr[i]}`);                      // imprime cada elemento
  }
}

hardware.push('Webcam');                             // adiciona 'Webcam' ao final de hardware
listarCategoria(hardware, 'Hardware');               // exibe hardware atualizado
listarCategoria(fruits,   'Frutas');                 // exibe frutas
listarCategoria(people,   'Pessoas');                // exibe pessoas

// =========================
// 2. BUSCA E REMOÇÃO EM ARRAYS
// =========================
let listaFrutas = ['Maçã', 'Banana', 'Melancia', 'Morango', 'Morango'];
console.log(`\nPrimeiro 'Morango' no índice: ${listaFrutas.indexOf('Morango')}`);
console.log(`Último 'Morango' no índice: ${listaFrutas.lastIndexOf('Morango')}`);
console.log('Contém Melancia?', listaFrutas.includes('Melancia'));     // true ou false

// Cria novo array sem 'Morango'
let semMorango = listaFrutas.filter(f => f !== 'Morango');
console.log('Lista sem Morango:', semMorango);

// =========================
// 3. ORDENAÇÃO DE ARRAYS
// =========================
let nums = [10, 12, 0, 16, 7, 9];
let numsCresc = nums.slice().sort((a, b) => a - b);    // crescente
console.log('\nNúmeros ordenados crescente:', numsCresc);
let numsDecres = nums.slice().sort((a, b) => b - a);   // decrescente
console.log('Números ordenados decrescente:', numsDecres);

// =========================
// 4. FUNÇÕES MATEMÁTICAS
// =========================
function areaRetangulo(base, altura) {
  return base * altura;                               // retorna produto
}
console.log('\nÁrea retângulo 10×5 =', areaRetangulo(10,5));

const areaCirculo = r => Math.PI * r * r;             // arrow function
console.log('Área círculo r=3 =', areaCirculo(3).toFixed(2));

// =========================
// 5. MÉTODOS DE ARRAY NUMÉRICOS
// =========================
let valores = [1, 2, 3, 4, 5];                        
let dobrados = valores.map(x => x * 2);                // map
console.log('\nDobro dos valores:', dobrados);
let pares    = valores.filter(x => x % 2 === 0);       // filter
console.log('Valores pares:', pares);
let soma     = valores.reduce((acc, x) => acc + x, 0); // reduce
console.log('Soma dos valores:', soma);

// =========================
// 6. LAÇOS BÁSICOS (for, while, do…while)
// =========================
console.log('\nContagem for 1 a 5:');
for (let i = 1; i <= 5; i++) console.log(i);

console.log('Contagem while 5 a 1:');
let w = 5;
while (w >= 1) {
  console.log(w);
  w--;
}

console.log('Digite números (0 para parar):');
let entrada;
do {
  entrada = Number(prompt('Número: '));
  console.log('Você digitou:', entrada);
} while (entrada !== 0);

// =========================
// 7. MENU SIMPLES COM ARRAYS
// =========================
let opcoes = ['Hardware', 'Frutas', 'Pessoas', 'Sair'];
function mostrarMenu() {
  console.log('\nMenu de Categorias:');
  for (let i = 0; i < opcoes.length; i++) console.log(`${i+1} - ${opcoes[i]}`);
  return Number(prompt('Escolha uma opção: '));
}

let escolha;
do {
  escolha = mostrarMenu();
  switch (escolha) {
    case 1: listarCategoria(hardware, 'Hardware'); break;
    case 2: listarCategoria(fruits,   'Frutas');   break;
    case 3: listarCategoria(people,   'Pessoas');  break;
    case 4: console.log('Saindo...');              break;
    default: console.log('Opção inválida!');       break;
  }
} while (escolha !== 4);

// =========================
// 8. MATRIZES (Array de Arrays)
// =========================
let matriz = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9]
];
console.log('\nElemento [0][1]:', matriz[0][1]);
console.log('Todos os elementos:');
for (let i = 0; i < matriz.length; i++) {
  for (let j = 0; j < matriz[i].length; j++) {
    console.log(`matriz[${i}][${j}] =`, matriz[i][j]);
  }
}

let somaDiagonal = 0;
for (let i = 0; i < matriz.length; i++) {
  somaDiagonal += matriz[i][i];
}
console.log('Soma diagonal principal =', somaDiagonal);

let transposta = matriz[0].map((_, colIndex) => matriz.map(row => row[colIndex]));
console.log('Matriz transposta:', transposta);