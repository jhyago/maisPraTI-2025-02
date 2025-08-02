// Criar um componente que exibe as informações de um produto. O componente pai (App) vai passar os dados do produto (nome, preço, descrição) como props para o componente filho (Produto), e o componente filho vai exibir essas informações.

function Produto(props) {
    return (
        <div>
            <h1>{props.name}</h1>
            <p>{props.price}</p>    
            <p>{props.description}</p>    
        </div>
    )
}

function App() {
    const product = {
        name: "Avell XLR8",
        price: "+ de 7000",
        description: "30 minutos e acabou o pagode"
    }

    return(
        <Produto name={product.name} price={product.price} description={product.description} />
    )
}

export default Appexport default function Produto(props) {
    return(
        <>
            <div>
                <h2>{props.name}</h2>
                <p>{props.price}</p>
                <p>{props.description}</p>
            </div>
        </>
    )
}