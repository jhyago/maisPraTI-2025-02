import { useState } from "react"

export default function Contador() {
    const [contador, setContador] = useState(0)

    const handleIncrement = () => {
        setContador(contador + 1)
    }

    const handleDecrement = () => {
        setContador(contador - 1)
    }

    return(
        <div>
            <p>Contagem: {contador}</p>
            <button onClick={handleIncrement}>Incrementar</button>
            <button onClick={handleDecrement}>Decrementar</button>
        </div>
    )
}