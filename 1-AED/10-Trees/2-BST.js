// =========================
// ÁRVORE BINÁRIA DE BUSCA (Binary Search Tree)
// =========================

// Nó da BST: armazena valor e referências para subárvores esquerda e direita\ 
class BSTNode {
    constructor(value) {
        this.value = value;   // valor armazenado no nó
        this.left = null;     // ponteiro para subárvore esquerda
        this.right = null;    // ponteiro para subárvore direita
    }
}

// Estrutura da BST com métodos de inserção, travessia e busca
class BinarySearchTree {
    constructor() {
        this.root = null;     // raiz inicial vazia
    }

    // ====================
    // Inserção de valor
    // ====================
    insert(value) {
        const newNode = new BSTNode(value);  // cria nó a inserir
        if (!this.root) {
            // se árvore vazia, raiz recebe o novo nó
            this.root = newNode;
        } else {
            // caso contrário, insere recursivamente
            this._insertNode(this.root, newNode);
        }
    }

    // Método auxiliar recursivo para inserir nós
    _insertNode(node, newNode) {
        // se valor menor que nó atual, vai à subárvore esquerda
        if (newNode.value < node.value) {
            if (!node.left) {
                node.left = newNode;         // insere aqui
            } else {
                this._insertNode(node.left, newNode);
            }
        } else {
            // valor maior ou igual, vai à subárvore direita
            if (!node.right) {
                node.right = newNode;
            } else {
                this._insertNode(node.right, newNode);
            }
        }
    }

    // ====================
    // Travessia Inorder
    // ====================
    // Visita: esquerda → nó → direita, produz valores ordenados
    inorderTraversal(node = this.root) {
        if (node) {
            this.inorderTraversal(node.left);
            console.log(node.value);
            this.inorderTraversal(node.right);
        }
    }

    // ====================
    // Busca de valor
    // ====================
    // Retorna verdadeiro se encontrar valor, ou falso caso contrário
    search(value, node = this.root) {
        if (!node) {
            return false;      // subárvore vazia, não encontrado
        }
        if (value === node.value) {
            return true;       // valor encontrado
        }
        // se valor menor, busca esquerda; senão busca direita
        return value < node.value
            ? this.search(value, node.left)
            : this.search(value, node.right);
    }
}

// =========================
// EXEMPLO DE USO - BST
// =========================
const bst = new BinarySearchTree();
console.log('--- BST ---');
// Insere vários valores
[10, 5, 12, 3, 6, 11, 7, 10, 10].forEach(v => bst.insert(v));

console.log('Travessia inorder (ordenada):');
bst.inorderTraversal();  // exibe valores em ordem crescente

console.log('Busca 3:', bst.search(3));  // true
console.log('Busca 8:', bst.search(8));  // false