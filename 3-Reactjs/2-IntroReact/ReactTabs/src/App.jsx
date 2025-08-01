import { useState } from 'react'

const content = [
  { 
    label: "Por que React?",
    items: [
      "Grêmio tri da libertadores",
      "Grêmio tri da libertadores",
      "Grêmio tri da libertadores"
    ]
  },

  { 
    label: "Por que não React?",
    items: [
      "Grêmio tetra da libertadores",
      "Grêmio tetra da libertadores",
      "Grêmio tetra da libertadores"
    ]
  },

  { 
    label: "Por que o Grêmio?",
    items: [
      "Grêmio penta da libertadores",
      "Grêmio penta da libertadores",
      "Grêmio penta da libertadores"
    ]
  },

]

function App() {

  const [indexAbaAtiva, atualizaIndexAbaAtiva] = useState(0)

  return (
    <>
      <div>
        <header>
          <h1>ReactJs</h1>
          <p>Estou estudando React</p>
        </header>

        <div>
          <menu>
            {content.map((tab, index) => (
              <button key={tab.label} onClick={() => atualizaIndexAbaAtiva(index)}>{tab.label}</button>
            ))}
          </menu>

          <div>
            <ul>
              {content[indexAbaAtiva].items.map((item) => (
                <li key={item}>{item}</li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </>
  )
}

export default App
