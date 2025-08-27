import { useTheme } from "../context/ThemeContext"

export default function ThemeToggle() {
  const { toggleTheme } = useTheme()

  return (
    <button onClick={toggleTheme}>
      Alternar tema
    </button>
  )
}