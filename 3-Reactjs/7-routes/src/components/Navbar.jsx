/*
  Diferença entre <Link> e <NavLink>:

  - Ambos servem para navegação sem recarregar a página (Single Page Application).
  - <Link> é o mais simples: apenas leva o usuário para a rota especificada.
  - <NavLink> é como <Link>, mas com uma vantagem extra: ele "sabe" se a rota está ativa
    e pode aplicar estilos/classe automaticamente quando o link corresponde à URL atual.
    Isso é útil para menus de navegação, onde queremos destacar a página atual.

  - No exemplo abaixo, usamos a prop style do <NavLink> que recebe uma função.
    Essa função recebe um objeto com { isActive } e retorna estilos diferentes:
    se o link estiver ativo -> fundo preto, texto branco
    se não -> fundo branco, texto preto.
*/

import { NavLink } from "react-router-dom"   // Importa o NavLink (semelhante ao Link, mas com suporte a estado "ativo").

const linkStyle = ({ isActive }) => ({       // Função que retorna um objeto de estilos dependendo do estado ativo.
    marginRight: 8,                          // Espaçamento à direita de cada link.
    textDecoration: "none",                  // Remove sublinhado do link.
    padding: "6px 10px",                     // Espaçamento interno (tamanho do botão).
    borderRadius: 6,                         // Bordas arredondadas.
    border: "1px solid #ddd",                // Borda cinza clara.
    background: isActive ? "#111": "#fff",   // Fundo preto se ativo, branco se inativo.
    color: isActive ? "#fff": "#111"         // Texto branco se ativo, preto se inativo.
})

export default function Navbar() {           // Define o componente Navbar.
    return(
        <nav style={{                       // Estilização do container da barra de navegação.
            display: "flex",                 // Organiza links em linha.
            alignItems: "center",            // Alinha verticalmente no centro.
            gap: 8,                          // Espaçamento entre links.
            padding: 12,                     // Espaçamento interno do nav.
            borderBottom: "1px solid #eee"   // Linha inferior para separar do conteúdo.
        }}>
            <NavLink to="/" style={linkStyle}>Home</NavLink>       {/* Link para "/" que ficará ativo na página inicial. */}
            <NavLink to="/sobre" style={linkStyle}>Sobre</NavLink> {/* Link para "/sobre" que ficará ativo nessa página. */}
            <NavLink to="/posts/10" style={linkStyle}>Post 10</NavLink> {/* Link para "/posts/10", útil para exemplo. */}
        </nav>
    )
}