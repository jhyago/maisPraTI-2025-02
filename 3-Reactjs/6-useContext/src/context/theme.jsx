import { createContext, useCallback, useContext, useState, useMemo } from "react"

const ThemeContext = createContext(null)

export function ThemeProvider({children}) {
    const [theme, setTheme] = useState("light")

    const toggleTheme = useCallback(() => {
        setTheme((t) => (t === "light"? "dark" : "light"))
    }, [])

    const value = useMemo(() => ({ theme, toggleTheme }), [theme, toggleTheme])

    return <ThemeContext value={value}>{children}</ThemeContext>
}

export function useTheme() {
    const ctx = useContext(ThemeContext)
    if(!ctx) throw new Error ("Você precisa usar o Hook dentro do Provider correto")
    return ctx
}