import { render, screen, fireEvent } from '@testing-library/react'
import { describe, it, expect } from 'vitest'
import CounterJSX from './CounterJSX'

describe('CounterJSX', () => {
    it('Deve renderizar o contador inicialmente com 0.', () => {
        render(<CounterJSX />)

        const countElement = screen.getByTestId('count')

        expect(countElement).toHaveTextContent(0)
    })

    it('Deve incrementar o contador quando o botão for clicado.', () => {
        render(<CounterJSX />)

        const countElement = screen.getByTestId('count')
        const buttonElement = screen.getByText('Incrementar')

        fireEvent.click(buttonElement)
        expect(countElement).toHaveTextContent('1')

        fireEvent.click(buttonElement)
        expect(countElement).toHaveTextContent('2')
    })
})