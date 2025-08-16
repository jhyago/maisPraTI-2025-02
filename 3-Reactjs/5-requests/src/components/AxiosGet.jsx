// Importação dos hooks useState e useEffect do React
import { useState, useEffect } from 'react';
// Importação do Axios para realizar requisições HTTP
import axios from 'axios';

const AxiosGet = () => {
    // Definindo estado para armazenar os dados dos usuários
    const [users, setUsers] = useState([]);
    // Estado para armazenar qualquer erro que ocorrer durante a requisição
    const [error, setError] = useState(null);
    // Estado para indicar se os dados estão sendo carregados
    const [loading, setLoading] = useState(true);

    // Função assíncrona para buscar os usuários
    const getUsers = async () => {
        try {
            // Realiza a requisição GET para obter os usuários
            const response = await axios.get("https://jsonplaceholder.typicode.com/users");
            // Atualiza o estado 'users' com os dados recebidos da requisição
            setUsers(response.data);
            // Após os dados serem carregados, define 'loading' como false
            setLoading(false);
        } catch (err) {
            // Se houver erro, define a mensagem de erro no estado
            setError('Erro ao carregar usuários: ' + err.message);
            // Define 'loading' como false após o erro
            setLoading(false);
        }
    };

    // useEffect para chamar getUsers quando o componente for montado
    useEffect(() => {
        getUsers();
    }, []); // O array vazio significa que o efeito será executado apenas uma vez, quando o componente for montado

    return (
        <div>
            {/* Exibe uma mensagem de carregamento enquanto os dados estão sendo obtidos */}
            {loading && <p>Carregando...</p>}

            {/* Exibe uma mensagem de erro, caso ocorra algum problema durante a requisição */}
            {error && <p style={{ color: 'red' }}>Erro: {error}</p>}

            {/* Exibe a lista de usuários, caso os dados sejam carregados com sucesso */}
            <ul>
                {users.length > 0 ? (
                    users.map((user) => (
                        <li key={user.id}>{user.name} - {user.email}</li>
                    ))
                ) : (
                    <p>Nenhum usuário encontrado.</p>
                )}
            </ul>
        </div>
    );
};

export default AxiosGet;
