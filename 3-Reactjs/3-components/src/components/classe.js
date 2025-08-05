class Animal {
    constructor(nome) {
        this.nome = nome
        console.log('Animal foi instanciado')
    }

    falar() {
        console.log(`${this.nome} faz barulho`)
    }
}

class Cachorro extends Animal {
    constructor(nome, raca) {
        super(nome)
        this.raca = raca
        console.log('Cachorro criado')
    }

    falar() {
        console.log(`${this.nome} faz au au`)
    }
}

let meuPet = new Cachorro('João', 'Husky')

meuPet.falar()