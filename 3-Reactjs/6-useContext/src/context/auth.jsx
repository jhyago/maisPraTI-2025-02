/*
  Visão geral: Context API (createContext/useContext) + useMemo

  - Contexto no React:
    * "Contexto" é uma forma de compartilhar dados entre componentes sem precisar
      passar props manualmente por todos os níveis (evita "prop drilling").
    * Ele é composto por: createContext() -> Provider -> useContext().

  - createContext(defaultValue):
    * Cria um "Contexto" que poderá ser lido por qualquer componente descendente do Provider.
    * O defaultValue (aqui null) só é usado quando não há um Provider acima na árvore.
      Colocar null é comum para forçar que o consumo sem Provider gere erro (via checagem manual).

  - Provider (AuthContext.Provider):
    * É um componente que “fornece” um value para todos os filhos.
    * Qualquer componente dentro do Provider pode ler o value com useContext(AuthContext).

  - useContext(Contexto):
    * Hook para consumir o valor do Contexto mais próximo acima na árvore de componentes.
    * Evita receber props; você lê direto do “canal” do contexto.

  - useMemo(factory, deps):
    * Memoriza (cacheia) o resultado de uma computação até que as dependências mudem.
    * Aqui usamos para estabilizar a referência do objeto "value" ({ user, login, logout }),
      evitando re-renderizações desnecessárias em consumidores que fazem comparação por referência.
    * Dependência [user] → o objeto só é recriado quando "user" mudar.

  - AuthProvider:
    * É um Provider específico para autenticação: guarda `user` no estado, expõe `login/logout`
      e fornece tudo via Contexto, para ser usado em qualquer lugar da app.

  - Hook personalizado useAuth():
    * Encapsula useContext(AuthContext) e adiciona uma checagem de segurança:
      se usado fora do Provider, lança um erro explicativo.
    * Isso melhora a DX (dev experience), evitando bugs silenciosos.
*/

import { createContext, useContext, useState, useMemo } from 'react'

// Cria o contexto de autenticação.
// Usamos `null` para deixar claro que, se alguém consumir fora do Provider, não haverá valor.
// Faremos uma checagem no hook useAuth para tratar esse caso com uma mensagem amigável.
const AuthContext = createContext(null)

export function AuthProvider({ children }) {
    // Estado que representa o usuário autenticado (ou null se não logado).
    const [user, setUser] = useState(null)

    // "login" define um usuário simples com { name } (simulação; em apps reais viria do backend).
    const login = (name) => setUser({ name })

    // "logout" limpa o usuário, voltando para null.
    const logout = () => setUser(null)

    // useMemo estabiliza a referência do objeto "value" para evitar recriações a cada render.
    // Assim, componentes consumidores que dependem da identidade do objeto não re-renderizam sem necessidade.
    // Dependência [user] garante que o value só muda quando "user" muda.
    const value = useMemo (() => ({ user, login, logout }), [user])

    // O Provider disponibiliza { user, login, logout } para toda a subárvore de {children}.
    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
    // Consome o contexto atual.
    const ctx = useContext(AuthContext)

    // Guarda de segurança: se não houver Provider acima, ctx será null.
    // Lançamos um erro para orientar o desenvolvedor a envolver a árvore com <AuthProvider>.
    if(!ctx) throw new Error("useAuth deve ser usado dentro de <AuthProvider>")

    // Retorna o objeto de contexto (user, login, logout).
    return ctx
}