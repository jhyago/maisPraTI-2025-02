import { ThemeProvider } from "./context/ThemeContext"
import ThemeToggle from "./components/ThemeToggle"
import ThemeStatus from "./components/ThemeStatus"

export default function App() {
  return (
    <ThemeProvider>
      <div style={{ padding: 20 }}>
        <h1>Mini-app de Tema</h1>
        <ThemeToggle />
        <ThemeStatus />
      </div>
    </ThemeProvider>
  )
}