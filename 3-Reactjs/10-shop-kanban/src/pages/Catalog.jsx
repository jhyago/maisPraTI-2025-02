// Página de Catálogo reutilizando ProductCard e grid responsivo
import React, { useEffect, useState } from 'react'; // Importa React e hooks
import ProductCard from '../components/ProductCard.jsx'; // Importa o card de produto
import styles from '../App.module.css'; // Reutiliza o CSS Module do App para layout e grid

// Define o componente de página de catálogo
export default function Catalog({ products, onAdd }) { // Recebe lista de produtos e handler
  const [loading, setLoading] = useState(true); // Estado de carregamento para skeleton

  useEffect(() => { // Efeito para simular atraso
    const timer = setTimeout(() => setLoading(false), 1200); // Simula 1.2s
    return () => clearTimeout(timer); // Limpa no unmount
  }, []); // Executa uma vez

  return ( // Retorna JSX
    <section className={styles.grid} aria-label="Lista de produtos"> {/* Grid com rótulo acessível */}
      {loading // Se estiver carregando
        ? Array.from({ length: 6 }).map((_, i) => ( // Cria 6 skeletons
            <ProductCard key={'skeleton-' + i} loading /> // Card em estado loading
          )) // Fim do array de skeletons
        : products.map((p) => ( // Caso contrário, renderiza produtos reais
            <ProductCard // Componente de card
              key={p.id} // Chave única
              product={p} // Objeto do produto
              onAdd={() => onAdd(p.id)} // Chama handler de adicionar
              buttonVariant={p.buttonVariant} // Variante do botão derivada da tag
            /> // Fecha ProductCard
          )) /* Fim do map de produtos */} {/* Fim do ternário */}
    </section> // Fecha a seção
  ); // Fim do retorno
} // Fim do componente Catalog
