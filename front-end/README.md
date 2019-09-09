# Front-end Softplan Desafio Fullstack

Front-end da aplicação Softplan desafio

## Framework 

A aplicação front-end foi desenvolvida utilizando a linguagem **javascript** e o framework Vue.js na versão 2.6.10, Foi utilizado também o **Bootstap** para construção de layouts padronizados.

## Autenticação e Autorização

Para realizar a autenticação foi utilizado o mesmo padrão do back-end o JWT. O token enviado é armazenado no `Local Storage` do navegador.

## Facilitadores

Foram utilizadas algumas bibliotecas para facilitar a implementação, são elas:

- **Axios** Biblioteca responsável pela execução das chamadas à API.

## Estutura de Componentes

Os components foram divididos em assuntos para facilitar a semantica do projeto.

 - __layout__: Pacote responsável pelos componentes reutilizáveis de layout.
 - __login__: Possui o componente de login.
 - __processos__: Possui os componentes de listagem de processos e adição de usuário, inclusão de processo e inclusão de parecer.
 - __usuario__: Possui os componentes de listagem de usuários, inclusão e edição de usuário.

## Construção

O projeto foi desenvolvido com o gerenciador de dependencias do node e posssui o arquivo **package.json**. Portanto para construção basta executar o comando

```bash
npm install
```

Em modo de desenvolvimento pode-se executar o comando abaixo para que seja executado a aplicação com um simples servidor de aplicação.
O servidor controla as alterações nos arquivos e automaticamente atualiza no browser.

```bash
npm run serve
```