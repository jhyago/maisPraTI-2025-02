// Card de produto com estados de loading, hover, focus e acessibilidade
import React, { useState } from 'react'; // Importa React e hook useState
import styles from './ProductCard.module.css'; // Importa o CSS Module do card

// Função utilitária para formatar preço em BRL
const formatPrice = (v) => { // Declara a função formatPrice que recebe um valor
  return v.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }); // Usa Intl para formatar em Real
}; // Fim da função formatPrice

// Componente ProductCard
export default function ProductCard({ product, onAdd, buttonVariant = 'solid', loading = false }) { // Declara o componente com props
  const [pending, setPending] = useState(false); // Estado local para simular operação de adicionar

  // Calcula o rótulo acessível de rating
  const ratingLabel = product ? `${product.rating} de 5 estrelas` : 'Carregando'; // Define o texto para leitores de tela

  // Manipula clique no botão "Adicionar"
  const handleClick = () => { // Declara função de clique
    if (!product || pending) return; // Evita múltiplos cliques ou quando não há produto
    setPending(true); // Define o estado para indicar operação em andamento
    setTimeout(() => { // Simula um pequeno atraso de processamento
      onAdd(product.id); // Chama o callback informando o id
      setPending(false); // Libera o botão após simulação
    }, 600); // Duração do atraso em milissegundos
  }; // Fim do handleClick

  // Renderiza o card (skeleton ou conteúdo real)
  if (loading) { // Verifica se deve renderizar o skeleton
    return ( // Retorna JSX do esqueleto
      <article className={styles.card} aria-busy="true" aria-label="Produto carregando"> {/* Elemento semântico article para o card */}
        <div className={`${styles.media} ${styles.skeleton}`} /> {/* Área de imagem como skeleton */}
        <div className={`${styles.content}`}> {/* Área de conteúdo */}
          <div className={`${styles.title} ${styles.skeleton}`} /> {/* Título skeleton */}
          <div className={`${styles.price} ${styles.skeleton}`} /> {/* Preço skeleton */}
          <div className={`${styles.rating} ${styles.skeleton}`} /> {/* Rating skeleton */}
          <div className={`${styles.actions}`}> {/* Área de ações */}
            <div className={`${styles.button} ${styles.skeleton}`} /> {/* Botão skeleton */}
          </div> {/* Fecha ações */}
        </div> {/* Fecha conteúdo */}
      </article> // Fecha o article skeleton
    ); // Finaliza retorno
  } // Fim do bloco de skeleton

  // Conteúdo do card quando não está carregando
  return ( // Inicia retorno JSX do card real
    <article className={styles.card} aria-label={product.title}> {/* Article com rótulo do produto */}
      <div className={styles.badges}> {/* Contêiner de badges */}
        {product.tag && ( // Verifica se há tag
          <span className={styles.badge} aria-label={`Tag: ${product.tag}`}>{product.tag}</span> // Mostra a tag com rótulo acessível
        )} {/* Fecha condicional da tag */}
      </div> {/* Fecha contêiner de badges */}

      <a href="#" className={styles.mediaLink} aria-label={`Ver detalhes de ${product.title}`}> {/* Link envolvendo a imagem */}
        <img
          className={styles.media} // Classe para estilo e aspect-ratio
          src={product.image} // URL da imagem (placeholder)
          alt={product.title} // Texto alternativo descritivo
          loading="lazy" // Ativa lazy loading da imagem
          width="512" // Largura intrínseca para layout estável
          height="512" // Altura intrínseca para layout estável (1:1)
        /> {/* Fecha a imagem */}
      </a> {/* Fecha o link da imagem */}

      <div className={styles.content}> {/* Área textual do card */}
        <h2 className={styles.title} title={product.title}> {/* Título com clamp de 2 linhas e title completo */}
          {product.title} {/* Texto do título do produto */}
        </h2> {/* Fecha h2 */}

        <p className={styles.price}> {/* Preço do produto */}
          {formatPrice(product.price)} {/* Exibe o valor formatado */}
        </p> {/* Fecha p do preço */}

        <div className={styles.rating} role="img" aria-label={ratingLabel}> {/* Exibe rating com rótulo aria */}
          {Array.from({ length: 5 }).map((_, i) => ( // Cria 5 estrelas
            <span key={i} aria-hidden="true"> {/* Cada estrela é apenas decorativa para leitores de tela */}
              {i < Math.round(product.rating) ? '★' : '☆'} {/* Preenche conforme a nota */}
            </span> // Fecha a estrela
          ))} {/* Fecha o map das estrelas */}
        </div> {/* Fecha rating */}

        <div className={styles.actions}> {/* Área do botão */}
          <button
            type="button" // Tipo do botão
            className={`${styles.button} ${styles[buttonVariant]}`} // Aplica variante via CSS Module
            onClick={handleClick} // Handler de clique
            disabled={pending} // Desabilita quando em operação
            aria-disabled={pending} // Reflete o estado para tecnologia assistiva
            aria-busy={pending} // Indica que está processando
          > {/* Abre o conteúdo do botão */}
            {pending ? 'Adicionando…' : 'Adicionar'} {/* Texto muda conforme estado */}
          </button> {/* Fecha o botão */}
        </div> {/* Fecha área de ações */}
      </div> {/* Fecha conteúdo */}
    </article> // Fecha o article
  ); // Finaliza retorno
} // Fim do componente ProductCard
