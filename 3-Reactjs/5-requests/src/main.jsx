import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import AxiosInterceptor from './components/AxiosInterceptor'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AxiosInterceptor />
  </StrictMode>,
)
