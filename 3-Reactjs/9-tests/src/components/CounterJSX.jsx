import { useState } from 'react'

export default function CounterJSX() {
    const [count, setCount] = useState(0)

    const increment = () => {
        setCount(count + 1)
    }

    return (
        <>
            <p data-testid='count'>{count}</p>
            <button onClick={increment}>Incrementar</button>
        </>
    )
}