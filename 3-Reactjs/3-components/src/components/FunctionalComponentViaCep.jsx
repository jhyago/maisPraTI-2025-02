import { useState, useEffect } from "react"

export default function FunctionalComponentViaCep() {
    const [cep, setCep] = useState('')
    const [address, setAddress] = useState('')
    
    useEffect(() => {
        const fetchCep = async() => {
            const result = await fetch(`https://viacep.com.br/ws/${cep}/json/`)
            const data = await result.json()
            setAddress(data)
        }

        if(cep.length === 8){
            fetchCep()
        }

    }, [cep])

    return(
        <div>
            <label htmlFor="cep">CEP</label>
            <input id="cep" type="text" onChange={(e) => setCep(e.target.value)}/>

            {address ? 
            <div>
                <p>{address.logradouro}</p>
                <p>{address.bairro}</p>
                <p>{address.localidade} - {address.uf}</p>
            </div> : <p>Aguardando Cep</p>
            }
        </div>
    )
}