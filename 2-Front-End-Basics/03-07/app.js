document.addEventListener('DOMContentLoaded', () => {
    const navToggle = document.getElementById('navToggle')
    const navMenu = document.getElementById('navMenu')
    navToggle.addEventListener('click', () => {
        navMenu.classList.toggle('open')
    })
})

const form = document.getElementById('contactForm')

form.querySelectorAll('input, textarea').forEach(field => {
    field.addEventListener('input', () => {
        field.classList.remove('error')
        field.nextElementSibling.textContent = ''
    })
})

form.addEventListener('submit', event => {
    event.preventDefault()

    let valido = true

    function showError(field, msg) {
        field.classList.add('error')
        field.nextElementSibling.textContent = msg
        valido = false
    }

    const nome = form.name.value.trim()
    if(nome.length < 3) {
        showError(form.name, 'Digite ao menos 3 caracteres.')
    }

    const email = form.email.value.trim()
    const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
    if(!regexEmail.test(email)){
        showError(form.email, 'E-mail inválido')
    }
})

