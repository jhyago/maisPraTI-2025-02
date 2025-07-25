/**
 * Pré-prepara a máquina de café: moagem, filtro e dose de pó.
 */
function getReadyToMakeCoffee() {
  console.log("Grind coffee beans."); // Tritura os grãos
  console.log("Line the basket of your coffee maker with a filter.");
  // Coloca o filtro no porta-filtro
  console.log("Wet the filter, and let it drain into your cup.");
  // Umedece o filtro e deixa drenar água limpa no copo
  console.log("Discard the water in the cup."); // Descarta a água residual do copo
  console.log("Measure the ground coffee into the wet filter.");
  // Adiciona o pó de café no filtro úmido
}

/**
 * Despeja a água sobre o pó e filtra o café.
 */
function makeCoffee() {
  console.log("Pour water to wet the ground beans and drain into your cup.");
  // Passa a água quente sobre o pó para extração
}

/**
 * Rotina assíncrona para todo o processo de fazer café.
 * Usa async/await para controlar fluxo e tratar erros.
 */
async function makeCoffeeRoutine() {
  try {
    await putWaterToBoil(); // Aguarda água ferver
    getReadyToMakeCoffee(); // Prepara máquina e ingredientes
    makeCoffee(); // Realiza a extração do café
    console.log("Your coffee is ready!"); // Mensagem de conclusão
  } catch (error) {
    console.error("Something went wrong:", error); // Trata qualquer erro
  }
}

// Inicia o processo de fazer café
makeCoffeeRoutine();
