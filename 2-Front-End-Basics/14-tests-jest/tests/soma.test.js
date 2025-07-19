const soma = require('../src/soma')

test('Soma de 5 + 1 deve ser 6: ', () => {
    expect(soma(5, 1)).toBe(6)
})

test('Soma -1 + 1 deve ser 0: ', () => {
    expect(soma(-1, 1)).toBe(0)
})