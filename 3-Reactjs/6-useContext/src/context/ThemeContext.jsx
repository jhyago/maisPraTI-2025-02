import { createContext, useContext, useState, useMemo } from 'react'

// Criamos o contexto
const ThemeContext = createContext(null)

// Provider: guarda e fornece o estado "theme" e a função toggleTheme
export function ThemeProvider({ children }) {
  const [theme, setTheme] = useState("light")

  const toggleTheme = () => {
    setTheme(prev => (prev === "light" ? "dark" : "light"))
  }

  // useMemo evita recriação desnecessária do objeto "value"
  const value = useMemo(() => ({ theme, toggleTheme }), [theme])

  return (
    <ThemeContext.Provider value={value}>
      {children}
    </ThemeContext.Provider>
  )
}

// Hook customizado para consumir o contexto
export function useTheme() {
  const ctx = useContext(ThemeContext)
  if (!ctx) throw new Error("useTheme deve ser usado dentro de <ThemeProvider>")
  return ctx
}