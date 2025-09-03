// Importa React e ReactDOM para renderizar a aplicação
import React from 'react'; // Importa a biblioteca React
import { createRoot } from 'react-dom/client'; // Importa o método createRoot para React 18+
import App from './App.jsx'; // Importa o componente principal da aplicação
import './styles/theme.css'; // Importa CSS global com variáveis, reset e tema

// Localiza o elemento com id 'root' para montar a aplicação
const rootElement = document.getElementById('root'); // Obtém a referência ao elemento root

// Cria a raiz React com o elemento encontrado
const root = createRoot(rootElement); // Inicializa a raiz do React

// Renderiza o componente App dentro de StrictMode para melhores avisos de desenvolvimento
root.render( // Chama o método render
  <React.StrictMode> {/* Ativa verificações adicionais em modo desenvolvimento */}
    <App /> {/* Renderiza o componente principal */}
  </React.StrictMode> // Fecha o StrictMode
); // Finaliza a chamada de render
