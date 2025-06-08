// =========================
// PILHA (Stack) COM LISTA ENCADEADA
// =========================

// Nó simples para a pilha, armazena valor e referência para o próximo
class SNode {
    constructor(value) {
        this.value = value;   // dado do nó
        this.next = null;     // ponteiro para o próximo nó na pilha
    }
}

// Implementação de pilha LIFO usando lista encadeada
class StackLinked {
    constructor() {
        this.top = null;      // topo da pilha (nó mais recente)
        this.size = 0;        // quantidade de elementos na pilha
    }

    // push: insere um valor no topo da pilha
    push(value) {
        const newNode = new SNode(value); // cria novo nó
        newNode.next = this.top;          // faz o novo nó apontar para o antigo topo
        this.top = newNode;               // atualiza topo para o novo nó
        this.size++;                      // incrementa contagem
    }

    // pop: remove e retorna o valor do topo
    pop() {
        if (!this.top) return null;      // se a pilha estiver vazia
        const poppedValue = this.top.value; // salva valor do topo
        this.top = this.top.next;        // atualiza topo para o próximo nó
        this.size--;                     // decrementa contagem
        return poppedValue;              // retorna valor removido
    }

    // peek: retorna o valor do topo sem remover
    peek() {
        return this.top ? this.top.value : null;
    }

    // isEmpty: verifica se a pilha está vazia
    isEmpty() {
        return this.size === 0;
    }

    // getSize: retorna número de elementos
    getSize() {
        return this.size;
    }

    // imprimir: exibe os elementos da pilha de cima para baixo
    imprimir() {
        let atual = this.top;
        let str = '';
        while (atual) {
            str += `${atual.value} ↑ `; // seta para cima indica direção LIFO
            atual = atual.next;
        }
        console.log(str + 'null');
    }
}

// Exemplo de uso da pilha encadeada
const pilhaEncadeada = new StackLinked();
console.log('--- Stack (Linked List) ---');
pilhaEncadeada.push(10);
pilhaEncadeada.push(20);
pilhaEncadeada.push(30);
pilhaEncadeada.imprimir();         // 30 ↑ 20 ↑ 10 ↑ null
console.log('Peek:', pilhaEncadeada.peek());
console.log('Pop:', pilhaEncadeada.pop());
pilhaEncadeada.imprimir();         // 20 ↑ 10 ↑ null
console.log('Size:', pilhaEncadeada.getSize());
console.log('Empty?:', pilhaEncadeada.isEmpty());

// =========================
// PILHA (Stack) COM ARRAY
// =========================

// Implementação de pilha LIFO usando array interno
class StackArray {
    constructor() {
        this.items = [];   // array armazena elementos
    }

    // push: adiciona elemento no fim do array (topo)
    push(element) {
        this.items.push(element); // método nativo push
    }

    // pop: remove e retorna o último elemento do array
    pop() {
        return this.items.pop() || null; // pop retorna undefined se vazio, converte para null
    }

    // peek: inspeção sem remoção
    peek() {
        return this.items.length > 0 ? this.items[this.items.length - 1] : null;
    }

    // isEmpty: verifica se o array está vazio
    isEmpty() {
        return this.items.length === 0;
    }

    // getSize: tamanho atual da pilha
    getSize() {
        return this.items.length;
    }

    // imprimir: mostra todos os elementos da pilha do topo para base
    imprimir() {
        let str = '';
        for (let i = this.items.length - 1; i >= 0; i--) {
            str += `${this.items[i]} ↑ `; // seta para cima indica topo
        }
        console.log(str + 'null');
    }
}

// Exemplo de uso da pilha baseada em array
const pilhaArray = new StackArray();
console.log('--- Stack (Array) ---');
pilhaArray.push('A');
pilhaArray.push('B');
pilhaArray.push('C');
pilhaArray.imprimir();             // C ↑ B ↑ A ↑ null
console.log('Peek:', pilhaArray.peek());
console.log('Pop:', pilhaArray.pop());
pilhaArray.imprimir();             // B ↑ A ↑ null
console.log('Size:', pilhaArray.getSize());
console.log('Empty?:', pilhaArray.isEmpty());