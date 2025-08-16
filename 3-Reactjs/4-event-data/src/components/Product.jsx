// Importação do hook useState do React
import { useState } from 'react';

function Product() {
    // Estado para armazenar o nome, preço, categoria e descrição do produto
    const [productName, setProductName] = useState('');
    const [price, setPrice] = useState('');
    const [category, setCategory] = useState('Eletrônicos');
    const [description, setDescription] = useState('');

    // Estado para armazenar a lista de produtos cadastrados
    const [products, setProducts] = useState([]);
    // Estado para armazenar mensagens de erro
    const [error, setError] = useState('');

    // Estado para filtro de categoria de produtos
    const [categoryFilter, setCategoryFilter] = useState('Todos');

    // Funções para atualizar o estado de cada campo do formulário
    const handleNameChange = (event) => setProductName(event.target.value);
    const handlePriceChange = (event) => setPrice(event.target.value);
    const handleCategoryChange = (event) => setCategory(event.target.value);
    const handleDescriptionChange = (event) => setDescription(event.target.value);

    // Função para adicionar um novo produto
    const handleAddProduct = (event) => {
        event.preventDefault(); // Impede o envio do formulário e o recarregamento da página

        // Validações dos campos do formulário
        if (!productName || !price || !description) {
            setError('Por favor, preencha todos os campos!');
            return;
        }

        // Verifica se o preço é um número positivo
        if (isNaN(price) || Number(price) <= 0) {
            setError('O preço deve ser um valor positivo');
            return;
        }

        // Se todas as validações passarem, limpa a mensagem de erro
        setError('');

        // Cria o novo produto com as informações fornecidas
        const newProduct = {
            id: Date.now(), // Usando o timestamp para garantir um id único
            name: productName,
            price: parseFloat(price).toFixed(2), // Converte o preço para 2 casas decimais
            category,
            description
        };

        // Adiciona o novo produto à lista de produtos
        setProducts([...products, newProduct]);

        // Limpa os campos do formulário
        setProductName('');
        setPrice('');
        setCategory('Eletrônicos');
        setDescription('');
    };

    // Função para remover um produto da lista
    const handleRemoveProduct = (id) => {
        setProducts(products.filter((product) => product.id !== id));
    };

    // Filtra os produtos pela categoria selecionada
    const filteredProducts = products.filter(
        (product) => categoryFilter === 'Todos' || product.category === categoryFilter
    );

    return (
        <div>
            <h1>Cadastro de Produto</h1>
            {/* Exibe a mensagem de erro, se houver */}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            {/* Formulário para cadastro de novos produtos */}
            <form onSubmit={handleAddProduct}>
                <label>
                    Nome do Produto:
                    <input type="text" value={productName} onChange={handleNameChange} />
                </label>
                <br />
                <label>
                    Preço:
                    <input type="text" value={price} onChange={handlePriceChange} />
                </label>
                <br />
                <label>
                    Categoria:
                    <select value={category} onChange={handleCategoryChange}>
                        <option value="Eletrônicos">Eletrônicos</option>
                        <option value="Esporte">Esporte</option>
                        <option value="Brinquedos">Brinquedos</option>
                    </select>
                </label>
                <br />
                <label>
                    Descrição:
                    <input type="text" value={description} onChange={handleDescriptionChange} />
                </label>
                <br />
                <button type="submit">Adicionar Produto</button>
            </form>

            {/* Filtro por categoria */}
            <label>
                Filtrar por categoria:
                <select value={categoryFilter} onChange={(event) => setCategoryFilter(event.target.value)}>
                    <option value="Todos">Todos</option>
                    <option value="Eletrônicos">Eletrônicos</option>
                    <option value="Esporte">Esporte</option>
                    <option value="Brinquedos">Brinquedos</option>
                </select>
            </label>

            {/* Exibe a lista de produtos filtrados */}
            <ul>
                {filteredProducts.map((product) => (
                    <li key={product.id}>
                        <strong>{product.name}</strong> - ${product.price} - {product.category}
                        <p>{product.description}</p>
                        <button onClick={() => handleRemoveProduct(product.id)}>Remover</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Product;