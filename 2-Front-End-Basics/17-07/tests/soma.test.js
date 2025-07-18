const soma = require('../src/soma')

test('Soma 2 + 3 deve ser igual a 5', () => {
    expect(soma(2, 3)).toBe(5)
})

test('soma -1 e +1 deve ser igual a 0', () => {
    expect(soma(-1, 1)).toBe(0)
})