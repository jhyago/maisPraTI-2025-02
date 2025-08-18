import { useTheme } from "../context/ThemeContext"

export default function ThemeStatus() {
  const { theme } = useTheme()

  return <p>Tema atual: {theme}</p>
}