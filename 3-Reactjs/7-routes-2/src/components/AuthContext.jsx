import { createContext, useContext, useMemo, useState } from "react"

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null)

    const login = (name) => setUser({ name })
    const logout = () => setUser(null)

    const value = useMemo(() => ({ user, login, logout }), [user])

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
    const ctx = useContext(AuthContext)
    if(!ctx) throw new Error("useAuth deve ser utilizado dentro do Provedor. ")
    return ctx
}