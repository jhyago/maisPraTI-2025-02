const formAdicionar = document.getElementById('form-adicionar')
const inputItem = document.getElementById('input-item')
const listaItens = document.getElementById('lista-itens')
const btnLimpar = document.getElementById('btn-limpar')

let itens = []

window.addEventListener('DOMContentLoaded', () => {
    const dados = localStorage.getItem('listaCompras')
    if(dados) {
        itens = JSON.parse(dados)
        renderizarLista()
    }
})

function salvarDados() {
    localStorage.setItem('listaCompras', JSON.stringify(itens))
}

function renderizarLista() {
    listaItens.innerHTML = ''
    itens.forEach((item, index) => {
        const li = document.createElement('li')

        const span = document.createElement('span')
        span.textContent = item

        const btnRemover = document.createElement('button')
        btnRemover.textContent = 'X'
        btnRemover.title = 'Remover Item'

        btnRemover.addEventListener('click', () => {
            removerItem(index)
        })

        li.append(span, btnRemover)
        listaItens.appendChild(li)
    })
}

formAdicionar.addEventListener('submit', (event) => {
    event.preventDefault()
    const novoItem = inputItem.value.trim()
    if(!novoItem) return

    itens.push(novoItem)
    salvarDados()
    renderizarLista()

    inputItem.value = ''
})

function removerItem(indice) {
    itens.splice(indice, 1)
    salvarDados()
    renderizarLista()
}

btnLimpar.addEventListener('click', () => {
    if(confirm('Deseja limpar toda a lista?')) {
        itens = []
        salvarDados()
        renderizarLista()
    }
})

// Funcionalidades:

// Marcar como comprado - Salvar esse Estado no localStorage
// Contador de Itens - Mostrar quantos itens tem na lista, atualizando em tempo real
// Adicione filtros para itens 'comprados' e 'pedentes'
// Permita ordenar alfabeticamente ou por status