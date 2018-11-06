## Desafio Full Stack Softplan app com MySql, Spring Boot, JWT, React

## Passo a passo do Spring Boot Back end app

1. **Criar MySQL database**

   ```bash
   create database desafio
   ```

2. **Alterar as configurações do MySQL de acordo com sua instalação**

   - abrir `src/main/resources/application.properties` arquivo.

   - alterar `spring.datasource.username` e `spring.datasource.password` de acordo com sua instalação do mysql

3. **Executar o app**

   Executar o spring boot app digitando a linha de command -

   ```bash
   mvn spring-boot:run
   ```

   O servidor irá rodar na porta 8081

4. **Criando Usuários**

   O sistema irá criar automaticamente um usuário de cada tipo: admin, triador e finalizador. As senhas são iguais aos usuários.

## Passo a passo do React Front end app

1. **Alterar as configurações do Backend de acordo com sua configuração**

   - abrir `frontend/src/authOrApp.js` arquivo.

   - alterar `const baseURL = "http://<seuendereco>:8081/desafiosoftplan";`

2. **Instalando as dependência e executando o projeto**

Dentro da pasta `frontend` execute os comando abaixo:

```bash
npm install && npm start
```

O front-end server irá rodar na porta `3000`.

## Docker

Entre na pasta `softplan-desafio-fullstack` -

Então exceute o comandos abaixo:

```bash
docker-compose up
```

O front-end server irá rodar na porta `8080`.
