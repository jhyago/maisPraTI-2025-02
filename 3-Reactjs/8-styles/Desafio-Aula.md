### Desafio de Estilização em React “Mini Loja”

**Tarefa:**
Implemente a mesma tela (Mesma tela opcionalmente) em **4 versões**:

1. CSS Global · 2) CSS Modules · 3) Tailwind CSS · 4) styled-components.

**Tela a construir:**

* **Navbar (fixa):** logo, **toggle de tema** (claro/escuro com persistência) e **badge do carrinho**.
* **Grid de produtos (cards) responsivo:**
  ≤480px: 1 col · 481–768px: 2 cols · 769–1024px: 3 cols · ≥1025px: 4 cols.
* **Card de produto:** imagem **1:1** com espaço reservado, título **2 linhas com ellipsis**, preço, **rating (★)**, **tag** (“Novo”/“Promo”), botão “Adicionar” com variantes **solid/outline/ghost**.

**Estados/Interações obrigatórios:**

* **Hover** (elevação/sombra), **focus visível**, **disabled**, **loading com skeleton** (simule atraso).
* **Dark mode** aplicado a cores/sombras/bordas.

**Acessibilidade:**

* Navegação por teclado, `aria-*` onde fizer sentido, **contraste ≥ 4.5:1**.

**Animações:**

* Transições de 150–250ms (usar transform/opacity).

**Dados:**

* Exibir **6 produtos** (id, título, preço, rating, tag, imagem placeholder). Imagens com **lazy loading**.

**Entregáveis:**

* Quatro pastas: `01-css-global/`, `02-css-modules/`, `03-tailwind/`, `04-styled-components/`.
* Cada pasta com os componentes (`Navbar`, `ProductCard`, `Button`, `Skeleton`) e um **README curto**.

**Restrições:**

* Em cada versão, use apenas a técnica indicada (global, modules, utilitários, CSS-in-JS com `ThemeProvider` e props).

**Critérios de aceite:**

* Breakpoints exatamente como descritos.
* **Dark mode** com persistência.
* **Focus ring** visível.
* **Skeleton** sem layout shift.
* Variantes do botão consistentes nos dois temas.
* Organização clara de pastas e uso de **tokens** (cores, espaçamentos, raio, sombras).
