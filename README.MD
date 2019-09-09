# Softplan Desafio Fullstack

Aplicação desafio que disponibiliza cadastro de usuários, processos e inclusão e pareceres nos processo.

## Arquitetura

Em termos de implementação, o sistema é dividio em 2 módulos/entregáveis: 
 - __back-end__: responsável por fornecer sua API Rest para o seu próprio frontend. Para mais detalhes de implementação acesse o [link](back-end/README.MD).
 - __front-end__: frontend principal deste sistema contendo as telas. Para mais detalhes de implementação acesse o [link](front-end/README.MD).
 
 
 ## Como executar o sistema
Para executar esse sistema foram disponibilizados arquivos que executam os processos de **construção**, **execução** e **parada**. Os arquivos estão na raiz do projeto.
É importante informar que os comandos dados ao Docker estão como **sudo** 
Requisitos para execução dos passos:

- Ambiente Linux
- Docker
- docker-compose  

### Passo 1 - Construção 
Os módulos são entregues em imagens **Docker**. Os comandos abaixo executam a construção das imagens tanto do front-end quanto do back-end. 
Esse processo é um pouco **demorado** devido aos artefatos que precisam ser baixados. 
 
```bash
 chmod +x build-prod.sh
./build-prod.sh
 ```

### Passo 2 - Execução 
Finalizado a consturção do sistema basta executar os comandos abaixo e os arquivos docker-compose do banco de dados e do sistema serão iniciados. São eles:

- __docker-database.yml__: Container PostgresSQL  
- __docker-projects.yml__: Containers back-end e front-end da aplicação

Um detalhe importante é que no processo também é criado uma rede chamada **softplan** para que os containers se comuniquem por ela.
 
```bash
 chmod +x start-prod.sh
./start-prod.sh
 ```

Aguarde uns segundo para que sejam iniciados os containers e está Pronto!! Basta acessar http://localhost:8080

Foram criados na carga inicial os seguintes usuários para acesso:

| __Usuário__ | __Papel__ |
| --------- | ------------- |
| administrador@teste.com | Administrador |
| triador@teste.com | Usuário Triador |
| finalizador@teste.com | Usuário Finalizador |

Todos os usuários do sistema possuem senha `123456`, inclusive os criados através do cadastro.


### Passo 3 - Parando a Execução
Finalizados os testes na aplicação basta executar o comando abaixo e todos os containers serão encerrados.

 ```bash
  chmod +x stop-prod.sh
 ./stop-prod.sh
  ```

 ## Como desenvolver no sistema
Caso seja desejável executar o sistema em modo desenvolvimento basta instalar as dependecias de cada sub-módulo executando os comandos:

- front end
 ```bash
  npm install
  ```

- back end
 ```bash
  mvn install
  ```

Em seguida executar o comando para que seja iniciado o banco, pelo docker, e aplicação front end

 ```bash
  chmod +x start-dev.sh
 ./start-dev.sh
  ```
 Após iniciados basta subir o back-end em seu IDE com as seguintes variáveis de ambiente.
 
- DATABASE_PASSWORD=postgres
- DATABASE_USER=postgres
- DATABASE_URL=postgresql://localhost:5432/postgres
- STATIC_SOURCES_URL=http://localhost:3001/
 
 
   