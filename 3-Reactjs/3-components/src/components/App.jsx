import Produto from "./Produto"
import Contador from "./Contador"

function Button(props) {
  // Retorna um botão com um evento 'onClick' que usa a função passada através de 'props'
  // Quando clicado, o botão executará a função recebida via 'props.onClick'
  return <button onClick={props.onClick}>Clique aqui!</button>
}

// Define o componente principal App
function App() {

  const productInfo = {
    name: "Smartphone Samsung S25 Ultra",
    price: 7999.90,
    description: "Smartphone de última geração com câmera quádrupla e zoom de 100x"
  }

  const handleClick = () => {
    alert("Botão clicado!")
  }

  // Renderiza o componente App
  // Aqui, o componente Button é utilizado e a função 'handleClick' é passada para ele como a prop 'onClick'
  return (
    <>
      {/* Chama o componente Button e passa a função 'handleClick' como prop */}
      <Button onClick={handleClick}/>

      <Produto name={productInfo.name} price={productInfo.price} description={productInfo.description} />

      <Contador />
    </>
  )
}

// Exporta o componente App como padrão para que ele possa ser importado em outros arquivos
export default App