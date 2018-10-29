# Processos API

API REST desenvolvida em Java utilizando Spring Boot. Permite a criação de usuários e gerenciamento de processos.

### Pré-requisitos

* [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Configurar variável de ambiente JAVA_HOME apontando para o JDK
* [Maven](https://maven.apache.org/)
* [MySQL](https://dev.mysql.com/downloads/mysql/) - (Configurar usuário root com a senha: root)
* Docker


### Rodando o Projeto
* Clone o projeto
* Executar o arquivo processos-api.sh, que é um conjunto de comandos que permite rodar o projeto usando o Docker

```
$ git clone < url-projeto.git >
$ cd processos-api
$ ./processos-api.sh
```

## Coleções da API

Para realização de testes com a serviço da API, podem ser utilizadas a Collections da aplicação para testes de API Postman. 

* [Usuarios](https://www.getpostman.com/collections/f689e89987e150554b89)
* [Processos](https://www.getpostman.com/collections/dc63194e419e805d866e)
* [Permissões](https://www.getpostman.com/collections/1243819303e53f3da872)

## Considerações

A API não contém todos os requisitos especificados no desafio, porém vou falar alguns pontos que gostaria de ter feito caso tivesse um tempo maior:

* Servidor de configuração centralizado - Spring Cloud Config Server
* Servidor de registro - Spring Cloud Netflix Eureka Server
* Servidor de Autorização - Spring Security OAuth2
* Criar docker compose para subir as duas aplicações e o banco de dados em conjunto
* Foram consideradas algumas variáveis estáticas para a construção da imagem do docker,
posteriormente iria alterar para utilização de variáveis de ambiente


## Autor

* ** Henrique Rodrigues - [Hickramos](https://github.com/hickramos)


