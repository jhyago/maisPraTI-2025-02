import { createContext, useContext, useState } from 'react'

const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null)

    const login = (username, password) => {
        if(username === 'admin' && password === '1234') {
            setUser({ username })
        }
    }

    const logout = () => setUser(null)

    return (
        <AuthContext.Provider value={{ user, login, logout }}>{children}</AuthContext.Provider>
    )
}