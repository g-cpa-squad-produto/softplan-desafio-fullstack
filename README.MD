# Teste softplan-desafio-fullstack
[![Sofplan](https://www.softplan.com.br/wp-content/themes/softplan-2019/imagens/logo-softplan-blue.svg)](https://www.softplan.com.br/en/solucoes/)

##### Como rodar o projeto
  - Faça o clone do repositório
- Usando Docker
  - Libere as portas 8080 e 5432 do seu sistema.
  - rode o commando ```docker-compose up --build``` na pasta raiz do projeto
- Usando apenas o Maven + Postgres
  - Abra o arquivo ```src/main/resources/application.properties```
  - Coloque as suas configurações no dataSource, 
  - Trocar o host ```processes-database``` para ```localhost``` ou outro de sua preferência.
  - entre na pasta processesapi
  - rode o comando ```mvn spring-boot:run``` 
 
- Configuração do postman
  - Abra a pasta raíz do projeto e importe o arquivo ```processes.postman_collection.json``` no cli do postman.

- Procedimento para testar end-points no Postman
  - abra a pasta public e altere o body para colocar seus dados
  - após um registro bem sucedido, a response virá com um header ```Authorization``` crie um header Authorization em seu request e adicione o valor que veio da api.
  - As rotas privadas podém ser acessadas somente de tiver esse token
  - dentro das pastas ```users``` e ```processes```, vão ter as rotas que você irá utilizar.

##### Como rodar os testes
  - Entre na pasta processesapi
  - rode o comando mvn test


Obs: Três dias é pouco tempo para fazer todo o ambiente, comecei o teste na sexta as 18 e terminei agora, faltou adicionar uma configuração mais robusta no docker, onde tivesse os environments para cada caso, "dev", "test", "release" por exemplo, os testes da camada de aplicação acabou ficando de fora para que desse tempo de entregar algo usável.

##### Como eu construiria os projetos

No back-end, na porta 5000, criaria todos os environments, "dev", "test" e "release", organizaria a estrutura em DDD, services, MVC usando rest, flyway nos migrations, Basic Auth para authenticar a Api com outros serviços, utilizaria OAuth2 Bearer Token para authenticar o usuário deixando o usuário e o token salvos em uma thread no sistema, permitiria o acesso aos dominios de acordo com o type do usuário que estiver logado, liberar o cors somente para os domínios que são cadastrados, configuração essa que ficaria no arquivo environment e seria usado pelo WebConfig que implementa o WebMvcConfigurer, podendo. Separaria as authenticações em um arquivo para cada, com poucas ou somente uma responsabilidade cada arquivo. Por exemplo:
AuthWebService.java e AuthUserToken.java, WebConfig.java, PermissionMiddleware.java e  UserSession.java para os arquivos de configuração.

demoraria 40 horas, para construir todo o projeto, com os migrations rodando com flyway, dockerizar e implementar o circle ci, rodando o build e testes, além do versionamento de imagens no dockerHub, incrementaria mais 16 horas para implementar o deploy automatico para produção, se estiver usando uma VPS linux.

A organização das pastas ficaria assim:
```
src/
resources/
    environments/
        dev.properties
        test.properties
        release.properties
    main/
        application/
            controllers/
                public_controllers/ # A palava public é reservada.
                    AuthController.java
                private__controllers/
                    UserController.java
                    ProcessesController.java
        domain/
            user/
                models/
                    User.java
                admin/
                    services/
                triator/
                    services/
                finisher/
                    services/
        repository/
            UserRepository.java
            ProcessRepository.java
        configs/
            UserSession.java
            WebConfig.java
            AuthUserToken.java
            AuthWebService.java
            PermissionMiddleware.java
        Application.java
```

No banco, postgres na porta 5432 com o padrão
  - nome da tabela em snake_case
  - nome da coluna em camelCase

No front-end, na porta 80 servindo com o nginx, criaria todos os environments, "dev", "test" e "release", utilizaria o react mesmo, e contruíria num padrão com arquivos de configuração [singleton-pattern](https://nandovieira.com.br/design-patterns-no-javascript-singleton), para o axios, router, redux, etc...
Impletar o cypress para testes e2e e jest + sinon para testes unitários.
Implementaria um estrutura em camadas de permissões nas Views, o que facilita o roteamento pelas permissões de acesso do usuário, que ficariam salvas nos cookies, junto da authenticação, mostrando assim somente as telas que ele tem acesso de acordo com o type do usuário que está salvo nos cookies.

Demoraria 40 horas, para entregar todo o sistema nos 3 environments, dockerizado + docker-compose com a api e banco rodando e circle-ci para os testes e o build. Adicionaria mais 16 horas para o deploy automatico em produção, se estiver usando uma VPS Linux.

A organização das pastas ficaria assim:
```
envs/
    .env.dev
    .env.test
    .env.release
src/
    __tests__/
        e2e/
        unit/
    public/
        Login/
            index.js
            ...
            ...
    privated/
        Admin/
            Store/
            Users/
                index.js
                styles.js
                UserAdd/
                    index.js
                    ...
        Triator/
            Store/
            Processes/
                index.js
                ...
                FinishProcess/
                    index.js
                    ...
                    ...
    configs/
    services/
        showToastError.js
        showToastSuccess.js
        ...
```