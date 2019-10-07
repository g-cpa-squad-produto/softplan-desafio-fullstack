# Softplan Desafio Fullstack (Backend)

Isadora Moysés de Souza

#### 1. Ambiente

A aplicação backend utiliza a porta 8080 do localhost.

#### 2. Stack, ferramentas e bibliotecas utilizadas no backend (Projeto Maven)

Para desenvolvimento do projeto backend foi utilizado o IntelliJ IDEA.
Foi desenvolvido utilizando Spring Boot 2.1.9 RELEASE, Spring Data, Spring Security, JJWT, DevTools.
O projeto utiliza Banco de dados em memória H2, escolha feita por praticidade por não precisar configurar um outro banco de dados.

#### 3. Dados iniciais

Ao iniciar o backend, três usuários serão criados com email e senha, sendo eles um admin (admin@softplan.com.br, 123456 ), um triador (triador@softplan.com.br, 123456) e um finalizador (finalizador@softplan.com.br, 123456).

#### 4. Build do backend
Importar como projeto Maven na IDE, rodar a aplicação após carregar as dependências.
Pode-se tentar rodar a aplicação também utilizando o comando mvn spring-boot:run na pasta da aplicação.


#### 5. Alguns dos serviços disponíveis no backend no endereço ```http://localhost:8080```, lembrando que é preciso de autenticação/autorização para acessá-las:

- **[POST]** */api/auth* : autentica usuario com email e senha informados

- **[GET]** */api/usuario/all* : lista todos os usuários
- **[GET]** */api/usuario/{usuarioId}* : lista um usuário pelo *usuarioId* informado
- **[POST]** */api/usuario* : cadastra um novo usuário
- **[PUT]** */api/usuario/{usuarioId}* : atualiza os dados de um usuário do sistema
- **[DELETE]** */api/usuario/{usuarioId}* : elimina o usuário pelo *usuarioId* informado


- **[GET]** */api/processo/all* : busca todos os processos cadastrados
- **[GET]** */api/processo/{processoId}* : busca um processo pelo *processoId* informado
- **[GET]** */api/processo/pendentes* : busca os processos pendentes de parecer
- **[POST]** */api/processo* : cadastra um novo processo
- **[DELETE]** */api/processo/{processoId}* : elimina o processo pelo *processoId* informado


- **[GET]** */api/parecer/{processoId}* : busca parecer de um processo pelo *processoId* informado
- **[GET]** */api/parecer//usuario/{idUsuario}* : busca pareceres feitos por um usuarios pelo *idUsuario* informado
- **[POST]** */api/parecer/{usuarioId}/{processoId}* : usuario cadastra um novo parecer para um processo informado

OBS: nem todas as rotas acimas foram utilizadas no frontend, mas estão disponíveis para testes no banckend.

Em caso de dúvidas de entendimento ou para rodar a aplicação, entrar em contato através do email isadora_msouza@hotmail.com / isadora@scadiagro.com.br.
