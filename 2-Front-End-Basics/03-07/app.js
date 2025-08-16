// const button = document.getElementById("navToggle")
// const navMenu = document.getElementById("navMenu")

// button.addEventListener('click', () => {
//     navMenu.classList.toggle('open')
// })

document.addEventListener('DOMContentLoaded', () => {
    const navToggle = document.getElementById("navToggle")
    const navMenu = document.getElementById("navMenu")

    navToggle.addEventListener('click', () => {
        navMenu.classList.toggle('open')
    })
})