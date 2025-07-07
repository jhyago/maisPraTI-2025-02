const formAdicionar = document.getElementById('form-adicionar');
const inputItem = document.getElementById('input-item');
const itensUl = document.getElementById('lista-itens');
const btnLimpar = document.getElementById('btn-limpar');
const contadorTotal = document.querySelector('.contador-total');
const contadorPendente = document.querySelector('.contador-pendentes');
const contadorComprado = document.querySelector('.contador-comprados');
const filtroStatus = document.getElementById('filtro-status');
const ordenar = document.getElementById('ordenar');

let listaItens = [];
let nextId = 0;

function salvarDados() {
  localStorage.setItem('listaCompras', JSON.stringify(listaItens));
}

function renderizarLista() {
  itensUl.innerHTML = '';
  let itensFiltrados = [...listaItens];
  const status = filtroStatus.value;

  if (status === 'pending')
    itensFiltrados = listaItens.filter((item) => !item.purchased);
  if (status === 'purchased')
    itensFiltrados = listaItens.filter((item) => item.purchased);

  const order = ordenar.value;
  if (order === 'alphabetical') {
    itensFiltrados.sort((a, b) => a.input.localeCompare(b.input));
  } else if (order === 'status') {
    itensFiltrados.sort((a, b) => a.purchased - b.purchased);
  }

  itensFiltrados.forEach((item) => {
    const li = document.createElement('li');
    const div = document.createElement('div');
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.name = 'confirmar';
    checkbox.checked = item.purchased;
    checkbox.title = 'Confirmar?';
    checkbox.addEventListener('click', () => {
      const itemOriginal = listaItens.find((i) => i.id === item.id);
      if (itemOriginal) {
        itemOriginal.purchased = !itemOriginal.purchased;
        salvarDados();
        contarItens();
        renderizarLista();
      }
    });

    const span = document.createElement('span');
    span.textContent = item.input;
    span.classList.add('item-text');

    const btnRemover = document.createElement('button');
    btnRemover.textContent = 'X';
    btnRemover.title = 'Remover Item';

    btnRemover.addEventListener('click', () => {
      removerItem(item.id);
    });

    // aplicando os elem na tela
    div.append(checkbox, span);
    li.append(div, btnRemover);
    itensUl.append(li);
  });
}

function removerItem(idRemover) {
  listaItens = listaItens.filter((item) => item.id !== idRemover);
  // os id's diferentes são mantidos na nova Array 'listaItens' e renderizados;
  // o id's iguais não são incluídos na nova array 'listaItens' (e são deletados)
  salvarDados();
  contarItens();
  renderizarLista();
}

function contarItens() {
  contadorTotal.textContent = `Total: ${listaItens.length}`;
  contadorPendente.textContent = `Pendentes: ${
    listaItens.filter((item) => !item.purchased).length
  }`;
  contadorComprado.textContent = `Comprados: ${
    listaItens.filter((item) => item.purchased).length
  }`;
}

// function nextID() {
//   if (listaItens.length === 0) return 1;
//   const maxId = Math.max(...listaItens.map((item) => item.id));
//   return maxId + 1;
// }

// EVENTOS

// Evento Refresh
window.addEventListener('DOMContentLoaded', () => {
  const dados = localStorage.getItem('listaCompras');
  if (dados) {
    listaItens = JSON.parse(dados);

    listaItens.length > 0
      ? (nextId = Math.max(...listaItens.map((item) => item.id)) + 1)
      : (nextId = 1);

    renderizarLista();
    contarItens();
  } else nextId = 1;
});

// Eventos de Filtro e de Ordenação
filtroStatus.addEventListener('change', renderizarLista);
ordenar.addEventListener('change', renderizarLista);

// Evento Principal
formAdicionar.addEventListener('submit', (event) => {
  event.preventDefault();
  const novoItem = inputItem.value.trim();
  if (!novoItem) return;

  listaItens.push({ id: nextId++, input: novoItem, purchased: false });
  salvarDados();
  renderizarLista();
  contarItens();

  inputItem.value = '';
});

// Evento limpar dados
btnLimpar.addEventListener('click', () => {
  if (confirm('Deseja limpar toda a lista?')) {
    listaItens = [];
    filtroStatus.value = 'all';
    ordenar.value = 'default';

    salvarDados();
    renderizarLista();
    contarItens();
    nextId = 1;
  }
});
