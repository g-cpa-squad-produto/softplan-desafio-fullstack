# Processos APP

Aplicação desenvolvida com React e Bootstrap. Permite a criação de usuários e gerenciamento de processos.

### Pré-requisitos

* [NodeJs](https://nodejs.org/en/)

### Rodando o Projeto

* Instale o NodeJs
* Clone o projeto
* No diretório do projeto instale as dependências
* Inicie o servidor

```
$ git clone < url-projeto.git >
$ cd processos-app
$ npm install
$ npm start
```

### Gerando build de produção

```
$ npm run build
```

## Considerações

Quando comecei a desenvolver o frontend, não tinha tanto conhecimento em React, porém decidi utilizar ele para atender a sugestão do desafio e
para ter também um aprendizado um pouco maior com o framework.

* A aplicação não está contemplando o login, porém criei uma tela simples de login que aceita qualquer usuário e senha e atribui um status de logado ao usuário.
* Todas as páginas podem ser acessadas sem a necessidade do login
* Criar docker compose para subir as duas aplicações e o banco de dados em conjunto
* Foram consideradas algumas variáveis estáticas para a construção da imagem do docker,
posteriormente iria alterar para utilização de variáveis de ambiente


## Autor

* ** Henrique Rodrigues - [Hickramos](https://github.com/hickramos)



