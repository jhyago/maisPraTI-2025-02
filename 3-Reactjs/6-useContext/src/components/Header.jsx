import { useAuth } from "/Users/jaquesantunes/Documents/maisPraTI-2025-02/3-Reactjs/6-useContext/src/context/auth.jsx"

function Header() {
    const { user, login, logout } = useAuth()

    return (
        <header>
            {user ? (
                <>
                    <span>Olá, {user.name}</span>
                    <button onClick={logout}>Sair!</button>
                </>
            ) : (
                <button onClick={() => login("Jaques")}>Entrar</button>
            )}
        </header>
    )
}

export default Header