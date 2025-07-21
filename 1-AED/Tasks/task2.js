// 1) Validação de Datas
function ehDataValida(dia, mes, ano) {
  // Ajusta para índice de mês 1–12
  if (ano < 1 || mes < 1 || mes > 12 || dia < 1) return false;

  const diasNoMes = [31, (ano % 4 === 0 && ano % 100 !== 0) || (ano % 400 === 0) ? 29 : 28,
                     31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  return dia <= diasNoMes[mes - 1];
}

// Exemplo:
// console.log(ehDataValida(29, 2, 2024)); // true (bissexto)
// console.log(ehDataValida(31, 4, 2025)); // false (abril tem 30 dias)


// 2) Jogo de Adivinhação (navegador)
function jogarAdivinhacao() {
  const alvo = Math.floor(Math.random() * 100) + 1;
  let tentativas = 0;
  let palpite;

  while (palpite !== alvo) {
    palpite = Number(prompt('Adivinhe o número (1–100):'));
    tentativas++;
    if (palpite < alvo) {
      alert('Mais alto…');
    } else if (palpite > alvo) {
      alert('Mais baixo…');
    } else if (isNaN(palpite)) {
      alert('Por favor, digite um número válido.');
    } else {
      alert(`Parabéns! Você acertou em ${tentativas} tentativas.`);
    }
  }
}

// Para rodar no browser, basta chamar:
// jogarAdivinhacao();


// 3) Palavras Únicas
function palavrasUnicas(str) {
  const todas = str
    .toLowerCase()
    .split(/\s+/)
    .map(w => w.replace(/[.,!?;:]/g, ''))
    .filter(w => w.length > 0);

  const únicas = [];
  for (let w of todas) {
    if (!únicas.includes(w)) únicas.push(w);
  }
  return únicas;
}

// Exemplo:
// console.log(palavrasUnicas("olá olá mundo mundo!")); // ['olá', 'mundo']


// 4) Fatorial Recursivo
function fatorial(n) {
  if (n < 0) throw new Error('n não pode ser negativo');
  if (n === 0) return 1;
  return n * fatorial(n - 1);
}

// Exemplo:
// console.log(fatorial(5)); // 120
// console.log(fatorial(0)); // 1
// console.log(fatorial(-1)); // lança erro


// 5) Debounce
function debounce(fn, delay) {
  let timer = null;
  return function(...args) {
    clearTimeout(timer);
    timer = setTimeout(() => fn.apply(this, args), delay);
  };
}

// Exemplo de uso:
// window.addEventListener('resize', debounce(() => {
//   console.log('Resize finalizado!');
// }, 300));


// 6) Memoization
function memoize(fn) {
  const cache = new Map();
  return function(...args) {
    const chave = JSON.stringify(args);
    if (cache.has(chave)) {
      return cache.get(chave);
    }
    const resultado = fn.apply(this, args);
    cache.set(chave, resultado);
    return resultado;
  };
}

// Exemplo de uso:
// const fib = memoize(function(n) {
//   if (n < 2) return n;
//   return fib(n - 1) + fib(n - 2);
// });
// console.log(fib(40)); // rápido mesmo para n grande


// 7) Mapeamento e Ordenação
function nomesOrdenadosPorPreco(produtos) {
  // produtos: [ { nome: string, preco: number }, ... ]
  return produtos
    .slice()                           // copia para não mutar original
    .sort((a, b) => a.preco - b.preco)
    .map(p => p.nome);
}

// Exemplo:
// const produtos = [
//   { nome: 'Caneta', preco: 3.5 },
//   { nome: 'Caderno', preco: 12 },
//   { nome: 'Lápis', preco: 1.2 }
// ];
// console.log(nomesOrdenadosPorPreco(produtos)); // ['Lápis', 'Caneta', 'Caderno']


// 8) Agrupamento por Propriedade
function agruparVendasPorCliente(vendas) {
  // vendas: [ { cliente: string, total: number }, ... ]
  return vendas.reduce((acc, { cliente, total }) => {
    acc[cliente] = (acc[cliente] || 0) + total;
    return acc;
  }, {});
}

// Exemplo:
// const vendas = [
//   { cliente: 'Ana', total: 100 },
//   { cliente: 'Bruno', total: 50 },
//   { cliente: 'Ana', total: 25 }
// ];
// console.log(agruparVendasPorCliente(vendas)); 
// // { Ana: 125, Bruno: 50 }


// 9) Conversão Entre Formatos
function paresParaObjeto(pares) {
  // pares: [ [chave, valor], ... ]
  return pares.reduce((obj, [chave, valor]) => {
    obj[chave] = valor;
    return obj;
  }, {});
}

function objetoParaPares(obj) {
  return Object.entries(obj);
}

// Exemplo:
// const pares = [['a', 1], ['b', 2]];
// const obj = paresParaObjeto(pares);
// console.log(obj); // { a: 1, b: 2 }
// console.log(objetoParaPares(obj)); // [['a',1], ['b',2]]