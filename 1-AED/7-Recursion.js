// Exemplo 1: Fatorial de um número (n!)
// Definição: 0! = 1; para n > 0, n! = n * (n - 1)!
// A recursão reduz o problema até atingir o caso base (n <= 1).
function fatorial(n) {
    // Caso base: quando n é 0 ou 1, o resultado é 1, evitando chamadas infinitas
    if (n <= 1) {
        // Aqui a função devolve 1 e a pilha de chamadas começa a retornar
        return 1;
    }
    // Passo recursivo: a função chama a si mesma com n - 1
    // Cada chamada aguarda o resultado da próxima até atingir o caso base
    return n * fatorial(n - 1);
}
// Demonstração:
console.log('Fatorial de 5 =', fatorial(5)); // Expande: 5*4*3*2*1 = 120
console.log('Fatorial de 0 =', fatorial(0)); // Caso base: 1


// Exemplo 2: Sequência de Fibonacci (recursivo)
// Definição: fib(0)=0, fib(1)=1; para n>1, fib(n)=fib(n-1)+fib(n-2)
// Ineficiente pois recalcula muitos valores, mas didático para entender recursão dupla.
function fibRec(n) {
    // Casos base: retornam 0 e 1 sem chamar recursivamente
    if (n < 2) {
        return n;
    }
    // Passo recursivo: soma dos dois termos anteriores
    return fibRec(n - 1) + fibRec(n - 2);
}
console.log('Fibonacci recursivo de 6 =', fibRec(6)); // fib(6)=8


// Exemplo 3: Sequência de Fibonacci (iterativo para comparação)
// Mostra como a versão iterativa evita múltiplas chamadas aninhadas
function fibIter(n) {
    if (n < 2) return n;       // base rápida
    let a = 0, b = 1;           // inicializa dois primeiros termos
    // loop até atingir o n-ésimo termo
    for (let i = 2; i <= n; i++) {
        const temp = a + b;     // calcula próximo termo
        a = b;                  // desloca "janela": a vira b
        b = temp;               // b vira termo calculado
    }
    return b;                   // b contém fib(n)
}
console.log('Fibonacci iterativo de 6 =', fibIter(6)); // 8


// Exemplo 4: Soma de elementos de um array usando recursão
// Abordagem: somaArray(arr, i) = arr[i] + somaArray(arr, i+1), até i >= arr.length
function somaArray(arr, indice = 0) {
    // Caso base: se índice for além do fim, não há mais elementos para somar
    if (indice >= arr.length) {
        return 0;
    }
    // Passo recursivo: soma atual + soma de todos os elementos seguintes
    return arr[indice] + somaArray(arr, indice + 1);
}
console.log('Soma [1,2,3,4,5] =', somaArray([1,2,3,4,5])); // 15


// Exemplo 5: Busca binária recursiva em array ordenado
// Busca eficiente: reduz o intervalo pela metade a cada chamada
function buscaBinaria(arr, alvo, inicio = 0, fim = arr.length - 1) {
    // Caso base: intervalo inválido significa não encontrado
    if (inicio > fim) return -1;
    // Encontra índice do meio para comparar
    const meio = Math.floor((inicio + fim) / 2);
    // Se valor do meio corresponde, retorno imediato
    if (arr[meio] === alvo) {
        return meio;
    }
    // Se alvo menor, busca subarray esquerdo
    if (alvo < arr[meio]) {
        return buscaBinaria(arr, alvo, inicio, meio - 1);
    }
    // Caso contrário, busca subarray direito
    return buscaBinaria(arr, alvo, meio + 1, fim);
}
const arrOrd = [2,4,6,8,10,12,14];
console.log('Busca 10 em array =', buscaBinaria(arrOrd, 10)); // índice 4
console.log('Busca 7 em array =', buscaBinaria(arrOrd, 7));  // não encontrado (-1)

// Busca Binária sem recursividade
// Usa loop while para evitar recursão
function buscaBinariaIter(arr, target) {
    let left = 0;
    let right = arr.length - 1;
    while (left <= right) {
        // Calcula meio a cada iteração
        const mid = Math.floor((left + right) / 2);
        if (arr[mid] === target) {
            return mid;            // encontrado
        } else if (arr[mid] < target) {
            left = mid + 1;        // descarta metade esquerda
        } else {
            right = mid - 1;       // descarta metade direita
        }
    }
    return -1;                       // não encontrado
}

// Exemplo 6: Percorrendo uma estrutura de árvore simples via recursão
// O objeto possui value e children (array de nós filhos)
const tree = {
    value: 'root',
    children: [
        { value: 'A', children: [] },
        { value: 'B', children: [
            { value: 'B1', children: [] },
            { value: 'B2', children: [] }
        ]},
        { value: 'C', children: [] }
    ]
};
// Função recursiva: processa nó e, em seguida, cada um de seus filhos
function percorrerTree(node) {
    console.log(node.value);            // imprime valor do nó atual
    // itera sobre cada filho e chama recursivamente
    node.children.forEach(child => percorrerTree(child));
}
console.log('Percorrendo árvore:');
percorrerTree(tree);