// =========================
// ÁRVORE N-ÁRIA (N-ary Tree)
// =========================

// Nó da árvore que armazena um valor e uma lista de filhos
class TreeNode {
    constructor(value) {
        this.value = value;    // valor do nó
        this.children = [];    // array com referências aos filhos
    }

    // Adiciona um filho (nó) ao array children
    addChild(node) {
        this.children.push(node);
    }

    // Remove filhos com valor igual ao informado
    removeChild(value) {
        this.children = this.children.filter(
            child => child.value !== value
        );
    }
}

// Estrutura de árvore com raiz e métodos de percurso
class Tree {
    constructor(rootValue) {
        this.root = new TreeNode(rootValue); // cria nó raiz
    }

    // Depth-First Search: visita raiz, depois recursivamente cada subárvore
    traverseDFS(callback) {
        // função interna para recursão
        function recurse(node) {
            callback(node);                 // processa nó atual
            // percorre cada filho recursivamente
            node.children.forEach(child => recurse(child));
        }
        recurse(this.root);                // inicia pela raiz
    }

    // Breadth-First Search: visita nível a nível usando fila
    traverseBFS(callback) {
        const queue = [this.root];        // fila inicial com raiz
        while (queue.length > 0) {
            const node = queue.shift();   // retira primeiro da fila
            callback(node);               // processa
            // adiciona todos os filhos ao final da fila
            node.children.forEach(child => queue.push(child));
        }
    }
}

// Exemplo de construção da árvore
const tree = new Tree('A');             // raiz A
const B = new TreeNode('B');
const C = new TreeNode('C');
const D = new TreeNode('D');
const E = new TreeNode('E');
const F = new TreeNode('F');
const G = new TreeNode('G');
const H = new TreeNode('H');

// Monta a hierarquia
//         A
//      /  |  \
//     B   C   D
//    / \   \
//   E   F   G
//        \
//         H

tree.root.addChild(B);
tree.root.addChild(C);
tree.root.addChild(D);
B.addChild(E);
B.addChild(F);
C.addChild(G);
F.addChild(H);

// Função para imprimir valor de um nó
function printNode(node) {
    console.log(node.value);
}

// Função para imprimir a árvore de forma hierárquica
function printTree(node, prefix = '', isLast = true) {
    // linha atual: prefixo + junção + valor
    console.log(
        prefix + (isLast ? '└── ' : '├── ') + node.value
    );
    const childrenCount = node.children.length;
    node.children.forEach((child, index) => {
        const ultimo = index === childrenCount - 1;
        // ajusta prefixo para próximos níveis
        const newPrefix = prefix + (isLast ? '    ' : '│   ');
        printTree(child, newPrefix, ultimo);
    });
}

// Exibe árvore formatada
console.log('\n--- Árvore N-ária ---');
printTree(tree.root);

// Demonstra DFS e BFS
console.log('\nDFS:');
tree.traverseDFS(printNode);
console.log('\nBFS:');
tree.traverseBFS(printNode);