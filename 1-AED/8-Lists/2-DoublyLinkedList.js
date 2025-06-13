// =========================
// LISTA DUPLAMENTE ENCADEADA (Doubly Linked List)
// =========================

// Nó duplamente encadeado: guarda valor e ponteiros para o nó anterior e o próximo
class Node {
    constructor(valor) {
        this.valor = valor;       // armazena o valor contido no nó
        this.anterior = null;     // referência ao nó anterior (antes deste)
        this.proximo = null;      // referência ao próximo nó (depois deste)
    }
}

// Estrutura da lista com referências ao primeiro (head) e último (tail) nós
class DoublyLinkedList {
    constructor() {
        this.head = null;         // ponteiro para o primeiro nó da lista
        this.tail = null;         // ponteiro para o último nó da lista
        this.length = 0;          // contador de nós na lista
    }

    // ====================================================
    // Insere valor no início da lista
    // ====================================================
    inserirInicio(valor) {
        // 1) Cria um novo nó com o valor fornecido
        const novoNo = new Node(valor);

        // 2) Se a lista estiver vazia (sem head), novo nó é único
        if (!this.head) {
            this.head = novoNo;  // head aponta para o novo nó
            this.tail = novoNo;  // tail também aponta para o novo nó
        } else {
            // 3) Liga o novo nó ao antigo head
            novoNo.proximo = this.head;   // seta o próximo do novo nó para o atual head
            this.head.anterior = novoNo;  // define o anterior do antigo head para o novo nó
            this.head = novoNo;           // atualiza head para o novo nó
        }

        // 4) Atualiza o tamanho da lista
        this.length++;
    }

    // ====================================================
    // Insere valor no fim da lista
    // ====================================================
    inserirFim(valor) {
        // 1) Cria um novo nó com o valor desejado
        const novoNo = new Node(valor);

        // 2) Se lista vazia, head e tail são o novo nó
        if (!this.tail) {
            this.head = novoNo;  // head aponta para o novo nó
            this.tail = novoNo;  // tail aponta para o novo nó
        } else {
            // 3) Anexa o novo nó após o atual tail
            this.tail.proximo = novoNo;  // o próximo do tail atual aponta para novo nó
            novoNo.anterior = this.tail; // o anterior do novo nó aponta para tail atual
            this.tail = novoNo;          // atualiza tail para o novo nó
        }

        // 4) Incrementa contagem de nós
        this.length++;
    }

    // ====================================================
    // Insere valor em posição específica (índice 0-based)
    // ====================================================
    inserirEm(indice, valor) {
        // Valida índice
        if (indice < 0 || indice > this.length) {
            throw new RangeError('Índice fora dos limites');
        }
        // Se índice 0, reaproveita inserirInicio
        if (indice === 0) {
            return this.inserirInicio(valor);
        }
        // Se índice igual ao tamanho, insere no fim
        if (indice === this.length) {
            return this.inserirFim(valor);
        }

        // 1) Percorre a lista até o índice desejado
        let atual = this.head;
        for (let i = 0; i < indice; i++) {
            atual = atual.proximo;  // avança para o próximo nó
        }

        // 2) Cria o novo nó e ajusta ponteiros
        const novoNo = new Node(valor);
        const anterior = atual.anterior;  // nó que vem antes do atual

        // 3) Reencadeia: anterior → novoNo → atual
        anterior.proximo = novoNo;  // anterior aponta para novo nó
        novoNo.anterior = anterior; // novo nó aponta para anterior
        novoNo.proximo = atual;     // novo nó aponta para atual
        atual.anterior = novoNo;    // atual aponta para novo nó

        // 4) Incrementa contagem
        this.length++;
    }

    // ====================================================
    // Remove e retorna valor do nó em posição específica
    // ====================================================
    removerEm(indice) {
        // Valida índice
        if (indice < 0 || indice >= this.length) {
            throw new RangeError('Índice fora dos limites');
        }

        let remover;  // nó a ser removido

        // Caso 1: lista com único nó
        if (this.length === 1) {
            remover = this.head;        // nó único
            this.head = null;          // limpa head
            this.tail = null;          // limpa tail
        }
        // Caso 2: remover no início (head)
        else if (indice === 0) {
            remover = this.head;              // nó a remover
            this.head = this.head.proximo;    // head aponta para segundo nó
            this.head.anterior = null;        // ajusta anterior do novo head
        }
        // Caso 3: remover no fim (tail)
        else if (indice === this.length - 1) {
            remover = this.tail;              // nó a remover
            this.tail = this.tail.anterior;   // tail aponta para penúltimo
            this.tail.proximo = null;         // ajusta próximo do novo tail
        }
        // Caso 4: remover no meio
        else {
            let atual = this.head;
            for (let i = 0; i < indice; i++) {
                atual = atual.proximo;  // avança até o índice
            }
            remover = atual;            // nó a remover

            // Guarda nós vizinhos
            const anterior = atual.anterior;
            const proximo  = atual.proximo;

            // Desencadeia 'atual'
            anterior.proximo = proximo;  // anterior liga ao próximo
            proximo.anterior   = anterior;// próximo liga ao anterior
        }

        // Atualiza contagem e retorna valor removido
        this.length--;
        return remover.valor;
    }

    // ====================================================
    // Imprime a lista da frente para trás
    // ====================================================
    imprimirFrente() {
        let atual = this.head;  // começa no primeiro nó
        let str = '';           // string acumuladora
        while (atual) {
            str += `${atual.valor} ⇄ `;  // concatena valor e seta bi-direcional
            atual = atual.proximo;       // avança para o próximo
        }
        str += 'null';                  // marca o fim da lista
        console.log(str);
    }

    // ====================================================
    // Imprime a lista de trás para frente
    // ====================================================
    imprimirAtras() {
        let atual = this.tail;  // começa no último nó
        let str = '';
        while (atual) {
            str += `${atual.valor} ⇄ `;  // concatena valor
            atual = atual.anterior;      // recua para o anterior
        }
        str += 'null';                  // fim da impressão
        console.log(str);
    }

    // ====================================================
    // Retorna o número de nós na lista
    // ====================================================
    tamanho() {
        return this.length;     // simplesmente retorna a contagem
    }
}

// =========================
// EXEMPLO DE USO
// =========================
const listaDupla = new DoublyLinkedList();

console.log('--- Inserindo no início: A, B, C ---');
listaDupla.inserirInicio('A');
listaDupla.inserirInicio('B');
listaDupla.inserirInicio('C');
listaDupla.imprimirFrente(); // C ⇄ B ⇄ A ⇄ null
listaDupla.imprimirAtras();  // A ⇄ B ⇄ C ⇄ null

console.log('\n--- Inserindo no fim: D, E ---');
listaDupla.inserirFim('D');
listaDupla.inserirFim('E');
listaDupla.imprimirFrente(); // C ⇄ B ⇄ A ⇄ D ⇄ E ⇄ null

console.log('\n--- Inserindo no índice 2: X ---');
listaDupla.inserirEm(2, 'X');
listaDupla.imprimirFrente(); // C ⇄ B ⇄ X ⇄ A ⇄ D ⇄ E ⇄ null

console.log('\n--- Removendo índice 3 ---');
console.log('Removido:', listaDupla.removerEm(3));
listaDupla.imprimirFrente(); // C ⇄ B ⇄ X ⇄ D ⇄ E ⇄ null

console.log('\nTamanho da lista:', listaDupla.tamanho());