# Desafio implementador fullstack
Adolfo Marangoni Hengling

### Sumário

1. Ambiente
2. Stack utilizada
3. Build do sistema
4. Rodando o sistema com docker
5. Rodando o sistem sem docker
6. Acessando a aplicação
7. Acessando o gerenciador de banco de dados 

#### 1. Ambiente 

Os itens listados abaixos são sugestões para o ambiente que for construir e executar a aplicação. Essas sugestões se 
devem ao fato de evitar erros na execução dos comandos que executam tais tarefas. 

- Linux (de preferência ubuntu ou debian)
- Maven instalado globalmente
- NPM instalado globalmente
- Docker
- Docker Compose
- Git

Além disso, a aplicação utiliza as portas *8080* e *8081* do host local. Caso essas portas estejam alocadas por algum
outro processo, não será possível executar a aplicação. 

#### 2. Stack utilizada

A stack escolhida levou em consideração as sugestões feitas na descrição do desafio. 

##### Backend

- Java 8 + Spring Boot 2.1.8
- Banco de dados em memória H2
- Maven

O banco de dados *H2* foi escolhido
para facilitar o desenvolvimento e, visto que utilizamos o mecanismo *Spring Data* para realizar os acessos ao banco, 
ele poderia ser facilmente substituído por outro SGDB, como *PostgreSQL* ou *SQL SERVER*. 

##### Frontend

- Javascript + Vue.js
- Nginx

O *Nginx* foi escolhido por ser um dos servidores de arquivos estáticos mais performáticos do mercado. Com ele, é possível
servir os arquivos estáticos do frontend e também realizar o proxy para as requisições ao backend. Além disso, ele também
é capaz de realizar o balanceamento de carga em um ambiente clusterizado, onde temos vários servidores para a API 
da aplicação.

#### 3. Build do sistema

##### 3.1 Repositório

Abra um terminal e faça o clone deste repositório. Iremos nos referir a pasta ```softplan-desafio-fullstack``` como
pasta raiz do código fonte do projeto.

##### 3.2 Backend

Abra um terminal, entre na pasta raiz do código fonte do desafio e execute os comandos a seguir:

```$bash
cd processos-api
mvn clean install
```

Ao final da execução, confira se o artefato ```processos-api.jar``` foi gerado na pasta ```processos-api/target```.

##### 3.3 Frontend

Abra um terminal, entre na pasta raiz do código fonte do desafio e execute os comandos a seguir:

```$bash
cd processos-app
npm install
npm run build
```

Ao final da execução, confira se a pasta ```dist``` foi gerada dentro da pasta ```processos-app```. 

##### 3.3 Imagens Docker

Abra um terminal, entre na pasta raiz do código fonte do desafio e execute os comandos a seguir:

```$bash
chmod +x build.sh
sudo ./build.sh
```

Confira se as imagens ```adolfo-hengling/processos-api:1.0.0``` e ```adolfo-hengling/processos-app:1.0.0``` foram
geradas através do comando:

```$bash
sudo docker images
```

#### 4. Rodando o sistema com docker

Abra um terminal, entre na pasta raiz do código fonte do desafio e execute os comandos a seguir:

```$bash
chmod +x start.sh
sudo ./start.sh
```

Espere alguns segundos e acesse o endereço ```http://localhost:8080```. A aplicação terá iniciado e você estará na tela 
de escolha do usuário para acesso. Para mais informações de como acessar a aplicação, consulte o *item 6* deste documento.

#### 5. Rodando o sistem sem docker

Para subir o sistema fora dos containers docker, execute os comandos abaixo:

##### 5.1 Backend

Abra um terminal, entre na pasta raiz do código fonte do desafio e execute os comandos a seguir:

```$bash
cd processos-api/target
java -jar processos-api.jar
```

##### 5.2 Frontend

Abra um terminal, entre na pasta raiz do código fonte do desafio e execute os comandos a seguir:

```$bash
cd processos-app
npm run serve
```

#### 6. Acessando a aplicação

Após subir a aplicação, com ou sem docker, ela estará disponível no endereço ```http://localhost:8080```.

Caso seja necessário realizar testes chamando diretamente a *api*, os seguintes serviços estarão disponíveis no endereço
```http://localhost:8081```:

- **[GET]** */api/usuarios* : busca todos os usuários cadastrados
- **[GET]** */api/usuarios/{id}* : busca um usuário pelo *id* informado
- **[POST]** */api/usuarios* : cadastra um novo usuário
- **[PUT]** */api/usuarios/{id}* : atualiza os dados de um usuário do sistema
- **[DELETE]** */api/usuarios/{id}* : remove o usuário pelo *id* informado


- **[GET]** */api/processos* : busca todos os processos cadastrados
- **[GET]** */api/processos/{id}* : busca um processo pelo *id* informado
- **[GET]** */api/processos/pendentes/{usuarioId}* : busca os processos pendentes de parecer de um usuário
- **[POST]** */api/processos* : cadastra um novo processo
- **[GET]** */api/processos/{id}/pareceres* : buscar todos os pareceres de um processo
- **[POST]** */api/processos/{id}/pareceres* : cadastra um novo parecer para o processo
- **[PUT]** */api/processos/{id}/parecer/{usuarioId}* : atualiza um parecer de um usuário em um processo


#### 7. Acessando o gerenciador de banco de dados

Após subir o backend da aplicação, com ou sem docker, será possível acessar o gerenciador de banco de dados pelo 
endereço ```http://localhost:8081/h2-console``` e utilizando os seguintes dados de conexão:

- String de conexão: *jdbc:h2:mem:processos* 
- Usuário: *sa*
- Senha: *password*