class Node {
    constructor(valor) {
        this.valor = valor
        this.anterior = null
        this.proximo = null
    } 
}

class DoublyLinkedList {
    constructor() {
        this.head = null
        this.tail = null
        this.length = 0
    }

    inserirInicio(v) {
        let novo = new Node(v)
        if(!this.head) {
            this.head = this.tail = novo
        } else {
            novo.proximo = this.head
            this.head.anterior = novo
            this.head = novo
        }

        this.length++
    }

    inserirFim(v) {
        let novo = new Node(v)
        if(!this.head) {
            this.head = this.tail = novo
        } else {
            this.tail.proximo = novo
            novo.anterior = this.tail
            this.tail = novo
        }

        this.length++
    }

    inserirEm(indice, valor) {
        if(indice < 0 || indice > this.length) throw RangeError("Índice está fora dos limites!")
        if(indice === 0) return this.inserirInicio(valor)
        if(indice === this.length) return this.inserirFim(valor)

        let atual = this.head

        for(let i = 0; i < indice; i++) {
            atual = atual.proximo
        }

        let novo = new Node(valor)
        let noAnterior = atual.anterior

        noAnterior.proximo = novo
        novo.anterior = noAnterior
        novo.proximo = atual
        atual.anterior = novo

        this.length++
    }

    removerEm(indice) {
        if(indice < 0 || indice > this.length) throw RangeError("Índice está fora dos limites!")

        let removido

        if(this.length === 1) {
            removido = this.head
            this.head = this.tail = null
        } else if(indice === 0) {
            removido = this.head
            this.head = this.head.proximo
            this.head.anterior = null
        } else if(indice === this.length - 1) {
            removido = this.tail
            this.tail = this.tail.anterior
            this.tail.proximo = null
        } else {
            let atual = this.head

            for(let i = 0; i < indice; i++) {
                atual = atual.proximo
            }

            removido = atual
            const { proximo, anterior } = atual
            anterior.proximo = proximo
            proximo.anterior = anterior
        }

        this.length--
    }
}
