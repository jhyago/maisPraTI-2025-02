import { useState, useEffect } from 'react'
import axios from 'axios'

const AxiosPost = () => {
    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [mensagem, setMensagem] = useState('')
    const [error, setError] = useState(null)
    const [loading, setLoading] = useState(false)

    const createUser = async () => {

        setLoading(true)
        const newUser = {
            name: name,
            email: email
        }

        try {
            const response = await axios.post("https://jsonplaceholder.typicode.com/users", newUser)
            setMensagem(`Usário ${response.data.name} foi criado com sucesso.`)
            setLoading(false)
        } catch(err) {
            setError(err.message)
            setLoading(false)
        }
    }

    return(
        <div>
            <h1>Criar Novo Usuário</h1>
            
            <div>
                <input type="text" placeholder='Nome'value={name} onChange={(event) => setName(event.target.value)}/>
            </div> 

            <div>
                <input type="text" placeholder='Email'value={email} onChange={(event) => setEmail(event.target.value)}/>
            </div> 

            <button onClick={createUser} disabled={loading}>{loading ? 'Enviando...' : 'Criar Usuário'}</button>

            {mensagem && <p>{mensagem}</p>}
        </div>
    )
}

export default AxiosPost