document.addEventListener('DOMContentLoaded', function () {
  const swiper = new Swiper('.swiper', {
    direction: 'horizontal',
    loop: true,
    pagination: {
      el: '.swiper-pagination',
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
    scrollbar: {
      el: '.swiper-scrollbar',
    },
  });

  document.addEventListener('keydown', function (event) {
    if (event.key === 'ArrowLeft') {
      swiper.slidePrev();
      removeFocusFromNavButtons();
    } else if (event.key === 'ArrowRight') {
      swiper.slideNext();
      removeFocusFromNavButtons();
    }
  });

  function removeFocusFromNavButtons() {
    const navButtons = document.querySelectorAll('.swiper-button-prev, .swiper-button-next');
    navButtons.forEach(button => button.blur());
  }

  const gameInput = document.getElementById('gameInput');
  const searchIcon = document.getElementById('searchIcon');

 
  gameInput.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
      const searchGame = gameInput.value.trim().toUpperCase();
      
      switch (searchGame) {
        case 'MARIO':
          swiper.slideTo(0); 
          break;
        case 'POKEMON':
          swiper.slideTo(1); 
          break;
        case 'ZELDA':
          swiper.slideTo(2); 
          break;
        default:
          alert('Jogo não encontrado.');
          break;
      }

      gameInput.value = '';
    }
  });

  
    searchIcon.addEventListener('click', function () {
    const searchGame = gameInput.value.trim().toUpperCase();

    switch (searchGame) {
      case 'MARIO':
        swiper.slideTo(0); 
        break;
      case 'POKEMON':
        swiper.slideTo(1); 
        break;
      case 'ZELDA':
        swiper.slideTo(2); 
        break;
      default:
        alert('Jogo não encontrado.');
        break;
    }

    gameInput.value = '';
  });
});

document.addEventListener('DOMContentLoaded', function () {
  const openEmailModalBtn = document.getElementById('openEmailModal');
  const emailModal = document.getElementById('emailModal');
  const closeEmailModalBtn = emailModal.querySelector('.close');
  const emailForm = document.getElementById('emailForm');

  
  openEmailModalBtn.addEventListener('click', function () {
    emailModal.style.display = 'block';
  });

  
  closeEmailModalBtn.addEventListener('click', function () {
    emailModal.style.display = 'none';
  });

  
  window.addEventListener('click', function (event) {
    if (event.target === emailModal) {
      emailModal.style.display = 'none';
    }
  });

  
  emailForm.addEventListener('submit', function (event) {
    event.preventDefault(); 
    alert('Email enviado com sucesso!');
    emailModal.style.display = 'none'; 
    emailForm.reset(); 
  });
});