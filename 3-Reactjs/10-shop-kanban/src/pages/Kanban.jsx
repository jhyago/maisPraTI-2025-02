// Página de Quadro Kanban com CSS Modules, acessibilidade e persistência
import React, { useEffect, useMemo, useRef, useState } from 'react'; // Importa React e hooks
import styles from './Kanban.module.css'; // Importa o CSS Module específico do Kanban

// Colunas disponíveis do quadro
const COLUMNS = [ // Array de colunas em ordem
  { id: 'todo', name: 'Backlog' }, // Coluna inicial (afazeres)
  { id: 'doing', name: 'Em Progresso' }, // Coluna intermediária
  { id: 'done', name: 'Concluído' }, // Coluna final
]; // Fim das colunas

// Tarefas iniciais (exemplo)
const DEFAULT_TASKS = [ // Define 6 tarefas iniciais
  { id: 't1', title: 'Definir escopo do sprint', column: 'todo', tag: 'Planejamento' }, // Tarefa 1
  { id: 't2', title: 'Levantar requisitos com o time', column: 'todo', tag: 'Descoberta' }, // Tarefa 2
  { id: 't3', title: 'Criar wireframes de alta fidelidade', column: 'doing', tag: 'Design' }, // Tarefa 3
  { id: 't4', title: 'Implementar autenticação OAuth2', column: 'doing', tag: 'Backend' }, // Tarefa 4
  { id: 't5', title: 'Escrever testes de integração', column: 'done', tag: 'QA' }, // Tarefa 5
  { id: 't6', title: 'Publicar release 1.0', column: 'done', tag: 'Release' }, // Tarefa 6
]; // Fim das tarefas

// Chave de persistência no localStorage
const STORAGE_KEY = 'kanban_tasks_v1'; // Nome da chave

// Componente principal do Kanban
export default function Kanban() { // Exporta o componente
  const [tasks, setTasks] = useState(() => { // Estado das tarefas com inicialização lazy
    const saved = localStorage.getItem(STORAGE_KEY); // Lê do localStorage
    return saved ? JSON.parse(saved) : DEFAULT_TASKS; // Converte ou usa padrão
  }); // Fim do estado

  const [filter, setFilter] = useState(''); // Estado do filtro de busca por texto
  const inputRef = useRef(null); // Ref para o input de nova tarefa

  // Persiste as tarefas sempre que mudarem
  useEffect(() => { // Efeito de persistência
    localStorage.setItem(STORAGE_KEY, JSON.stringify(tasks)); // Salva no localStorage
  }, [tasks]); // Executa quando tasks muda

  // Deriva tarefas filtradas por título
  const filtered = useMemo(() => { // Memoriza a lista filtrada
    const term = filter.trim().toLowerCase(); // Normaliza filtro
    if (!term) return tasks; // Sem filtro: retorna todas
    return tasks.filter(t => t.title.toLowerCase().includes(term)); // Filtra por título
  }, [tasks, filter]); // Dependências

  // Adiciona nova tarefa
  const addTask = (title) => { // Função para adicionar
    const trimmed = title.trim(); // Remove espaços
    if (!trimmed) return; // Ignora vazio
    const newTask = { // Monta objeto da nova tarefa
      id: 't' + Math.random().toString(36).slice(2, 8), // Gera id simples
      title: trimmed, // Usa o título informado
      column: 'todo', // Começa no Backlog
      tag: 'Nova', // Tag padrão
    }; // Fim do objeto
    setTasks(prev => [newTask, ...prev]); // Prepend na lista
  }; // Fim de addTask

  // Move tarefa para coluna anterior ou próxima
  const moveTask = (taskId, dir) => { // Recebe id e direção (-1 esquerda, +1 direita)
    setTasks(prev => { // Atualiza lista
      const map = Object.fromEntries(COLUMNS.map((c, i) => [c.id, i])); // Mapa coluna->índice
      return prev.map(t => { // Mapeia tarefas
        if (t.id !== taskId) return t; // Mantém outras
        const i = map[t.column]; // Índice atual
        const nextIndex = Math.min(Math.max(i + dir, 0), COLUMNS.length - 1); // Limita faixa
        return { ...t, column: COLUMNS[nextIndex].id }; // Troca coluna
      }); // Fim do map
    }); // Fim do setTasks
  }; // Fim de moveTask

  // Exclui tarefa
  const removeTask = (taskId) => { // Recebe id da tarefa
    setTasks(prev => prev.filter(t => t.id != taskId)); // Remove pelo id
  }; // Fim de removeTask

  // Manipuladores de drag and drop (HTML5)
  const onDragStart = (e, taskId) => { // Ao iniciar arraste
    e.dataTransfer.setData('text/plain', taskId); // Coloca id no payload
    e.dataTransfer.effectAllowed = 'move'; // Indica operação de mover
    e.currentTarget.setAttribute('aria-grabbed', 'true'); // Ajuda tecnologias assistivas
  }; // Fim de onDragStart

  const onDragEnd = (e) => { // Ao finalizar arraste
    e.currentTarget.setAttribute('aria-grabbed', 'false'); // Reseta atributo
  }; // Fim de onDragEnd

  const onDrop = (e, columnId) => { // Ao soltar em uma coluna
    e.preventDefault(); // Previne navegação
    const id = e.dataTransfer.getData('text/plain'); // Recupera id
    setTasks(prev => prev.map(t => (t.id === id ? { ...t, column: columnId } : t))); // Atualiza coluna
  }; // Fim de onDrop

  const onDragOver = (e) => { // Ao arrastar sobre coluna
    e.preventDefault(); // Necessário para permitir drop
  }; // Fim de onDragOver

  // Renderização
  return ( // Inicia JSX
    <div className={styles.wrapper}> {/* Contêiner do quadro */}
      <h1 className={styles.title}> {/* Título da página */}
        Quadro Kanban {/* Texto do título */}
      </h1> {/* Fecha o título */}

      <form // Formulário para adicionar tarefas
        className={styles.toolbar} // Classe de estilo
        onSubmit={(e) => { // Manipula envio do formulário
          e.preventDefault(); // Evita reload
          addTask(inputRef.current.value); // Adiciona tarefa com o valor digitado
          inputRef.current.value = ''; // Limpa o campo
          inputRef.current.focus(); // Mantém foco para rapidez
        }} // Fim do onSubmit
        aria-label="Adicionar e filtrar tarefas" // Rótulo acessível
      > {/* Abre o formulário */}
        <label className={styles.visuallyHidden} htmlFor="newTask"> {/* Rótulo invisível para leitores de tela */}
          Nova tarefa {/* Texto do rótulo */}
        </label> {/* Fecha label */}
        <input
          id="newTask" // Id associado ao label
          ref={inputRef} // Ref para acessar valor e foco
          className={styles.input} // Classe do input
          type="text" // Tipo de campo
          placeholder="Descreva a tarefa e pressione Enter" // Placeholder orientativo
          required // Torna obrigatório
          aria-required="true" // Atributo ARIA correspondente
        /> {/* Fecha input de nova tarefa */}

        <label className={styles.visuallyHidden} htmlFor="filter"> {/* Rótulo invisível do filtro */}
          Filtrar tarefas {/* Texto do rótulo */}
        </label> {/* Fecha label */}
        <input
          id="filter" // Id do filtro
          className={styles.input} // Reaproveita estilo
          type="search" // Tipo de campo de busca
          placeholder="Filtrar por título" // Texto de ajuda
          value={filter} // Valor controlado
          onChange={(e) => setFilter(e.target.value)} // Atualiza estado
          aria-label="Filtrar tarefas por título" // Rótulo acessível
        /> {/* Fecha input de filtro */}
      </form> {/* Fecha formulário */}

      <div className={styles.board} role="list" aria-label="Colunas do kanban"> {/* Área das colunas */}
        {COLUMNS.map((col) => ( // Itera sobre as colunas
          <section
            key={col.id} // Chave única
            className={styles.column} // Estilo da coluna
            onDragOver={onDragOver} // Permite soltura
            onDrop={(e) => onDrop(e, col.id)} // Solta tarefa aqui
            aria-labelledby={`h-${col.id}`} // Relaciona com o cabeçalho
            role="group" // Grupo de itens relacionados
            tabIndex={0} // Permite foco via teclado
          > {/* Abre a seção da coluna */}
            <header className={styles.columnHeader}> {/* Cabeçalho da coluna */}
              <h2 id={`h-${col.id}`} className={styles.columnTitle}> {/* Título da coluna com id */}
                {col.name} {/* Nome da coluna */}
              </h2> {/* Fecha título da coluna */}
              <span className={styles.count} aria-label={`Total de tarefas: ${filtered.filter(t => t.column === col.id).length}`}> {/* Contador acessível */}
                {filtered.filter(t => t.column === col.id).length} {/* Número de tarefas */}
              </span> {/* Fecha contador */}
            </header> {/* Fecha cabeçalho */}

            <ul className={styles.list} role="list" aria-describedby={`d-${col.id}`}> {/* Lista de tarefas da coluna */}
              <span id={`d-${col.id}`} className={styles.visuallyHidden}> {/* Instrução invisível */}
                Arraste e solte tarefas ou use os botões para mover. {/* Texto de ajuda */}
              </span> {/* Fecha instrução */}

              {filtered // Usa tarefas filtradas
                .filter(t => t.column === col.id) // Mantém as da coluna
                .map((t) => ( // Itera tarefas
                  <li key={t.id} className={styles.card} role="listitem"> {/* Item de lista (tarefa) */}
                    <article // Conteúdo da tarefa
                      className={styles.cardInner} // Estilo do cartão
                      draggable // Torna arrastável
                      onDragStart={(e) => onDragStart(e, t.id)} // Início do arraste
                      onDragEnd={onDragEnd} // Fim do arraste
                      aria-grabbed="false" // Estado inicial não agarrado
                    > {/* Abre o article */}
                      <div className={styles.badge}> {/* Badge com a tag */}
                        {t.tag} {/* Texto da tag */}
                      </div> {/* Fecha badge */}

                      <h3 className={styles.cardTitle}> {/* Título da tarefa */}
                        {t.title} {/* Texto do título */}
                      </h3> {/* Fecha h3 */}

                      <div className={styles.cardActions}> {/* Ações da tarefa */}
                        <button
                          type="button" // Tipo de botão
                          className={`${styles.btn} ${styles.ghost}`} // Variante ghost
                          onClick={() => moveTask(t.id, -1)} // Move à esquerda
                          aria-label="Mover para a coluna anterior" // Rótulo para leitores
                          title="Mover para a coluna anterior" // Dica de ferramenta
                        > {/* Abre botão */}
                          ← {/* Seta esquerda */}
                        </button> {/* Fecha botão */}

                        <button
                          type="button" // Tipo de botão
                          className={`${styles.btn} ${styles.solid}`} // Variante sólida
                          onClick={() => moveTask(t.id, +1)} // Move à direita
                          aria-label="Mover para a próxima coluna" // Rótulo para leitores
                          title="Mover para a próxima coluna" // Dica de ferramenta
                        > {/* Abre botão */}
                          → {/* Seta direita */}
                        </button> {/* Fecha botão */}

                        <button
                          type="button" // Tipo de botão
                          className={`${styles.btn} ${styles.outline}`} // Variante outline
                          onClick={() => removeTask(t.id)} // Remove tarefa
                          aria-label="Excluir tarefa" // Rótulo acessível
                          title="Excluir tarefa" // Dica de ferramenta
                        > {/* Abre botão */}
                          Excluir {/* Texto do botão */}
                        </button> {/* Fecha botão */}
                      </div> {/* Fecha ações */}
                    </article> {/* Fecha article */}
                  </li> // Fecha item de lista
                )) /* Fim do map de tarefas */} {/* Fim do bloco de lista */}
            </ul> {/* Fecha lista */}
          </section> // Fecha seção da coluna
        ))} {/* Fim do map de colunas */}
      </div> {/* Fecha área do board */}
    </div> // Fecha contêiner
  ); // Fim do retorno
} // Fim do componente Kanban
