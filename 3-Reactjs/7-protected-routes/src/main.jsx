/*
  - "Rotas privadas" são caminhos do app que só podem ser acessados por usuários autenticados.
    Em React Router, uma forma comum é envolver a rota com um componente "guardião"
    (ex.: <PrivateRoute>) que checa se há usuário logado. Se não houver, redireciona (ex.: para /login).
  - "Contexto de autenticação" (AuthContext) guarda o estado global de login (user) e funções (login/logout),
    evitando "prop drilling". O <AuthProvider> expõe esses dados para toda a árvore do app.
  - Assim, <PrivateRoute> consulta o contexto: se user existe → renderiza a rota protegida; se não → <Navigate to="/login" />.
*/

import { StrictMode } from 'react'                      // (Opcional) Modo estrito do React para avisos de práticas inseguras em dev (não está sendo usado aqui).
import { createRoot } from 'react-dom/client'           // Nova API de root do React 18 para montar a aplicação.
import './index.css'                                    // CSS global (reset/estilos base do app).
import App from './App.jsx'                             // (Importado mas não usado neste trecho — poderia ser removido ou utilizado).
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom' 
// BrowserRouter renomeado para Router; Routes e Route definem as rotas do app.

import { AuthProvider } from './context/AuthContext.jsx' // Provedor do contexto de autenticação (user, login, logout).
import Login from './pages/Login.jsx'                    // Página pública de login.
import Home from './pages/Home.jsx'                      // Página inicial (será protegida).
import PrivateRoute from './components/PrivateRoute.jsx' // Guardião de rotas privadas (usa <Navigate> se não logado).

createRoot(document.getElementById('root')).render(     // Encontra o elemento #root no HTML e renderiza a árvore React.
  <AuthProvider>                                        {/* Torna o contexto de auth disponível para tudo dentro (Router, rotas, páginas). */}
    <Router>                                            {/* Habilita roteamento via History API (SPA). */}
      <Routes>                                          {/* Escolhe qual <Route> renderizar com base na URL atual. */}
        <Route 
          path='/'                                      // Caminho raíz ("/").
          element={                                     // Elemento a ser renderizado quando a rota for acessada.
            // Envolve o conteúdo com o guardião de rota privada.
            <PrivateRoute>                              
              <Home />                                  {/* Conteúdo protegido: só aparece se houver user no contexto. */}
            </PrivateRoute>
          } 
        />
        <Route 
          path='/login'                                 // Rota pública de login.
          element={<Login />}                           // Renderiza a página de login.
        />
      </Routes>
    </Router>
  </AuthProvider>
)

/*
  Como essa proteção funciona em conjunto:

  - <AuthProvider> mantém `user` no estado e o fornece via contexto.
  - <PrivateRoute> (em components/PrivateRoute.jsx) usa `useAuth()` para ler `user`.
      • Se `user` existe → renderiza `children` (neste caso, <Home />).
      • Se `user` não existe → <Navigate to="/login" /> (redireciona automaticamente).
  - Assim, acessar "/" sem login leva você para "/login". Após autenticar, você pode acessar "/".
*/
