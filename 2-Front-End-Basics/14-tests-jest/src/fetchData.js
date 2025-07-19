// Função que simula uma chamada assíncrona retornando uma Promise

/**
 * fetchData
 * @returns {Promise<string>} - Promise que resolve com texto após 2 segundos
 */
function fetchData() {
  // Retorna uma nova Promise
  return new Promise((resolve) => {
    // Simula atraso de 2 segundos (2000 ms)
    setTimeout(() => {
      // Quando o tempo expira, resolve a Promise com a string
      resolve('Dados Recebidos');
    }, 2000);
  });
}

// Exporta a função para ser testada
module.exports = fetchData;