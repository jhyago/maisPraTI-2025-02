const porQueUtilizar = document.getElementById('porQueUtilizar')
const principaisRecursos = document.getElementById('principaisRecursos')
const recursosRelacionados = document.getElementById('recursosRelacionados')
const conteudo = document.getElementById('conteudo')


porQueUtilizar.addEventListener('click', () => {
    conteudo.innerHTML = ''

    const ul = document.createElement('ul')

    const motivos = [
        'Item 1',
        'Item 2',
        'Item 3',
        'Item 4'
    ]

    motivos.forEach(motivo => {
        const li = document.createElement('li')
        li.textContent = motivo
        ul.appendChild(li)
    })

    conteudo.appendChild(ul)
})

principaisRecursos.addEventListener('click', () => {
    conteudo.innerHTML = ''

    const ul = document.createElement('ul')

    const motivos = [
        'Componentes, JSX e Props',
        'Estado',
        'Hooks',
        'Renderização dinâmica'
    ]

    motivos.forEach(motivo => {
        const li = document.createElement('li')
        li.textContent = motivo
        ul.appendChild(li)
    })

    conteudo.appendChild(ul)
})

recursosRelacionados.addEventListener('click', () => {
    conteudo.innerHTML = ''

    const ul = document.createElement('ul')

    const motivos = [
        'Item 5',
        'Item 6',
        'Item 7',
        'Item 8'
    ]

    motivos.forEach(motivo => {
        const li = document.createElement('li')
        li.textContent = motivo
        ul.appendChild(li)
    })

    conteudo.appendChild(ul)
})