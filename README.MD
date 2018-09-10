# ProcessAdmin

Sistema administrador de processos, com controle de usuários e perfil de acesso.

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


### Tecnologia utilizadas
- O sistema foi desenvolvido utilizando AngularJS 6, no frontend, e Java (SpringBoot), no backend, acessando banco de dados NoSQL (MongoDB).

## Backend (Process-API)

API do sistema administrador de processos com Java e Spring Boot.

### Pré-requisitos
- Java 1.8
- Spring Tools Suite 3.9.5;
- MongoDB 4.0;
- Spring Boot 1.5.15 [SpringData, SpringRest e SpringSecurity];
- JWT (Componente de segurança) 0.9.5;

### Arquitetura

Explicando a arquitetura do backend de maneira simplificada, segue este modelo:

Controllers => Services => Repository => Entity
				     |=> MongoDB

Desenvolvida no padrão REST, sobre uma camada de segurança para gerenciar o acesso aos serviços do sistema.

### Detalhes da API RESTful

A API RESTful ProcessAdmin contém as seguintes características:

- Projeto criado com Spring Boot e Java 8;
- Banco de dados MongoDB e Spring Data;
- Autenticação e autorização com Spring Security e tokens JWT (JSON Web Token);

#### Serviços do Usuário (UserService)
```bash
POST    /api/auth                       # Autenticação do usuário, retorna o token de autenticação
GET     /api/user/{id}                  # Retorna o usuário do id informado
GET     /api/user/{page}/{count}        # Retorna a lista de usuários com paginação
GET     /api/user/reviews               # Retorna a lista de usuários que realiza revisão
POST    /api/user                       # Incluir um usuário
PUT     /api/user                       # Alterar um usuário
DELETE  /api/user/{id}                  # Remove um usuário
```

#### Serviços do Processo (ProcessService)
```bash
GET     /api/process/{page}/{count}     # Retorna a lista de processos com paginação
GET     /api/process/{page}/            # Retorna a lista de processos, de acordo com filtro informado	
                     {count}/
                     {number}/
                     {subject}/
                     {status}/
                     {priority}/
                     {assigned} 		
GET     /api/process/summary            # Retorna o resumo das operações
GET     /api/process/{id}               # Retorna o processo de acordo com id informado
POST    /api/process                    # Incluir um processo
PUT     /api/process                    # Alterar um processo
PUT     /api/process/{id}/{description}	# Envia a análise de um processo
DELETE  /api/process/{id}               # Remove um processo
```

### Testes da API

Os testes dos serviços da API foram realizados com o software Postman, acessando a cada serviço desenvolvido;

### Como executar a aplicação

Certifique-se de ter o MongoDB instalado e adicionado ao PATH de seu sistema operacional, assim como o Git e Maven.

#### Baixando o projeto

Abra o prompt de comando e execute:

```
git clone https://github.com/renatoviegas/softplan-desafio-fullstack.git
```

#### Subindo o MongoDB 

Caso o serviço do mongoDB não esteja ativo, subir o serviço:
```
mongod --dbpath <DIRETORIO>\softplan-desafio-fullstack\data
```

Caso a pasta "data" não exista, pode criar.

#### Subindo o Servidor

Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.

Abra o prompt de comando e dentro da pasta `process-api`, execute:

```
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080
```

### Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:
```
mvn eclipse:eclipse
```
No Eclipse/STS, importe o projeto como projeto Maven.

## Frontend (ProcessAdmin) - Angular Application

### Pré-requisitos
- NodeJS

### Instalando as Dependências

Com o projeto já baixado, acesse a pasta `process-admin` dentro do diretorio `softplan-desafio-fullstack.git`;

No prompt, execute a seguinte linha de comando:

`npm install`

### Inicializando o Sistema

`ng serve` ou `npm start`

Acesse o sistema através da url http://localhost:4200

### Acessando

O usuário `admin@email.com.br` já será criado, com perfil de "Administrador", a senha é `12345`.