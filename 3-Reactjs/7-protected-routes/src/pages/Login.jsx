import { useState } from 'react'
import { useAuth } from '../context/AuthContext'
import { useNavigate } from 'react-router-dom'

const Login = () => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const { login } = useAuth()
    const navigate = useNavigate()

    const handleSubmit = (event) => {
        event.preventDefault()
        login(username, password)
        navigate('/')
    }

    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder='User' value={username} onChange={(event) => setUsername(event.target.value)} />

                <input type="text" placeholder='Password' value={password} onChange={(event) => setPassword(event.target.value)}/>

                <button type='submit'>
                    Entrar
                </button>
            </form>
        </div>
    )
}

export default Login