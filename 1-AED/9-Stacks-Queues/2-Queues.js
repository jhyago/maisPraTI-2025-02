// =========================
// FILA (Queue) COM LISTA ENCADEADA
// =========================

// Nó simples para a fila, com valor e ponteiro para o próximo
class QNode {
  constructor(value) {
    this.value = value; // dado armazenado
    this.next = null;   // referência ao próximo nó na fila
  }
}

// Implementação de fila FIFO usando lista encadeada
class Queue {
  constructor() {
    this.front = null; // início da fila (remoção)
    this.rear  = null; // fim da fila (inserção)
    this.size  = 0;    // contagem de elementos
  }

  // Enqueue: adiciona valor ao fim da fila
  enqueue(value) {
    const novoNo = new QNode(value); // cria nó
    if (this.rear) {
      this.rear.next = novoNo;       // liga novo nó após o antigo rear
    }
    this.rear = novoNo;              // atualiza rear
    if (!this.front) {
      this.front = novoNo;           // se fila vazia, front também aponta para novo nó
    }
    this.size++;                     // incrementa contagem
  }

  // Dequeue: remove e retorna valor do início da fila
  dequeue() {
    if (!this.front) return null;   // fila vazia
    const valorRemovido = this.front.value;
    this.front = this.front.next;   // avança front
    if (!this.front) {              // se ficou vazia
      this.rear = null;             // rear também é null
    }
    this.size--;                    // decrementa contagem
    return valorRemovido;           // retorna o valor removido
  }

  // Peek: retorna valor do início sem remover
  peek() {
    return this.front ? this.front.value : null;
  }

  // isEmpty: verifica se a fila está vazia
  isEmpty() {
    return this.size === 0;
  }

  // imprimir: mostra todos os valores da fila
  imprimir() {
    let atual = this.front;
    let str = '';
    while (atual) {
      str += `${atual.value} <- `; // seta indica direção de saída
      atual = atual.next;
    }
    console.log(str + 'null');
  }

  // getSize: retorna número de elementos
  getSize() {
    return this.size;
  }
}

// =========================
// EXEMPLO DE USO - Queue
// =========================
const fila = new Queue();
console.log('\n--- Queue ---');
fila.enqueue(1);
fila.enqueue(2);
fila.enqueue(3);
fila.imprimir();                // 1 <- 2 <- 3 <- null
console.log('Peek:', fila.peek());
console.log('Dequeue:', fila.dequeue());
fila.imprimir();                // 2 <- 3 <- null
console.log('Size:', fila.getSize());
console.log('Empty?:', fila.isEmpty());