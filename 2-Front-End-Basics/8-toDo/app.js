// Obtém referências aos elementos do DOM
const formAdicionar = document.getElementById('form-adicionar');
const inputItem = document.getElementById('input-item');
const listaItens = document.getElementById('lista-itens');
const btnLimpar = document.getElementById('btn-limpar');
const seletorFiltro = document.getElementById('filtro');
const seletorOrdenar = document.getElementById('ordenar');
const contadorElem = document.querySelector('#contador span');

// Array que armazenará os itens (cada um é { text, purchased })
let itens = [];

// Ao carregar a página, tenta restaurar dados do localStorage
window.addEventListener('DOMContentLoaded', () => {
  const dados = localStorage.getItem('listaCompras');
  if (dados) {
    itens = JSON.parse(dados);
    renderizarLista();
  }
});

// Salva a lista no localStorage
function salvarDados() {
  localStorage.setItem('listaCompras', JSON.stringify(itens));
}

// Atualiza o contador de itens
function atualizarContador() {
  contadorElem.textContent = itens.length;
}

// Renderiza a lista de acordo com filtro e ordenação atuais
function renderizarLista() {
  listaItens.innerHTML = '';           // limpa o container
  const filtro = seletorFiltro.value;  // "all", "pending" ou "purchased"
  const ordenar = seletorOrdenar.value; // "insertion", "alphabetical", "status"

  // Filtra itens
  let itensFiltrados = itens.filter(item => {
    if (filtro === 'pending') return !item.purchased;
    if (filtro === 'purchased') return item.purchased;
    return true;
  });

  // Ordena itens
  if (ordenar === 'alphabetical') {
    itensFiltrados.sort((a, b) => a.text.localeCompare(b.text));
  } else if (ordenar === 'status') {
    // Comprados por último
    itensFiltrados.sort((a, b) => a.purchased - b.purchased);
  }
  // "insertion" mantém a ordem original do array

  // Para cada item filtrado, cria um <li>
  itensFiltrados.forEach((item, index) => {
    const li = document.createElement('li');
    li.className = item.purchased ? 'purchased' : ''; // adiciona classe se comprado

    // Checkbox para marcar comprado/pendente
    const chk = document.createElement('input');
    chk.type = 'checkbox';
    chk.checked = item.purchased;
    chk.addEventListener('change', () => {
      item.purchased = chk.checked; // atualiza estado
      salvarDados();
      renderizarLista();
    });

    // Texto do item
    const span = document.createElement('span');
    span.textContent = item.text;

    // Botão de remover
    const btnRemover = document.createElement('button');
    btnRemover.textContent = 'X';
    btnRemover.addEventListener('click', () => {
      const posOriginal = itens.indexOf(item);
      itens.splice(posOriginal, 1);
      salvarDados();
      renderizarLista();
    });

    // Monta o <li>
    li.appendChild(chk);
    li.appendChild(span);
    li.appendChild(btnRemover);
    listaItens.appendChild(li);
  });

  atualizarContador(); // atualiza o número exibido
}

// Ao enviar o formulário, adiciona novo item
formAdicionar.addEventListener('submit', evento => {
  evento.preventDefault();
  const texto = inputItem.value.trim();
  if (texto === '') return;           // não adiciona vazio

  itens.push({ text: texto, purchased: false });
  salvarDados();
  renderizarLista();

  inputItem.value = '';               // limpa o campo
});

// Limpa toda a lista após confirmação
btnLimpar.addEventListener('click', () => {
  if (confirm('Deseja realmente limpar toda a lista?')) {
    itens = [];
    salvarDados();
    renderizarLista();
  }
});

// Re-renderiza sempre que filtro ou ordenação mudarem
seletorFiltro.addEventListener('change', renderizarLista);
seletorOrdenar.addEventListener('change', renderizarLista);