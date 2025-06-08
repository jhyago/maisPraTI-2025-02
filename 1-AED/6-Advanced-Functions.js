// Importa prompt-sync para entrada do usuário (Node.js)
const prompt = require('prompt-sync')();

// ==== 1. FUNÇÕES BÁSICAS ====

// 1.1. Função anônima armazenada em variável
let teste = function() {
    console.log('Olá, mundo!'); // Imprime 'Olá, mundo!'
};

// 1.2. IIFE: função invocada imediatamente após a definição
(function() {
    console.log('IIFE executada imediatamente!'); // Executa assim que definida
})();


// ==== 2. CALLBACKS ====

// 2.1. Função que recebe callbacks de sucesso e erro
function showFunction(successCallback, errorCallback) {
    const condicao = false; // Simula condição (sempre falsa)
    if (condicao) {
        successCallback('Requisição bem sucedida'); // Caminho de sucesso
    } else {
        errorCallback('Erro na requisição');        // Caminho de erro
    }
}

// 2.2. Callbacks de exemplo
let onSuccess = function(message) {
    console.log('Sucesso:', message); // Trata mensagem de sucesso
};

let onError = (message) => {
    console.log('Erro:', message);    // Trata mensagem de erro
};


// ==== 3. UTILITÁRIOS DE ARRAY ====

// 3.1. Soma dos elementos de um array
function arrSum(arr) {
    let sum = 0;               // Inicializa soma em zero
    for (let i = 0; i < arr.length; i++) {
        sum += arr[i];         // Acumula cada elemento
    }
    return sum;                // Retorna soma total
}

// 3.2. Encontra o maior número em um array
function findMax(arr) {
    if (arr.length === 0) return undefined; // Se array vazio
    let max = arr[0];          // Assume primeiro elemento como maior
    for (let i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];      // Atualiza se encontrar valor maior
        }
    }
    return max;                // Retorna maior valor
}


// ==== 4. CLOSURES E RECURSÃO ====

// 4.1. Closure: contador que "lembra" seu estado
function criarContador() {
    let contagem = 0;         // Variável privada ao closure
    return function() {       // Função retornada forma closure
        contagem++;           // Incrementa contador interno
        console.log('Contador:', contagem);
    };
}

// 4.2. Recursão: cálculo de fatorial
function fatorial(n) {
    if (n <= 1) return 1;     // Caso base: 0! e 1! = 1
    return n * fatorial(n - 1); // Chamada recursiva
}

// 4.3. Fibonacci recursivo (menos eficiente)
function fibRecursivo(n) {
    if (n <= 1) return n;     // Casos base fib(0)=0, fib(1)=1
    return fibRecursivo(n - 1) + fibRecursivo(n - 2);
}

// 4.4. Fibonacci iterativo (mais eficiente)
function fibIterativo(n) {
    let a = 0, b = 1;         // Termos iniciais
    for (let i = 2; i <= n; i++) {
        let prox = a + b;     // Próximo termo = soma dos anteriores
        a = b;                // Atualiza a para o próximo cálculo
        b = prox;             // Atualiza b com o termo atual
    }
    return n === 0 ? 0 : b;   // Se n=0 retorna 0, caso contrário retorna b
}


// ==== 5. OPERAÇÕES EM ARRAY DE OBJETOS ====

// Exemplo de array de produtos
const produtos = [
    { nome: 'Camisa', preco: 50 },
    { nome: 'Calça', preco: 80 },
    { nome: 'Sapato', preco: 120 }
];

// 5.1. Extrai preços dos produtos
function getPrecos(produtos) {
    return produtos.map(function(p) {
        return p.preco;      // Retorna campo 'preco' de cada objeto
    });
}

// 5.2. Filtra produtos acima de determinado preço
function filtrarCaros(produtos, limite) {
    return produtos.filter(function(p) {
        return p.preco > limite; // Mantém elementos cujo preco > limite
    });
}

// 5.3. Ordena produtos por preço (crescente)
function ordenarPorPreco(produtos) {
    return produtos.slice().sort(function(a, b) {
        return a.preco - b.preco; // Ordenação numérica
    });
}


// ==== 6. REDUÇÃO E ASSÍNCRONO ====

// 6.1. Soma total de valores em array usando reduce
function somaTotal(arr) {
    return arr.reduce(function(acc, val) {
        return acc + val;      // Acumula cada elemento
    }, 0);                     // Valor inicial do acumulador = 0
}

// 6.2. Executa callback após atraso simulado
function executarApos(ms, callback) {
    setTimeout(function() {
        console.log(`Executando após ${ms} ms`);
        if (callback) callback(); // Invoca callback se existir
    }, ms);
}

// 6.3. Exemplo simples de Promise
function prometerAlgo(valor) {
    return new Promise(function(resolve, reject) {
        if (valor > 0) {
            resolve('Valor positivo recebido!'); // Caminho de sucesso
        } else {
            reject('Valor inválido!');          // Caminho de erro
        }
    });
}


// ==== 7. EXECUÇÃO DE EXEMPLOS ====

// 7.1. Teste de função anônima
teste();

// 7.2. Callbacks de sucesso/erro
showFunction(onSuccess, onError);

// 7.3. Soma e máximo de arrays
console.log('Soma [10,20,40,30]:', arrSum([10,20,40,30]));
console.log('Máximo [1,2,3,4,5]:', findMax([1,2,3,4,5]));

// 7.4. Closure - contador independente
const contador1 = criarContador();
contador1();
contador1();

// 7.5. Fatorial e Fibonacci
console.log('5! =', fatorial(5));
console.log('Fib(6) recursivo =', fibRecursivo(6));
console.log('Fib(6) iterativo =', fibIterativo(6));

// 7.6. Operações em produtos
console.log('Preços:', getPrecos(produtos));
console.log('Produtos caros (>60):', filtrarCaros(produtos, 60));
console.log('Produtos ordenados por preço:', ordenarPorPreco(produtos));

// 7.7. Redução e async
console.log('Soma total [10,5,20,15]:', somaTotal([10,5,20,15]));
executarApos(500, function() {
    console.log('Callback executado após atraso');
});
prometerAlgo(5)
    .then(function(msg) {
        console.log('Sucesso:', msg);
    })
    .catch(function(err) {
        console.log('Falha:', err);
    });