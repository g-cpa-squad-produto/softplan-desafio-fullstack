### Executando o Banco de Dados

-- O banco de dados foi feito em MongoDB para executá-lo será preciso:

- Ter o mongo instalado na máquina. Para saber se tem o mongo instalado na máquina, Abrir o terminal e digite `mongo -version`
- Como o terminal aberto digite `mongod`
- O mongodb deve ser executado na porta 27017

### Observações

- Como não deu tempo para aprender e gerar o docker compose e mandar o projeto, será preciso inserir um usuário admin com o sequinte endpoint:
- POST localhost:8080/api/auth/register
- com o body

```json
{ "username": "admin", "password": "admin", "roles": ["ADMIN"] }
```

- acesso com a senha e usuario adiciona acima
- Lembrando que não é possível acessar endpoints sem o token JWT

### EndPoints

-- Endpoints de acesso sem restrição:

- POST /api/auth/register
- POST /api/auth/login

-- Endpoints com restrição

-- Visão de administrador

- GET /api/users - pegar todos os usuários
- POST /api/users - salvar usuários
- POST /api/users/{id} - atualiza o usuário
- Delete /api/users/{id} - deleta o usuário

-- Visão de usuário-triador

- GET /api/proccess - pegar todos os processos
- POST /api/proccess - salvar processos
- PUT /api/proccess/{id} - atualiza o processo
- Delete /api/proccess/{id} - deleta o processo

-- Visão de usuário-finalizador

- GET /api/comments - pegar todos os pareceres
- POST /api/comments - salvar processos
- PUT /api/comments/{id} - atualiza o parecer
- Delete /api/comments/{id} - deleta o parecer

### Executar api:

- Clonar o repositório do github
- Entrar na pasta back-end pelo terminal e digitar `mvn spring-boot:run`

### Executar front-end:

- Entrar na pasta front-end pelo terminal e digitar `npm install`
- Depois de instaldo as dependências execute `npm start`

### Stack Back-end:

- MongoDB
- JAVA
- Spring security
- Spring boot
- JWT Token

### Stack Front-end

- React
- React-router-dom
- Redux
- Redux-saga
- Redux-sauce
- Axios
- styled-components
