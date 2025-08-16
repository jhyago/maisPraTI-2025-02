// Importa useState e useEffect do React
import { useState, useEffect } from 'react';
// Importa o axios para fazer requisições HTTP
import axios from 'axios';

// Componente funcional AxiosPost para criar um novo usuário
const AxiosPost = () => {
  // Estado para os dados do formulário: nome e email
  const [formData, setFormData] = useState({ name: '', email: '' });
  // Estado para mensagem de sucesso
  const [mensagem, setMensagem] = useState('');
  // Estado para mensagem de erro
  const [erro, setErro] = useState(null);
  // Estado para indicar carregamento
  const [loading, setLoading] = useState(false);

  // Limpa mensagens de sucesso/erro após 5 segundos
  useEffect(() => {
    if (mensagem || erro) {
      const timer = setTimeout(() => {
        setMensagem('');
        setErro(null);
      }, 5000);
      return () => clearTimeout(timer);
    }
  }, [mensagem, erro]);

  // Função para atualizar os campos do formulário
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Função de envio do formulário
  const handleSubmit = async (e) => {
    e.preventDefault(); // Evita recarregar a página
    setErro(null);
    setMensagem('');

    // Validação simples dos campos
    if (!formData.name || !formData.email) {
      setErro('Por favor, preencha nome e email.');
      return;
    }

    setLoading(true);
    try {
      // Requisição POST para criar usuário
      const response = await axios.post(
        'https://jsonplaceholder.typicode.com/users',
        formData
      );
      // Exibe mensagem de sucesso
      setMensagem(`Usuário ${response.data.name} criado com sucesso!`);
      // Limpa formulário
      setFormData({ name: '', email: '' });
    } catch (err) {
      // Exibe mensagem de erro detalhada se possível
      setErro(
        err.response?.data?.message || err.message || 'Erro ao criar usuário.'
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Criar Novo Usuário</h1>

      {/* Exibe erro se houver */}
      {erro && <p style={{ color: 'red' }}>{erro}</p>}
      {/* Exibe mensagem de sucesso se houver */}
      {mensagem && <p style={{ color: 'green' }}>{mensagem}</p>}

      {/* Formulário com onSubmit */}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">Nome:</label>
          <input
            id="name"
            name="name"
            type="text"
            placeholder="Nome"
            value={formData.name}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            name="email"
            type="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
          />
        </div>
        <button type="submit" disabled={loading || !formData.name || !formData.email}>
          {loading ? 'Enviando...' : 'Criar Usuário'}
        </button>
      </form>
    </div>
  );
};

export default AxiosPost;