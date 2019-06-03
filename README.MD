# Gerenciador de Processos

## Configurando o Backend (api-processos)

+ **Criando o banco e dados PostgreSQL**

	```bash
	psql> create database processos
	```

+ **Configurando as credenciais de acesso ao banco de dados**

	```yml
	# backend/src/main/resources/application.yml
	spring:
	    datasource:
	        url: jdbc:postgresql://localhost:5432/processos
	        username: <YOUR_DB_USERNAME>
	        password: <YOUR_DB_PASSWORD>
	```

+ **Rodando o backend**

	```bash
	mvn spring-boot:run
	```

+ **Endpoints disponíveis**

	```bash
	http://localhost:8085/processos/api/usuarios
	http://localhost:8085/processos/api/processos
	http://localhost:8085/processos/api/processos-atribuidos
	http://localhost:8085/processos/api/atribuicoes
	http://localhost:8085/processos/api/pareceres
	```

## Configurando o Frontend (app-processos)

+ **Resolvendo dependências e rodando o frontend**

	```bash
	cd frontend
	npm install && npm run start
	```

+ **Acessando a aplicação**

	```bash
	http://localhost:3000
	```

## Acessando a aplicação

A aplicação pode ser acessada com os usuários padrões 'admin@dominio.com', 'triador@dominio.com' ou 'finalizador@dominio.com', utilizando a senha '123456'.

## Próximos passos

* Implementar autenticação por OAuth 2.0;
* Documentar a API utilizando OpenAPI 3.0;
* Tratar o layout da aplicação (centralizar elementos, dimensionar colunas, tratar responsividade);
* Isolar os styles em arquivos separados do código fonte;