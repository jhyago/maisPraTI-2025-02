function Greeting(props) {
    return <h1>Olá, {props.name}</h1>
}

function Aplicativo() {
    <Greeting name='Jaques'/>
}

// Criar um componente que exibe as informações de um produto. O componente pai (App) vai passar os dados do produto (nome, preço, descrição) como props para o componente filho (Produto), e o componente filho vai exibir essas informações.

export default Greeting