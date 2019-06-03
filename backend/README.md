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
