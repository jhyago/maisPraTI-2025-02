// --- 1. Várias formas de escrever "Hello World!" no console ---
// Com aspas simples dentro de aspas duplas
console.log("'Hello World!'");  
// Com aspas duplas dentro de aspas simples
console.log('"Hello World!"');  
// Com crases (template literal), permite interpolar expressões
console.log(`Hello World!`);    

// --- 2. Números e tipos básicos ---
// Imprime um número decimal
console.log(1.5);               
// typeof mostra o tipo de dado
console.log(typeof(10));        // "number"
console.log(typeof('10'));      // "string"

// Combina diferentes tipos de dados na mesma chamada
console.log(typeof('10'), 1000, "Jaques");

// --- 3. Declaração de variáveis ---
// let: escopo de bloco
let name = 'Jaques';            
// var: escopo global ou de função
var anotherName = 'Jaques';     

// const: valor fixo, não pode ser reatribuído
const CITY = "Porto Alegre";    

// variável sem inicializar (undefined)
let test;                       
console.log(test);              // undefined
test = "Teste";                 
console.log(test);              // "Teste"

// null e booleano
let myNull = null;              
console.log(myNull);            // null
let myBoolean = true;           
console.log(typeof(myBoolean)); // "boolean"

// --- 4. Operações aritméticas básicas --- 
let num1 = 10;
let num2 = 5;

console.log(num1 + num2);       // soma: 15
console.log(num1 - num2);       // subtração: 5
console.log(num1 * num2);       // multiplicação: 50
console.log(num1 / num2);       // divisão: 2
console.log(num1 ** num2);      // exponenciação: 10^5 = 100000
console.log(num1 % num2);       // módulo (resto): 0

// Precedência de operadores:
// 1) ()   2) **   3) * / %   4) + -

// --- 5. Conversão de tipos ---
// parseInt, parseFloat, Number(), String()
let numTest = "5";
console.log(parseInt(numTest) + 10);  // 15
console.log(parseFloat("3.14") + 1); // 4.14
console.log(Number("20") * 2);       // 40
console.log(String(100) + " reais"); // "100 reais"

// --- 6. Incremento e decremento --- 
let counter = 1;
counter = counter + 1;  // 2
counter = counter - 1;  // 1
counter++;              // 2 (pós-fix)
counter--;              // 1 (pós-fix)

console.log(counter);   // 1
console.log(counter++); // imprime 1, depois fica 2
console.log(counter);   // 2
console.log(++counter); // pré-fix: incrementa para 3 e imprime 3

// Passos maiores via operadores compostos
let step = 2;
counter += step;  // counter = counter + 2
counter -= step;  // counter = counter - 2
counter *= step;  // counter = counter * 2
counter /= step;  // counter = counter / 2

// --- 7. Métodos úteis de Math ---
// Números aleatórios entre 0 e 1
console.log("Math.random():", Math.random());

// Aleatório inteiro de 0 a 9
console.log("0–9:", Math.floor(Math.random() * 10));

// Arredondamentos
console.log("Math.ceil(4.2):", Math.ceil(4.2));   // 5
console.log("Math.floor(4.8):", Math.floor(4.8)); // 4
console.log("Math.round(4.5):", Math.round(4.5)); // 5
console.log("Math.trunc(4.9):", Math.trunc(4.9)); // 4

// Máximo, mínimo, potência, raiz quadrada, valor absoluto
console.log("Math.max(1,5,3):", Math.max(1, 5, 3));   // 5
console.log("Math.min(1,5,3):", Math.min(1, 5, 3));   // 1
console.log("Math.pow(2,3):", Math.pow(2, 3));       // 8
console.log("Math.sqrt(16):", Math.sqrt(16));        // 4
console.log("Math.abs(-10):", Math.abs(-10));        // 10

// --- 8. Trabalhando com Date ---
// Data e hora atuais
const now = new Date();
console.log("Agora:", now.toString());                
console.log("Timestamp (ms):", now.getTime());         // ms desde 1 Jan 1970

// Componentes da data
console.log("Ano:", now.getFullYear());                // ex: 2025
console.log("Mês (0–11):", now.getMonth());            // 0 = janeiro
console.log("Dia do mês:", now.getDate());             // 1–31
console.log("Dia da semana (0–6):", now.getDay());     // 0 = domingo
console.log("Hora:", now.getHours());                  // 0–23
console.log("Minutos:", now.getMinutes());             // 0–59
console.log("Segundos:", now.getSeconds());            // 0–59

// Formatando data local
console.log("Data local:", now.toLocaleDateString()); 
console.log("Hora local:", now.toLocaleTimeString()); 

// Criando data a partir de string
const dataStr = new Date("2025-12-25T10:30:00");
console.log("Natal 2025:", dataStr.toLocaleString());

// Alterando componentes
let aniversario = new Date(1990, 4, 15); // 15 de maio de 1990 (mês 0-based)
aniversario.setFullYear(1991);           // muda para 1991
aniversario.setMonth(11);                // dezembro (11)
console.log("Aniversário ajustado:", aniversario.toLocaleString());

// Diferença entre datas (em dias)
const dataFuturo = new Date(2025, 11, 31);
const diffMs = dataFuturo - now;                   // diferença em ms
const diffDias = Math.ceil(diffMs / (1000 * 60 * 60 * 24));
console.log(`Faltam ${diffDias} dias para 31 Dez 2025.`);

//  --- 9. Métodos de String: length, slice, repeat, padStart ---. 
const msg = "JS";
console.log("length:", msg.length);           // 2
console.log("slice(0,1):", msg.slice(0,1));   // "J"
console.log("repeat(3):", msg.repeat(3));     // "JSJSJS"
console.log("padStart(5):", msg.padStart(5, "*")); // "**JS"
