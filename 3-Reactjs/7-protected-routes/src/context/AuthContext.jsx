/*
  O que é "Contexto" no React?

  - Contexto (createContext/useContext) é uma forma de compartilhar dados
    entre vários componentes sem precisar passar props manualmente em cada nível.
  - Ele resolve o problema de "prop drilling" (quando precisamos passar props
    de um componente pai até um neto/bisneto que realmente usa o dado).
  - Funciona em 3 partes:
      1) Criar o contexto → createContext().
      2) Fornecer o valor → usando <AuthContext.Provider>.
      3) Consumir o valor → useContext(AuthContext) em qualquer componente filho.
  - Aqui o contexto armazena o estado de autenticação (usuário logado ou não)
    e fornece funções de login/logout para toda a aplicação.
*/

import { createContext, useContext, useState } from 'react'

// 1) Criamos o contexto vazio (pode fornecer qualquer tipo de dado).
const AuthContext = createContext()

// 3) Hook personalizado que facilita consumir o contexto.
//    Em vez de usar useContext(AuthContext) toda vez,
//    podemos simplesmente usar useAuth().
export const useAuth = () => useContext(AuthContext)

// 2) Componente que envolve toda a aplicação e fornece os valores do contexto.
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null)    // Estado que guarda o usuário logado (ou null se deslogado).

    const login = (username, password) => {   // Função de login (simulada, sem backend).
        if(username === 'admin' && password === '1234') {
            setUser({ username })             // Se as credenciais forem corretas, define o usuário.
        }
    }

    const logout = () => setUser(null)        // Função de logout: limpa o usuário.

    // O Provider fornece { user, login, logout } para qualquer componente dentro dele.
    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}                        {/* Renderiza os filhos dentro do Provider. */}
        </AuthContext.Provider>
    )
}