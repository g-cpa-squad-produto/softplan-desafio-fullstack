# Desafio implementador fullstack
## Overview 

Tres tipos de usuários, um com cada atribuição

- Visão de administrador
	- basicamente CRUD de usuários,
- Visão de usuário-triador
	- Visualiza os processos criados por ele,
	- Ao criar o processo, já define os finalizador que irá dar o parecer,
- Visão de usuário-finalizador
	- Listagem dos processos atribuidos apenas a ele.
	- Edita o parecer para incluir seu comentario.
	- Poderia ter definido que, ao editar o processo, no back definir como concluido e fechar para edição, mas achei melhor deixar aberto  

![class_diagram](/class-diagram.png)

### Usage
Pode ser rodado os dois modulos em separado ou rodar o script no front que ira compilar as páginas dentro do resource/static do java
Esta aplicação pode ser startada a partir da classe `com.example.demo.DemoApplication`

### Prerequisites
É necessário ter instalado e disponível no $PATH os itens:

* [Java 1.8](http://java.oracle.com)
* [Apache maven 3.x](http://maven.apache.org/)
* [npm 12](https://www.npmjs.com/get-npm)

Após clonar o projeto, o backend pode ser buildado a partir do terminal rodando o comando na raiz /backend:

```
mvn package
```

O front pode ser buildado a partir do terminal rodando o comando na raiz /frontend:

```
npm install
npm run build
```


### Utilitário
* [Swagger](http://localhost:8080/swagger-ui.html)
* [h2 console](http://localhost:8080/h2-console)
