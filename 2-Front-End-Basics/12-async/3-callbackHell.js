/**
 * Inicia o processo de ferver a água.
 * @param {Function} callback - Função a ser executada após a água ferver.
 */
function putWaterToBoil(callback) {
    console.log('Boil the water'); // Indica início da fervura
    // Simula o tempo de fervura de 5 segundos
    setTimeout(() => {
        console.log('We have boiled the water'); // Informa que a água ferveu
        callback(); // Chama a próxima etapa (pré-preparo do café)
    }, 5000);
}

/**
 * Prepara a máquina e os ingredientes para fazer o café.
 * @param {Function} callback - Função a ser executada após o preparo inicial.
 */
function getReadyToMakeCoffee(callback) {
    console.log('Grind coffee beans.'); // Tritura os grãos
    console.log('Line the basket of your coffee maker with a filter.'); // Posiciona o filtro
    console.log('Wet the filter, and let it drain into your cup.'); // Umedece e escorre água limpa
    console.log('Discard the water in the cup.'); // Descarte a água residual
    console.log('Measure the ground coffee into the wet filter.'); // Adiciona o pó no filtro
    callback(); // Chama a etapa de preparação do café propriamente dita
}

/**
 * Realiza a extração do café.
 * @param {Function} callback - Função a ser executada após extrair o café.
 */
function makeCoffee(callback) {
    console.log('Pour water to wet the ground beans and drain into your cup.'); // Passa água quente sobre o pó
    callback(); // Finaliza o processo
}

// Sequência de execução encadeada (callback hell)
putWaterToBoil(() => {
    getReadyToMakeCoffee(() => {
        makeCoffee(() => {
            console.log('Your coffee is ready!'); // Mensagem final ao usuário
        });
    });
});