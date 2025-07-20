// Sudoku 4x4 com Backtracking - Versão Altamente Comentada

/**
 * isSafe(board, row, col, num)
 * Verifica se é válido colocar 'num' na posição [row][col] do tabuleiro.
 * Condições para ser seguro:
 *  1) 'num' não deve existir na mesma linha
 *  2) 'num' não deve existir na mesma coluna
 *  3) 'num' não deve existir no sub-quadrante 2x2 correspondente
 *
 * @param {number[][]} board - Matriz 4x4 representando o tabuleiro (0 = vazio)
 * @param {number} row - Índice da linha (0 a 3)
 * @param {number} col - Índice da coluna (0 a 3)
 * @param {number} num - Número a testar (1 a 4)
 * @returns {boolean} - true se for seguro, false caso contrário
 */
function isSafe(board, row, col, num) {
  // 1) Verificar linha e coluna
  for (let x = 0; x < 4; x++) {
    // Se o mesmo número já estiver na linha ou na coluna, não é seguro
    if (board[row][x] === num || board[x][col] === num) {
      return false;
    }
  }

  // 2) Determinar o canto superior esquerdo do sub-quadrante 2x2
  // row - (row % 2) encontra 0 ou 2, startCol faz o mesmo para col
  const startRow = row - (row % 2);
  const startCol = col - (col % 2);

  // 3) Verificar cada célula dentro desse sub-quadrante 2x2
  for (let r = 0; r < 2; r++) {
    for (let c = 0; c < 2; c++) {
      if (board[startRow + r][startCol + c] === num) {
        return false;
      }
    }
  }

  // Se passou em todas as checagens, é seguro colocar o número
  return true;
}

/**
 * solveSudoku(board)
 * Tenta preencher todo o tabuleiro usando backtracking.
 *
 * Passos:
 *  1) Encontrar uma célula vazia (valor == 0).
 *     - Se não encontrar, o Sudoku está completo e retornamos true.
 *  2) Para números de 1 a 4:
 *     a) Testar se é seguro colocar o número nesta célula (isSafe).
 *     b) Se seguro, colocar num e chamar recursivamente solveSudoku.
 *     c) Se a chamada recursiva retornar true, propagamos o sucesso.
 *     d) Se retornar false, desfazemos (backtrack) colocando 0 novamente.
 *  3) Se nenhum número resolver, retornamos false para retroceder.
 *
 * @param {number[][]} board - Tabuleiro 4x4 a ser preenchido
 * @returns {boolean} - true se conseguir resolver, false caso contrário
 */
function solveSudoku(board) {
  let row = -1;
  let col = -1;
  let foundEmpty = false;

  // 1) Procura primeira célula vazia (varrendo linhas e colunas)
  for (let i = 0; i < 4; i++) {
    for (let j = 0; j < 4; j++) {
      if (board[i][j] === 0) {
        row = i;
        col = j;
        foundEmpty = true;
        break; // sai do loop de colunas
      }
    }
    if (foundEmpty) break; // sai do loop de linhas se achou vazio
  }

  // 1a) Se não há célula vazia, Sudoku está resolvido!
  if (!foundEmpty) {
    return true;
  }

  // 2) Tenta números de 1 a 4 na posição vazia encontrada
  for (let num = 1; num <= 4; num++) {
    // Checa se colocar 'num' é permitido pelas regras
    if (isSafe(board, row, col, num)) {
      board[row][col] = num; // coloca temporariamente

      // 2b) Chama recursivamente para continuar preenchendo
      if (solveSudoku(board)) {
        return true; // se der certo em recursão, mantemos e retornamos true
      }

      // 2d) Backtracking: desfaz a escolha (restaura 0)
      board[row][col] = 0;
    }
  }

  // 3) Se nenhum número funcionou, retorna false para retroceder
  return false;
}

/**
 * generateSudoku4x4()
 * Cria um tabuleiro 4x4 vazio (todos zeros) e tenta resolvê-lo.
 * @returns {number[][]} - Matriz 4x4 preenchida com solução válida
 */
function generateSudoku4x4() {
  // Define tabuleiro inicial com zeros em todas as posições
  const board = [
    [0, 0, 0, 0],
    [0, 0, 0, 0],
    [0, 0, 0, 0],
    [0, 0, 0, 0]
  ];

  // Chama o solver e assume que sempre encontrará solução
  solveSudoku(board);

  return board;
}

/**
 * imprimirSudoku(sudoku)
 * Exibe cada linha do Sudoku no console.
 * @param {number[][]} sudoku - Matriz 4x4 a ser impressa
 */
function imprimirSudoku(sudoku) {
  // forEach em cada linha, unindo valores com espaço
  sudoku.forEach((linha, idx) => {
    console.log(linha.join(' '));
  });
}

// Exemplo de uso:
const sudokuGrid = generateSudoku4x4(); // Gera e resolve um Sudoku 4x4
imprimirSudoku(sudokuGrid); // Mostra no console cada linha da solução