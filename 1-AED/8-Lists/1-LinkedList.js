// =========================
// IMPLEMENTAÇÃO E MELHORIA DE UMA LINKED LIST
// =========================

// Nó único da lista, contendo valor e referência ao próximo nó
class Node {
  constructor(valor) {
    this.valor = valor;   // armazena o dado do nó
    this.proximo = null;  // inicialmente não aponta para nenhum outro nó
  }
}

// Estrutura principal da lista encadeada
class LinkedList {
  constructor() {
    this.head = null;     // primeiro nó da lista (vazio no início)
    this.length = 0;      // contagem de elementos na lista
  }

  // Insere um novo valor no início da lista
  inserirInicio(valor) {
    const novoNo = new Node(valor);  // cria um novo nó com o valor
    novoNo.proximo = this.head;      // faz o novo nó apontar para o atual head
    this.head = novoNo;              // atualiza head para o novo nó
    this.length++;                    // incrementa tamanho da lista
  }

  // Insere um novo valor no fim da lista
  inserirFim(valor) {
    const novoNo = new Node(valor);  
    if (!this.head) {
      // se a lista estiver vazia, head passa a ser o novo nó
      this.head = novoNo;
    } else {
      // percorre até encontrar o último nó (aquele que tem proximo === null)
      let atual = this.head;
      while (atual.proximo) {
        atual = atual.proximo;
      }
      atual.proximo = novoNo; // faz o último nó apontar para o novo nó
    }
    this.length++;            // incrementa tamanho da lista
  }

  // Remove e retorna o valor do primeiro nó da lista
  removerInicio() {
    if (!this.head) return null;      // lista vazia: nada a remover
    const valorRemovido = this.head.valor;
    this.head = this.head.proximo;    // atualiza head para o próximo nó
    this.length--;                    // decrementa tamanho da lista
    return valorRemovido;             // retorna o valor removido
  }

  // Busca o índice (0-based) de um valor na lista; retorna -1 se não encontrar
  buscar(valor) {
    let atual = this.head;
    let indice = 0;
    // percorre até o fim da lista comparando valores
    while (atual) {
      if (atual.valor === valor) return indice;
      atual = atual.proximo;
      indice++;
    }
    return -1;  // não encontrado
  }

  // Retorna o número de elementos na lista
  tamanho() {
    return this.length;
  }

  // Converte a lista em string para fácil visualização
  imprimir() {
    let atual = this.head;
    let resultado = "";
    // percorre todos os nós concatenando seus valores
    while (atual) {
      resultado += `${atual.valor} → `;
      atual = atual.proximo;
    }
    resultado += "null";              // indica fim da lista
    console.log(resultado);
  }
}

// =========================
// EXEMPLO DE USO
// =========================
const lista = new LinkedList();

console.log("Inserindo no início: 10, 20, 30, 40");
lista.inserirInicio(10);
lista.inserirInicio(20);
lista.inserirInicio(30);
lista.inserirInicio(40);
lista.imprimir();               // 40 → 30 → 20 → 10 → null
console.log("Tamanho:", lista.tamanho());

console.log("\nInserindo no fim: 5, 0");
lista.inserirFim(5);
lista.inserirFim(0);
lista.imprimir();               // 40 → 30 → 20 → 10 → 5 → 0 → null
console.log("Tamanho:", lista.tamanho());

console.log("\nBuscando valor 20 na lista:", lista.buscar(20)); // 2
console.log("Buscando valor 99 na lista:", lista.buscar(99)); // -1

console.log("\nRemovendo do início:", lista.removerInicio());  // 40
lista.imprimir();               // 30 → 20 → 10 → 5 → 0 → null
console.log("Tamanho:", lista.tamanho());
