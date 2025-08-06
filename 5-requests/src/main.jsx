import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import FetchUser from './components/FetchUser'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <FetchUser />
  </StrictMode>,
)
