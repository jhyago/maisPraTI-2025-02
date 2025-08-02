import Produto from "./Produto"
import Contador from "./Contador"

function Button(props) {
  return <button onClick={props.onClick}>Clique aqui!</button>
}

function App() {

  const productInfo = {
    name: "Smartphone Samsung S25 Ultra",
    price: 7999.90,
    description: "Smartphone de última geração com câmera quádrupla e zoom de 100x"
  }

  const handleClick = () => {
    alert("Botão clicado!")
  }
  
  return (
    <>
      <Button onClick={handleClick}/>

      <Produto name={productInfo.name} price={productInfo.price} description={productInfo.description} />

      <Contador />
    </>
  )
}

export default App
