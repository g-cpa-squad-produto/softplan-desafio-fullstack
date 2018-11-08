### Sistema
- Foi desenvolvida uma aplicação web responsável por gerenciar processos. O sistema apresenta a possibilidade de criar novos usuários bem como definindo suas permissões que podem ser as seguintes: ADMINISTRADOR (gerencia os usuarios do sistema), TRIADOR (cria processos e atribui aos usuários com permissão de finalizador quais serão responsáveis pelo parecer a um determinado processo) e FINALIZADOR. Um mesmo usuário poderá ter uma ou mais permissões.

### Tecnologia utilizada

Para o desenvolvimento da aplicação foram utilizadas:
- No Backend foram utilizados java 8, Spring Boot 2.1.0, Banco de dados H2 (em memória), Jwt para criar o token de acesso;
- No Frontend foi utilizado Angular 7.0 integrado com o framework de estilos Materialize.css 1.0.0

### Problemas encontrados
- Devido à restrição de tempo não foi possível desenvoler o sistema em React, bem como implementar algumas funcionalidades, validações e testes mais complexos.
- Os arquivos de testes comoeçaram a dar erro ao rodar com o Docker, e como não tive tempo habil para descobrir o problema, coloquei eles em uma pasta fora do projeto.

## EXECUCAO NO DOCKER
Para Iniciar os containers:<br/>
```no shell executar (ira procurar dentro dos diretorios do backend e frontend e rodar o dockerfile de cada um)
sudo docker-compose up --build
```