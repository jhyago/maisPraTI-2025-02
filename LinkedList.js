class Node {
    constructor(valor) {
        this.valor = valor
        this.proximo = null
    } 
}

class LinkedList {
    constructor(){
        this.head = null
    }

    inserirInicio(v) {
        let novo = new Node(v)
        novo.proximo = this.head
        this.head = novo
    }

    imprimir() {
        let atual = this.head
        let str = ""
        while(atual) {
            str += atual.valor + "->"
            atual = atual.proximo
        }
        str += 'null'
        console.log(str)
    }
}

let lista = new LinkedList()

lista.inserirInicio(10)
lista.inserirInicio(9)

lista.imprimir()