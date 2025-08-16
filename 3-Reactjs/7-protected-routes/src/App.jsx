import { useAuth } from './context/AuthContext'

function App() {

  const { user, logout } = useAuth()

  return (
      <div>
        <header>
          <nav>
            <Link to='/'>Home</Link>
            {user ? (
              <>
                <span>Olá, {user.username}</span>
              <button onClick={logout}>Logout</button>
              </>
            ) : (
              <Link to="/login">Login</Link>
            )}
          </nav>
        </header>
      </div>
  )
}

export default App
