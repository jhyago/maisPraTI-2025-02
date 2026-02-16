const formAdicionar = document.getElementById('form-adicionar')
const inputItem = document.getElementById('input-item')
const listaItens = document.getElementById('lista-itens')
const btnLimpar = document.getElementById('btn-limpar')
const checkboxComprar = document.getElementsByClassName('checkbox-comprar')
const contadorItens = document.getElementById('contador-itens')

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
        span.textContent = item.nome
        span.style.textDecoration = item.comprado ? 'line-through' : 'none'

        const checkbox = document.createElement('input')
        checkbox.type = 'checkbox'
        checkbox.setAttribute('class', 'checkbox-comprar')
        checkbox.setAttribute('name', 'comprado')
        checkbox.checked = item.comprado
        checkbox.addEventListener('change', () => {
            itens[index].comprado = checkbox.checked
            salvarDados()
            console.log(`Item "${item.nome}" foi marcado: ${checkbox.checked}`)
            span.style.textDecoration = item.comprado ? 'line-through' : 'none'
        })

        const btnRemover = document.createElement('button')
        btnRemover.textContent = 'X'
        btnRemover.title = 'Remover Item'

        btnRemover.addEventListener('click', () => {
            removerItem(index)
        })

        li.append(checkbox, span, btnRemover)
        listaItens.appendChild(li)
    })
    contarItens(itens)
}

formAdicionar.addEventListener('submit', (event) => {
    event.preventDefault()
    const novoItem = inputItem.value.trim()
    if(!novoItem) return

    itens.push({ nome: novoItem, comprado: false })
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

function contarItens(lista) {
    contadorItens.textContent = `Total de itens: ${lista.length}`
}

// Funcionalidades:

// Marcar como comprado - Salvar esse Estado no localStorage
// Contador de Itens - Mostrar quantos itens tem na lista, atualizando em tempo real
// Adicione filtros para itens 'comprados' e 'pedentes'
// Permita ordenar alfabeticamente ou por status