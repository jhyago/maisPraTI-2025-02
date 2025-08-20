import { render, screen, fireEvent } from '@testing-library/react'
import { describe, it, expect } from 'vitest'
import App from './App'

describe('CounterJSX', () => {
    
    it('Deve incrementar o contador quando o botão for clicado.', () => {
        render(<App />)

        const countElement = screen.getByTestId('count')
        const buttonElement = screen.getByText('Incrementar')

        fireEvent.click(buttonElement)
        expect(countElement).toHaveTextContent('1')

        fireEvent.click(buttonElement)
        expect(countElement).toHaveTextContent('2')
    })
})