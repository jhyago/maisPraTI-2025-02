/*
  O que é o Tailwind CSS?

  - Tailwind é um framework CSS utilitário: em vez de criar classes customizadas
    em arquivos CSS, usamos classes pré-prontas (utilitárias) diretamente no JSX/HTML.
  - Cada classe aplica um estilo pequeno e isolado (ex.: "p-4" = padding 1rem).
  - Compondo várias classes, conseguimos criar interfaces complexas de forma rápida.
  - Vantagens: produtividade, consistência visual e menos código CSS manual.
*/

const Home = () => {
    return (
        // Container externo: ocupa a tela toda, centraliza o conteúdo e dá fundo suave
        <div className="min-h-screen flex items-center justify-center bg-gray-100 p-6">
            
            {/* Card interno: caixa branca centralizada com sombra, arredondada e espaçosa */}
            <div className="max-w-md w-full bg-white rounded-2xl shadow-lg p-8 transition hover:shadow-xl">
                
                {/* Título da página */}
                <h1 className="text-3xl font-extrabold text-gray-800 mb-4">
                    Página Inicial
                </h1>
                
                {/* Texto de boas-vindas */}
                <p className="text-gray-600 text-lg">
                    Bem-vindo à área protegida!
                </p>
            </div>
        </div>
    )
}

export default Home

/*
  Explicação das classes do Tailwind usadas:

  Container externo (<div className="min-h-screen flex items-center justify-center bg-gray-100 p-6">)
  - min-h-screen → altura mínima ocupa 100% da tela (viewport height).
  - flex → ativa flexbox.
  - items-center → alinha verticalmente o conteúdo ao centro.
  - justify-center → alinha horizontalmente ao centro.
  - bg-gray-100 → fundo cinza bem claro (cor de fundo suave).
  - p-6 → padding (espaçamento interno) de 1.5rem em todos os lados.

  Card interno (<div className="max-w-md w-full bg-white rounded-2xl shadow-lg p-8 transition hover:shadow-xl">)
  - max-w-md → largura máxima média (~28rem).
  - w-full → ocupa toda a largura disponível dentro do limite máximo.
  - bg-white → fundo branco.
  - rounded-2xl → cantos bem arredondados.
  - shadow-lg → sombra grande para destacar o card.
  - p-8 → padding interno generoso (2rem).
  - transition → habilita transições suaves (usado junto com hover).
  - hover:shadow-xl → quando o mouse passa, a sombra aumenta (efeito de elevação).

  Título (<h1 className="text-3xl font-extrabold text-gray-800 mb-4">)
  - text-3xl → fonte grande.
  - font-extrabold → peso da fonte bem forte.
  - text-gray-800 → cor cinza escura (bom contraste).
  - mb-4 → margem inferior de 1rem para separar do parágrafo.

  Parágrafo (<p className="text-gray-600 text-lg">)
  - text-gray-600 → cinza médio para dar leveza ao texto.
  - text-lg → fonte levemente maior que a padrão.
*/