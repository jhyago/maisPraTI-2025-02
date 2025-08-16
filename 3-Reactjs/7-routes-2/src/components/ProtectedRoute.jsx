import { Navigate } from "react-router-dom"

export default function ProtectedRoute({ children }) {
    const isAuthenticated = false

    if(!isAuthenticated) {
        return <Navigate to="/" replace />
    }

    return children
}