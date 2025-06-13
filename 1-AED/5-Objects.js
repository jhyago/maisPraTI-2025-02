// =========================
// EXEMPLOS DE OBJETOS, FUNÇÕES E ITERAÇÕES
// =========================

// 1. OBJETOS LITERAIS COM MÉTODOS
// -------------------------------
let serie = {
    nome: "The Boys",                             // nome da série
    genero: ["Ação", "Paródia", "Heróis"],     // array de gêneros
    nrTemporadas: 4,                                // número de temporadas
    status: "Em andamento",                       // status de exibição
    classificacao: 18,                              // classificação etária
    nrEpisodios: {                                  // objeto aninhado com número de episódios
        temp1: 8,
        temp2: 8,
        temp3: 8,
        temp4: 8
    },
    // Método que retorna string com nome e classificação
    mostrarCaracteristicas: function() {
        return `Série: ${this.nome} | Classificação: +${this.classificacao}`;
    }
};
console.log(serie.mostrarCaracteristicas());

let livro = {
    titulo: "O Hobbit",                           // título do livro
    autor: "J. R. R. Tolkien",                   // autor
    ano: 1937,                                      // ano de publicação
    mostrarCaracteristicas: function() {
        // Método que retorna título e autor
        return `${this.titulo} foi escrito por ${this.autor}`;
    }
};
console.log(livro.mostrarCaracteristicas());

let carro = {
    nome: 'Herby',                                  // apelido do carro
    modelo: 'Marea',                                // modelo
    velocidadeMaxima: 350,                          // em km/h
    ano: 2005,                                      // ano de fabricação
    acelerar() {                                    // método abreviado
        return "Acelerando com pé no porão!";    // retorna mensagem
    }
};
console.log(carro.acelerar());

// 2. CRIAÇÃO DINÂMICA E PROTOTYPES
// --------------------------------
// Função construtora para criar objetos 'Computador'
function Computador(processador, gpu, ram, armazenamento) {
    this.processador = processador;
    this.gpu = gpu;
    this.ram = ram;
    this.armazenamento = armazenamento;
}
// Adiciona método ao prototype para compartilhar entre instâncias
Computador.prototype.desligar = function() {
    return `O computador ${this.processador} desligou.`;
};
// Instancia
let pc = new Computador("i9", "RTX4090", "16GB", "1TB SSD");
console.log(pc.desligar());

// 3. FACTORY FUNCTIONS
// --------------------
function criarJogo(titulo, genero, ano, empresa) {
    return {
        titulo,                                      // shorthand ES6
        genero,
        ano,
        empresa,
        jogar() {                                    // método shorthand
            return `Jogando ${this.titulo}...`;
        }
    };
}
let jogo = criarJogo("Final Fantasy VII", "RPG", 1997, "Square Soft");
console.log(jogo.jogar());

// 4. PROPRIEDADES COMPUTADAS E SPREAD
// -----------------------------------
let chave = 'velocidadeMax';                      // nome de propriedade dinâmico
let moto = {
    motor: '1000cc',
    tipo: 'Esportiva',
    [chave]: '280 km/h'                            // propriedade computada
};
// Clona objeto e adiciona nova propriedade com spread
let motoAtualizada = { ...moto, cor: 'Branca' };
console.log(motoAtualizada);

// 5. DESTRUTURING DE OBJETOS
// --------------------------
let { titulo, autor } = livro;                    // extrai propriedades do objeto 'livro'
console.log(`Título: ${titulo} | Autor: ${autor}`);

// 6. ITERAÇÃO EM OBJETOS
// ----------------------
// 6.1 Object.keys, values e entries
console.log('Chaves de livro:', Object.keys(livro));
console.log('Valores de livro:', Object.values(livro));
console.log('Entradas de livro:', Object.entries(livro));

// 6.2 for...in em professor.grades (média de notas)
let professor = {
    name: "Tony Stark",
    subject: "Physics",
    grades: {
        student1: [10.0, 8.0],
        student2: 8.0,
        student3: 7.0
    }
};
let soma = 0, count = 0;
for (let aluno in professor.grades) {
    let notas = professor.grades[aluno];
    let media = Array.isArray(notas)
        ? notas.reduce((a,b)=>a+b,0)/notas.length
        : notas;
    soma += media;
    count++;
}
let mediaTurma = soma / count;
console.log(`Média da turma: ${mediaTurma.toFixed(2)}`);

// 7. ITERAÇÃO EM ARRAYS
// ---------------------
// 7.1 for...of em array multidimensional
let jogadores = [['Pelé','Romário'], 'CR7', 'Messi'];
for (let item of jogadores) {
    console.log('Jogador/Grupo:', item);
}

// 7.2 forEach para contagem de gêneros de filmes
const movies = [
    { title: "Tropa de Elite", genre: "Militar" },
    { title: "Top Gun", genre: "Ação" },
    { title: "Interestelar", genre: "Sci-Fi" },
    { title: "Senhor dos Anéis", genre: "Aventura" },
    { title: "Poderoso Chefão", genre: "Máfia" },
    { title: "Vingadores: Ultimato", genre: "Heróis" },
    { title: "Deadpool & Wolverine", genre: "Heróis" }
];
let genreCount = {};
movies.forEach(movie => {
    // Se já existe, incrementa; senão, inicializa com 1
    genreCount[movie.genre] = (genreCount[movie.genre] || 0) + 1;
});
for (let g in genreCount) {
    console.log(`Gênero ${g}: ${genreCount[g]} filme(s)`);
}

// 7.3 for...in em array de strings
const nome = "Silva";
for (let i in nome) {
    console.log(`Letra ${i}:`, nome[i]);
}

// 8. MAP E SET
// ------------
// 8.1 Usando Map para pares chave-valor
let mapa = new Map();
mapa.set('nome', 'Bruce Wayne');
mapa.set('alias', 'Batman');
console.log('Map size:', mapa.size);
console.log('Map.has("alias")?', mapa.has('alias'));

// 8.2 Usando Set para valores únicos
let conjunto = new Set([1,2,2,3,3,3]);
console.log('Set:', conjunto);            // {1,2,3}
conjunto.add(4);                         
console.log('Após add(4):', conjunto);

// 9. ASYNC/AWAIT (Exemplo simplificado)
// -------------------------------------
async function exemploAsync() {
    try {
        const resultado = await prometerAlgo(10); // reusa a função de Promise acima
        console.log('Resultado async:', resultado);
    } catch (e) {
        console.log('Erro async:', e);
    }
}
exemploAsync();
