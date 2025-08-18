/*
  O que é o <Outlet>?

  - O <Outlet> é um espaço reservado que o react-router-dom usa para renderizar
    o componente da rota FILHA que corresponde à URL atual.
  - Exemplo: se "/" renderiza RootLayout e dentro dele há um <Outlet />,
    então, se a URL for "/sobre", o componente <Sobre /> será renderizado dentro desse <Outlet>.
  - Ele permite criar layouts persistentes (como Navbar, Footer, Sidebar) que ficam fixos,
    enquanto o conteúdo do meio muda conforme a rota.
*/

import { Outlet } from "react-router"          // Importa o <Outlet>, usado para mostrar a rota filha ativa.
import Navbar from "../components/Navbar"      // Importa o componente Navbar (menu de navegação do site).

export default function RootLayout() {         // Define o layout raiz, usado como "casca" das páginas.
    return (
        <div style={{fontFamily:"system-ui, Arial", lineHeight: 1.5}}> {/* Container geral com estilos globais. */}
            <Navbar />                         {/* O menu de navegação aparece em todas as páginas. */}

            <main style={{ padding: 16 }}>     {/* Área principal do layout com espaçamento interno. */}
                <Outlet />                     {/* Aqui será inserido o componente da rota filha correspondente. */}
            </main>
        </div>
    )
}