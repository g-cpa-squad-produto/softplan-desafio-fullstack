# Desafio implementador fullstack

### Desafio
- Desenvolver uma aplicação web responsável por gerenciar processos.
- *Soluções parcias serão aceitas.*
- Visão de administrador
	- Incluir, excluir, atualizar e visualizar usuários,
- Visão de usuário-triador
	- Incluir e visualizar processos,
	- Atribuir um ou mais usuários a realizar um parecer sobre um processo,
- Visão de usuário-finalizador
	- Visualizar processos pendentes de parecer.
	- Incluir o parecer sobre o processo.


# Desenvolvimento
### Bibliotecas Utilizadas
- React Router
- React Dom
- Query String para parse de buscas via url
- Bootstrap e Reactstrap para layout
- Autenticação via JWT

### Funcionalidades implementadas
- Administrador pode:
    - Incluir, excluir, atualizar e visualizar usuários
    - Incluir, excluir, atualizar e visualizar processos
- Usuário Triador pode:
    - Incluir processos
    - Excluir, atualizar e visualizar processos criados por ele
    - Vincular de inúmeros usuários finalizadores a um processo criado
    - Visualizar pareceres sobre seus processos
- Usuário Finalizador pode:
    - Visualizar processos aos quais está vinculado
    - Escrever pareceres sobre os processos aos quais está vinculado

### Recursos
- Para consulta da api do back-end, foi utilizada a api Fecth do React
- O Token (criado no login) fica armazenado utilizando localStorage
- Foi utilizao recurso de private route para controle de autenticação para acesso às páginas 
- Foi criado um Dockerfile, que está na raiz da aplicação front-end, que é chamada pelo docker-compose, que inicia a aplicação como um todo (front e back-end). Este Dockerfile utiliza o arquivo nginx.conf para configuração de um servidor nginx para rodas a aplicação no container.
- A aplicação pode ser rodada a partir da imagem disponível no Docker Hub. Consultar readme da raiz do projeto
- Se desejar rodar a aplicação manualmente a partir da raiz do front-end, executar os comandos
    - Se utilizar o yarn:
        - yarn install
        - yarn start
    - Ou então:
        - npm install
        - npm start 
