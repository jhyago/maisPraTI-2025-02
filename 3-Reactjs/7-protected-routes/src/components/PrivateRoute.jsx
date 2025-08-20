/*
  O que é o <Navigate> no React Router?

  - <Navigate> é um componente que faz redirecionamento de rota.
  - Diferente de um <Link> ou <NavLink>, ele não é um link clicável,
    mas sim um redirecionamento automático quando renderizado.
  - É útil em casos como:
      • Redirecionar usuários não autenticados para "/login".
      • Redirecionar após logout.
      • Criar rotas que mudam automaticamente para outra rota.

  - A prop "to" define para onde o usuário deve ser enviado.
  - Por padrão, substitui a entrada atual no histórico do navegador,
    mas pode receber `replace={false}` para empilhar no histórico.
*/

import { Navigate } from 'react-router-dom'   // Importa o componente responsável por redirecionar.
import { useAuth } from '../context/AuthContext' // Hook de contexto que fornece informações de autenticação.

const PrivateRoute = ({ children }) => {      // Componente que protege rotas privadas.
    const { user } = useAuth()                // Obtém o usuário logado (ou null se não houver login).

    return user                               // Se houver usuário logado...
        ? children                            // ...renderiza o conteúdo protegido (rota privada).
        : <Navigate to='/login' />            // ...senão, redireciona automaticamente para a página de login.
}

export default PrivateRoute                   // Exporta o componente para ser usado no App.