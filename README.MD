# Processess Management APP

### Rodando as aplicações
- Frontend (React)
 - npm install
  - Aguardar instalação
 - npm run
  - Sobe a aplicação, disponibilizando em http://localhost:3000/
- Backend (Java)
 - Spring Boot App
  - Rodar aplicação. A porta configurada 8080

### Decisões Tecnológicas
- Frontend
 - React
 - redux-form
  - No início pareceu a melhor ideia
 - formik
  - Substituindo o redux-form. Ágil, ótima documentação e diferente do redux-form faz coisas simples
	- Faltou migrar a tela de login e criação de usuário.
 - yup
  - Utilizado para validação de campos
- Backend
 - Java 1.8
 - Spring Boot 2.1.0
 - Banco H2

### Funcionalidades
- Visão de administrador
 - Incluir, excluir, atualizar e visualizar usuários
- As demais ficaram fora por priorização do tempo.
- Mostrar um contexto por completo em utilização.

### Usuários
- Administrador
 - E-mail
  - admin@gmail.com
 - Senha
  - 3333
- Usuário-Triador
 - E-mail
  - screening@gmail.com
 - Senha
  - 1111
- Usuário-Finalizador
 - E-mail
  - finisher@gmail.com
 - Senha
  - 2222
