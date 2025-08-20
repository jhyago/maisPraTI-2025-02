/*
  O que são "rotas" no React?

  - Em apps React de página única (SPA), usamos a biblioteca react-router-dom para
    mapear caminhos de URL (ex.: "/", "/sobre", "/posts/42") para componentes.
  - <BrowserRouter> habilita o roteamento usando a History API do navegador
    (pushState/replaceState), evitando recarregar a página a cada navegação.
  - <Routes> escolhe e renderiza a ÚNICA <Route> (ou combinação de rotas aninhadas)
    que melhor casa com a URL atual.
  - <Route> associa um "path" a um "element" (o componente a renderizar).
  - Rotas aninhadas permitem um layout persistente (ex.: cabeçalho/rodapé) no pai
    e uma área variável para os filhos. O componente de layout normalmente renderiza
    <Outlet /> onde os filhos aparecerão.
  - "index" define a rota padrão do pai (ex.: a página inicial de "/").
  - Segmentos dinâmicos (ex.: ":id") capturam parâmetros da URL, acessíveis com
    useParams() dentro do componente.
  - O path "*" é um curinga para lidar com caminhos não mapeados (página 404).
*/

import { BrowserRouter, Routes, Route } from "react-router-dom" // Importa os componentes de roteamento da biblioteca.

import RootLayout from "./layouts/RootLayout" // Layout raiz (geralmente contém <Header/>, <Footer/> e <Outlet />).
import Home from "./pages/Home"               // Página inicial (renderizada em "/").
import Sobre from "./pages/Sobre"             // Página "Sobre" (renderizada em "/sobre").
import Post from "./pages/Post"               // Página de um post individual (renderizada em "/posts/:id").
import NotFound from "./pages/NotFound"       // Página de erro/404 (renderizada para caminhos não mapeados).

export default function App() {               // Define o componente principal da aplicação.

  return (                                   // Retorna a árvore de elementos JSX com as rotas.
    <BrowserRouter>                          {/* Provedor de roteamento baseado na URL do navegador. */}
      <Routes>                               {/* "Seletor" de rotas que escolhe a melhor correspondência para a URL atual. */}
        <Route path="/" element={<RootLayout />}> {/* Rota PAI para "/" que renderiza o layout; filhos aparecem onde o layout usa <Outlet />. */}
          <Route index element={<Home />} />       {/* Rota de ÍNDICE do pai: corresponde exatamente a "/". */}
          <Route path="sobre" element={<Sobre />} /> {/* Rota FILHA relativa: forma a URL "/sobre". */}
          <Route path="posts/:id" element={<Post />} /> {/* Rota dinâmica: "/posts/123" captura "id" via useParams() dentro de <Post />. */}

          <Route path="*" element={<NotFound />} /> {/* Curinga: qualquer caminho não casado pelos filhos acima cai aqui (404). */}
        </Route>
      </Routes>
    </BrowserRouter>
  )
}