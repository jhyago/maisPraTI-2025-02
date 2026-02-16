import { useState } from "react";

export default function Product() {
    const [name, setName] = useState('')
    const [price, setPrice] = useState('')
    const [description, setDescription] = useState('')
    const [category, setCategory] = useState('Casa')

    const handleNameChange = (event) => setName(event.target.value)
    const handlePriceChange = (event) => setPrice(event.target.value)
    const handleDescriptionChange = (event) => setDescription(event.target.value)
    const handleCategoryChange = (event) => setCategory(event.target.value)

    const handleSubmit = (event) => {
        event.preventDefault()


    }

    return(
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="name">Nome do produto: </label>
                <input name="name" type="text" id="name" value={name} onChange={handleNameChange} required />
            </div>
            <div>
                <label htmlFor="price">Preço: </label>
                <input name="price" type="text" id="price" value={price} onChange={handlePriceChange} required />
            </div>
            <div>
                <label htmlFor="category">Categoria: </label>
                <select name="category" id="category" value={category} onChange={handleCategoryChange} required>
                    <option value="Casa">Casa</option>
                    <option value="Carro">Carro</option>
                    <option value="Jardinagem">Jardinagem</option>
                    <option value="Moda">Moda</option>
                </select>
            </div>
            <div>
                <label htmlFor="description">Descrição: </label>
                <textarea name="description" id="description" value={description} onChange={handleDescriptionChange} rows="4"cols="50" required />
            </div>
            <button>Enviar</button>
        </form>
    )
}