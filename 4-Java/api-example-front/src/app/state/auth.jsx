import { createContext, useContext, useEffect, useMemo, useState } from 'react'
import * as authApi from '../../entities/auth/api.js'
import { getMe } from '../../entities/user/api.js'

const AuthContext = createContext(null)

const TOKEN_KEY = 'jwt_token'

export function AuthProvider({ children }) {
    const [token, setToken] = useState(() => {
        return localStorage.getItem(TOKEN_KEY)
    })

    const [user, setUser] = useState(null)

    useEffect(() => {
        if (token) localStorage.setItem(TOKEN_KEY, token)
        else localStorage.removeItem(TOKEN_KEY)
    }, [token])

    useEffect(() => {
        async function init() {
            if (!token) {
                setUser(null)
                return
            }
            
            try {
                const me = await getMe(token)
                setUser(me)
            } catch (e) {
                setUser(null)
                console.error('Failed to fetch user data', e)
            }
        } 
        init()
    }, [token])

    const login = async (username, password) => {
        const { token: t } = await authApi.login({username, password})
        setToken(t)
        const me = await getMe(t)
        setUser(me)
    }

    const register = async (data) => {
        const { token: t } = await authApi.register(data)
        setToken(t)
        const me = await getMe(t)
        setUser(me)
    }

    const value = useMemo(
        () => {
            const contextValue = {token, setToken, user, isAuthenticated: !!user, setUser, login, register}
            return contextValue
        }, 
        [token, user]
    )
    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
    const context = useContext(AuthContext)
    return context
}
