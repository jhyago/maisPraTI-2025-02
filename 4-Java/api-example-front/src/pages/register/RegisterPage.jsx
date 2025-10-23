import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../../app/state/auth.jsx'

export default function RegisterPage() {
    const authContext = useAuth()
    
    const { register } = authContext || {}
    const [form, setForm] = useState({
        name: '',
        username: '',
        password: ''
    })
    const navigate = useNavigate()

    const onChange = (event) => {
        setForm({
            ...form,
            [event.target.name]: event.target.value
        })
    }

    const onSubmit = async (event) => {
        event.preventDefault()

        try {
            await register(form)
            navigate('/dashboard')
        } catch (e) {
            console.error('Registration failed', e)
        }
    }

    return (
        <div>
            <h1>Registre-se</h1>
            <form onSubmit={onSubmit}>
                <div>
                    <label>Nome</label>
                    <input type="text" name='name' value={form.name} onChange={onChange}/>
                </div>

                <div>
                    <label>E-mail</label>
                    <input type="email" name='username' value={form.username} onChange={onChange}/>
                </div>

                <div>
                    <label>Senha</label>
                    <input type="password" name='password' value={form.password} onChange={onChange}/>
                </div>
                <button>Registre-se</button>               
            </form>

        </div>
    )
}