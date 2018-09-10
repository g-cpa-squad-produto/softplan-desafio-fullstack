# Desafio implementador fullstack

## Instruções para execução do app react:

	1 - Instalar todas as dependências do projeto via o comando "npm install" no diretório raíz do projeto;
	
	2 - Alterar, caso necessário, a variável "URL_API" no arquivo "constants.js" localizado na raíz da pasta "src";
	
	3 - Executar o comando "npm start" no diretório raíz do projeto.

## Instruções para execução da api:

   1 - Por se tratar de um projeto Maven, é necessário realizar o download de todas as dependências do projeto contidas no arquivo pom.xml;
   
   2 - Configurar informações de seu banco de dados no arquivo "application.properties" dentro da pasta resource;
      
      2.1 - Trocar URL do datasource, informando também o nome do schema;
      2.2 - Trocar nome de usuário e senha nos campos username e password, respectivamente.
   
   3 - Executar script para criação do schema no banco de dados (arquivo "create-schema.sql" na pasta docs);
   
   4 - Executar script para inserção do primeiro usuário administrador (arquivo "insert-user.sql" na pasta docs);
   
   5 - Executar o projeto a partir do método main na classe Application.
   
OBS.: A senha do usuário administrador é "123456".
