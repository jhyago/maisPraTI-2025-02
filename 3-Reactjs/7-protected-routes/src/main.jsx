import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { AuthProvider } from './context/AuthContext.jsx'
import Login from './pages/Login.jsx'
import Home from './pages/Home.jsx'
import PrivateRoute from './components/PrivateRoute.jsx'

createRoot(document.getElementById('root')).render(
  <AuthProvider>
    <Router>
      <Routes>
        <Route path='/' element={<PrivateRoute>
          <Home />
        </PrivateRoute>} />
        <Route path='/login' element={<Login />} />
      </Routes>
    </Router>
  </AuthProvider>
)
