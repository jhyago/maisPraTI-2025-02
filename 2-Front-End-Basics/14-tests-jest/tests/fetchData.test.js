// Importa a função que retorna Promise
const fetchData = require('../src/fetchData');

test('Deve retornar "Dados Recebidos" após 2 segundos', async () => {
  // 1) Chama a função e aguarda a Promise resolver usando async/await
  const data = await fetchData();

  // 2) Verifica se o valor resolvido é exatamente a string esperada
  expect(data).toBe('Dados Recebidos');
});

// Alternativa 1: usando retorno de Promise sem async/await
test('Retorno de Promise sem async/await', () => {
  // Ao retornar a Promise, Jest aguarda sua resolução
  return fetchData().then((data) => {
    // Dentro do then(), validamos o valor retornado
    expect(data).toBe('Dados Recebidos');
  });
});

// Alternativa 2: usando callback "done" para sinalizar fim do teste
test('Usando callback done para Promise', (done) => {
  // Chama fetchData e aguarda resolução
  fetchData().then((data) => {
    expect(data).toBe('Dados Recebidos');
    done(); // Informa ao Jest que o teste assíncrono terminou
  });
});

// Alternativa 3: Simulando timers com jest.useFakeTimers()
describe('Teste com fake timers', () => {
  beforeAll(() => {
    // Substitui timers nativos por funções mock do Jest
    jest.useFakeTimers();
  });

  afterAll(() => {
    // Restaura timers reais após testes
    jest.useRealTimers();
  });

  test('fetchData resolve após 2s usando fake timers', () => {
    const promise = fetchData(); // Inicia a Promise (setTimeout mockado)

    // Avança o tempo em 2000 ms para disparar o setTimeout
    jest.advanceTimersByTime(2000);

    // Retorna a Promise para o Jest aguardar
    return promise.then((data) => {
      expect(data).toBe('Dados Recebidos');
    });
  });
});
