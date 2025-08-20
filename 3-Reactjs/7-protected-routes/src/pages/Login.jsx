/*
  O que é o useNavigate?

  - O hook useNavigate do react-router-dom serve para navegar entre rotas
    de forma programática (via código), sem precisar de cliques em <Link> ou <NavLink>.
  - Ele retorna uma função (navigate) que podemos chamar passando:
      • Um caminho: navigate('/sobre') → envia o usuário para /sobre.
      • Um número: navigate(-1) → volta uma página no histórico.
  - Também aceita opções como { replace: true } para substituir a rota no histórico
    (assim o usuário não consegue "voltar" para a página anterior).
  - É muito usado depois de login, logout, envio de formulários etc.

  Exemplo neste código:
    - Após o login ser validado, usamos navigate('/') para levar o usuário para a Home.
*/

import { useState } from 'react'                  // Hook para gerenciar estados (username e password).
import { useAuth } from '../context/AuthContext'  // Hook de contexto que dá acesso à função login().
import { useNavigate } from 'react-router-dom'    // Hook usado para redirecionar via código.

const Login = () => {
    const [username, setUsername] = useState('')  // Estado para o campo de usuário.
    const [password, setPassword] = useState('')  // Estado para o campo de senha.

    const { login } = useAuth()                   // Pega a função login do contexto de autenticação.
    const navigate = useNavigate()                // Cria a função navigate para redirecionar o usuário.

    const handleSubmit = (event) => {
        event.preventDefault()                    // Evita o refresh do formulário.
        login(username, password)                 // Chama a função de login (simulada).
        navigate('/')                             // Redireciona para a Home após login.
    }

    return (
        // Container de tela cheia para centralizar o card de login
        <div className="min-h-screen flex items-center justify-center bg-gray-100 p-6">
            {/* Card de login: caixa com sombra, cantos arredondados e largura máxima controlada */}
            <div className="w-full max-w-md bg-white rounded-2xl shadow-lg p-8">
                {/* Cabeçalho do card com título estilizado */}
                <h1 className="text-3xl font-extrabold text-gray-800 mb-6 text-center">Login</h1>

                {/* Formulário com layout em coluna e espaçamento vertical entre campos */}
                <form onSubmit={handleSubmit} className="space-y-5">        {/* Formulário controlado com onSubmit. */}
                    {/* Campo de usuário: input com borda, foco visível e padding interno */}
                    <div className="flex flex-col gap-2">
                        <label className="text-sm font-medium text-gray-700" htmlFor="username">
                            Usuário
                        </label>
                        <input 
                            id="username"
                            type="text" 
                            placeholder='User' 
                            value={username} 
                            onChange={(event) => setUsername(event.target.value)} 
                            className="w-full rounded-lg border border-gray-300 px-3 py-2 text-gray-800 placeholder-gray-400
                                       focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition"
                        />
                        {/* Dica contextual opcional */}
                        <p className="text-xs text-gray-500">Ex.: admin</p>
                    </div>

                    {/* Campo de senha: segue o mesmo padrão visual do campo anterior */}
                    <div className="flex flex-col gap-2">
                        <label className="text-sm font-medium text-gray-700" htmlFor="password">
                            Senha
                        </label>
                        <input 
                            id="password"
                            type="text" 
                            placeholder='Password' 
                            value={password} 
                            onChange={(event) => setPassword(event.target.value)} 
                            className="w-full rounded-lg border border-gray-300 px-3 py-2 text-gray-800 placeholder-gray-400
                                       focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition"
                        />
                        {/* Observação: aqui mantivemos type="text" conforme seu código original. */}
                        <p className="text-xs text-gray-500">Dica: 1234</p>
                    </div>

                    {/* Botão de submit com estado hover/focus e transição suave */}
                    <button 
                        type='submit'
                        className="w-full rounded-lg bg-indigo-600 text-white font-semibold py-2.5
                                   hover:bg-indigo-700 active:bg-indigo-800
                                   focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2
                                   transition"
                    >
                        Entrar
                    </button>
                </form>

                {/* Rodapé opcional do card com link auxiliar */}
                <div className="mt-6 text-center text-sm text-gray-500">
                    Dica de acesso: <span className="font-medium text-gray-700">admin / 1234</span>
                </div>
            </div>
        </div>
    )
}

export default Login

/* 
  Container externo:
  - min-h-screen → ocupa 100% da altura da tela.
  - flex items-center justify-center → centraliza o conteúdo no eixo vertical e horizontal.
  - bg-gray-100 → fundo cinza claro para contraste com o card.
  - p-6 → padding externo para respiro nas bordas em telas menores.

  Card:
  - w-full max-w-md → largura total até um máximo (~28rem) para manter legibilidade.
  - bg-white → fundo branco.
  - rounded-2xl → cantos bem arredondados.
  - shadow-lg → sombra destacada.
  - p-8 → espaçamento interno confortável.

  Título:
  - text-3xl font-extrabold → tipografia grande e forte.
  - text-gray-800 → cor escura para alto contraste.
  - mb-6 → separação do formulário.
  - text-center → centraliza o título.

  Form:
  - space-y-5 → espaçamento vertical automático entre os filhos do formulário.

  Labels:
  - text-sm font-medium text-gray-700 → tamanho menor, peso médio, cor legível.

  Inputs:
  - rounded-lg border border-gray-300 → borda padrão e cantos arredondados.
  - px-3 py-2 → padding horizontal/vertical.
  - text-gray-800 placeholder-gray-400 → cores de texto e placeholder.
  - focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 → estilo claro de foco (acessibilidade).
  - transition → anima transições de foco suavemente.

  Botão:
  - w-full → ocupa toda a largura.
  - rounded-lg → cantos arredondados.
  - bg-indigo-600 text-white font-semibold → cor de fundo, cor do texto e peso da fonte.
  - py-2.5 → altura confortável do botão.
  - hover:bg-indigo-700 active:bg-indigo-800 → estados de interação.
  - focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 → foco acessível com anel de destaque.
  - transition → transição suave.
*/