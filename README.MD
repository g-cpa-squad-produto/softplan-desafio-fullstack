# Teste softplan-desafio-fullstack
[![Sofplan](https://www.softplan.com.br/wp-content/themes/softplan-2019/imagens/logo-softplan-blue.svg)](https://www.softplan.com.br/en/solucoes/)

##### Como rodar o projeto
  - Faça o clone do repositório
- Usando Docker
  - Libere as portas 8080, 5432 e 3000 do seu sistema.
  - rode o commando ```docker-compose up --build``` na pasta raiz do projeto
  - pronto!
- Usando apenas o Maven + Postgres + yarn ou npm
  - Abra o arquivo ```src/main/resources/application.properties```
  - Coloque as suas configurações no dataSource, 
  - Trocar o host ```processes-database``` para ```localhost``` ou outro de sua preferência.
  - entre na pasta processesapi
  - rode o comando ```mvn spring-boot:run``` 
  - Abra a pasta front-end e rode o comando yarn start ou npm run start
 
- Configuração do postman
  - Abra a pasta raíz do projeto e importe o arquivo ```processes.postman_collection.json``` no cli do postman.

- Procedimento para testar front-end
  - acesse localhost:3000
  - Registre-se e faça o login
  - Cadastre um usuário triador e um usuário finalizador
  - Deslogue clicando no botão do lado direito superior da tela
  - Logue com o usuário triador que você criou
  - Crie um processo
  - Deslogue novamente
  - Logue com o usuário finalizador.
  - Finalize o processo

- Procedimento para testar end-points no Postman
  - abra a pasta public e altere o body para colocar seus dados
  - após um registro bem sucedido, a response virá com um header ```Authorization``` crie um header Authorization em seu request e adicione o valor que veio da api.
  - As rotas privadas podém ser acessadas somente de tiver esse token
  - dentro das pastas ```users``` e ```processes```, vão ter as rotas que você irá utilizar.

##### Como rodar os testes
  - Entre na pasta processesapi
  - rode o comando mvn test


Obs: 6 dias é pouco tempo para fazer todo o ambiente, comecei o teste na sexta 03/04/2020 as 18 e terminei Quinta 09/03/2020 as 03:48, faltou adicionar uma configuração mais robusta no docker, onde tivesse os environments para cada caso, "dev", "test", "release" por exemplo, os testes da camada de aplicação acabou ficando de fora para que desse tempo de entregar algo usável. O end-point ```finish``` ficou faltando, precisaria resolver o problema de bidirecionamento com recursividade infinita sem usar a lib json do jackson, faria isso criando as DTO's e fazendo um Map da model para a DTO, quando navegace entre as camadas appliacation e domain do projeto.
Essa foi a primeira vez que utilizei o docker-compose e o react, então algumas coisas não ficaram muito bem estruturadas.

Ao todo trabalhei por volta de 56h nesse teste, portanto algumas coisas realmente ficaram faltando, testes e2e e unit no front-end assim como os testes da camada de aplicação, verificando os throws, chamadas de serviços etc... Não faço isso no dia a dia, porém como se trata de um teste, e eu estou desesperado pra conseguir um emprego, preferi entregar algo usavel demonstrando um pouco de minhas habilidades, porém logo abaixo eu defino como eu construíria esse projeto, e o tempo que eu demoraria para entrega-lo.

Somente lembrando, que sempre trabalhei com metas de test-coverage acima de 90%, tanto no front como no back.

##### Como eu construiria os projetos

No back-end, na porta 5000, criaria todos os environments, "dev", "test" e "release", Swagger, organizaria a estrutura em DDD, services, MVC usando rest, flyway nos migrations, Basic Auth para authenticar a Api com outros serviços, utilizaria OAuth2 Bearer Token para authenticar o usuário deixando o usuário e o token salvos em uma thread no sistema, permitiria o acesso aos dominios de acordo com o type do usuário que estiver logado, liberar o cors somente para os domínios que são cadastrados, configuração essa que ficaria no arquivo environment e seria usado pelo WebConfig que implementa o WebMvcConfigurer, podendo. Separaria as authenticações em um arquivo para cada, com poucas ou somente uma responsabilidade cada arquivo. Por exemplo:
AuthWebService.java e AuthUserToken.java, WebConfig.java, PermissionMiddleware.java e  UserSession.java para os arquivos de configuração.

demoraria 48 horas, para construir todo o projeto, com os migrations rodando com flyway, Swagger para testar os end-points, dockerizar e implementar o circle ci, rodando o build e testes, além do versionamento de imagens no dockerHub, incrementaria mais 16 horas para implementar o deploy automatico para produção, se estiver usando uma VPS linux.

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
            DTOS/
                UserDTO.java
                ProcessDTO.java
                ...
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