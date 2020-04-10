# Gerenciador de Processos

## Resumo

Projeto de um sistema de gerenciamento de processo.

## Tecnologias utilizadas

- O front-end foi desenvolvido utilizando o framework angular na versão 9.
- Para o back-end foi utilizado java 8, spring-boot na versão 2.2, banco de dados em memória H2 e swagger para documentação

## Instruções para uso local

- Para o front-end certifique-se de que esteja instalo o node e npm na máquina. Realize o clone do projeto e execute o comando `npm install `. 
Execute na pasta no projeto ng serve for usar o modo dev. Navegue até a url http://localhost:4200/.
- para uso da api, certifique-se que esteja instalado o java 8, maven. Ao clona o projeto execute o comando `mvn clean install` no prompt de comando ou terminal e em seguida `mvn spring-boot:run`. 
Naveque até a url http://localhost:8081/api/gerenciador-processos/swagger-ui.html para ter maiores informações sobre todas as rotas.

## Algumas funções ainda não implementadas

- Ficou pendendo a implementação das visões dos usuários, necessitando somente validar as permissão os mesmos.
- Validações não estão em funcionamente
- Função de remover incompleta
