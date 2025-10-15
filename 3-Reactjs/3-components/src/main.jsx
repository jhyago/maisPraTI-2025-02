import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import LifeCycleFunctionalComponent from './components/LifeCycleFunctionalComponent'
// import FunctionalComponentViaCep from './components/FunctionalComponentViaCep'
import ViaCepFunctional from './components/ViaCepFunctional'

// Desenvolva um componente React (versão classe e versão funcional) que consome a API viacep para buscar e exibir o endereço correspondente a um CEP recebido por prop, de modo a evidenciar as fases de montagem, atualização e desmontagem.

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ViaCepFunctional cep={"21021370"} />
  </StrictMode>,
)
