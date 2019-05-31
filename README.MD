# SOFTPLAN - BackEnd 

## Configurações para executar a aplicação

### Banco de dados: Postgres 9.6
* instalar e configurar o usuario/senha abaixo e criar o database:
* user: postgres
* pass: postgres
* database: bd_softplan

### Lombok
* Configurar plugin lombok caso executa o projeto pela IDE IntelliJ
* https://www.baeldung.com/lombok-ide

## Executando a Aplicação no IntelliJ

  Clonar/baixar o repositório e importar no IntelliJ;
 
 ### Backend

 1. Executar a classe **../br/com/softplan/marcusvoltolim/MarcusVoltolimApplication.java** como **SpringApplication**
    1. Caso não esteja habilitado para execução, clique com o direito no arquivo **softplan_back_end/pom.xml** e clique em _Add as Maven Project_
 2. Após executar o backend estará disponível em: http://localhost:8080/usuario/
    1. Será criado 3 usuários com perfis de admin, triador e finalizador e será inserido um processo.


 ### Frontend

 1. Clique com o botão direito no arquivo **softplan_front_end/package.json** e clique na opção _Run 'npm install'_ para instalar as dependencias

    1. Ou abra o terminal navegue até a raiz do projeto **softplan_front_end** e execute o comando **npm install**

 2. abra o terminal navegue até a raiz do projeto **softplan_front_end** e execute o comando **ng serve** ou **npm start**
 2. Após executar o frontend estará disponível em: http://localhost:4200
