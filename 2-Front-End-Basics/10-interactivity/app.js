// Aguarda o carregamento completo do DOM antes de acessar elementos
document.addEventListener('DOMContentLoaded', () => {
  const navToggle = document.getElementById('navToggle'); // Botão hamburger
  const navMenu   = document.getElementById('navMenu');   // Lista de navegação

  // Ao clicar no botão, alterna abertura do menu e atualiza aria-expanded
  navToggle.addEventListener('click', () => {
    const isOpen = navMenu.classList.toggle('open');     // Adiciona/remove classe
    navToggle.setAttribute('aria-expanded', isOpen);     // Atualiza atributo para leitores de tela

    // Atualiza ícone visual: ☰ quando fechado, × quando aberto
    navToggle.innerHTML = isOpen ? '&times;' : '&#9776;';
    navToggle.setAttribute('aria-label', isOpen ? 'Fechar menu' : 'Abrir menu');
  });
});