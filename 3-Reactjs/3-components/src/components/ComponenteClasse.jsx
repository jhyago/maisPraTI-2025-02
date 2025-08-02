// Importa a classe Component do React para permitir a criação de um componente de classe
import { Component } from 'react';

// Define o componente de classe MyClassComponent que herda funcionalidades de React.Component
class MyClassComponent extends Component {

  // Construtor inicializa o estado do componente e recebe as props passadas pelo componente pai
  constructor(props) {
    super(props); // Chama o construtor da classe Component, passando as props para que o React configure-as internamente
    // O super(props) passa as props que o componente filho (MyClassComponent) recebeu para o construtor da classe Component (do React)
    // O React então trata e distribui essas props internamente, permitindo que o componente acesse elas via this.props.
    
    // Define o estado inicial com 'count' igual a 0 ou o valor de initialCount (passado pelo componente pai)
    this.state = { 
      count: props.initialCount || 0 // Aqui, usamos a prop 'initialCount' passada pelo componente pai para inicializar o estado do componente
    };
  }

  // Método incrementCount: esse método será chamado ao clicar no botão
  incrementCount = () => {
    // Atualiza o estado de 'count', somando 1 ao valor atual de 'count' e provocando uma re-renderização
    this.setState({ count: this.state.count + 1 });
  }

  // Método render é obrigatório em componentes de classe e define o que será exibido no DOM
  render() {
    return (
      <div>
        {/* Exibe o valor atual de 'count' */}
        <p>Contagem: {this.state.count}</p>
        
        {/* Botão que chama o método incrementCount ao ser clicado */}
        <button onClick={this.incrementCount}>Incrementar</button>
      </div>
    );
  }
}

// Exporta o componente para ser utilizado em outros arquivos
export default MyClassComponent;


//O super(props) está passando as props que o MyClassComponent recebeu para o Component, para que ele trate e distribua entre os atributos e métodos internos da classe Component e possa inicializar na MyClassComponent.