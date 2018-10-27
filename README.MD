# GDP - Gerenciador de Processos

Este sistema tem como obejetivo a resolução da avaliação técnica do processo seletivo para o cargo de Desenvolvedor Full Stack na [Softplan](https://www.softplan.com.br/) [www.softplan.com.br]

Esta solução é composta pelos seguintes artefatos:

  - GDP-SERVER
  - GDP-UI
  
# GDP-SERVER
-   Aplicação JAVA que disponibiliza endpoints a serem consumidos através de requisições HTTP: POST, GET, PUT, DELETE, OPTIONS. 

    ### Tecnologias envolvidas
* [Java]  -- 1.8
* [Maven] --  3.5.3
* [Spring]  -- 2.0.6.RELEASE
* [H2] -- H2 Data Base

# GPD-UI
-   Aplicação cliente (SPA) Single Page Application, que realiza o consumo desses endpoints
    ### Tecnologias envolvidas
    [NODE] -- v8.10.0
    [NPM] -- 5.6.0
    [Angular] -- 6.0.3
    [TYPESCRIPT] -- 2.7.2
    [RxJS] -- 6.0.0

   [dill]: <https://github.com/yurekesley>
   
   [Java]: <https://www.java.com/pt_BR/>
   [Spring]: <https://spring.io/projects/spring-boot>
   [Maven]: <https://maven.apache.org/>
   [H2]: <http://www.h2database.com/html/main.html>
   
   [NODE]: <https://nodejs.org/en/>
   [NPM]: <https://www.npmjs.com/>
   [Angular]: <https://angular.io/>
   [TYPESCRIPT]: <https://www.typescriptlang.org/>
   [RxJS]: <http://reactivex.io/>
   
#   Instalação
Para realizar a instação da solução, será nescesário seguiur os seguintes passos via terminal.
*  GDP-SERVER
```sh
$ git clone -b feature/yure-kesley https://github.com/yurekesley/softplan-desafio-fullstack.git
```
```sh
$ cd GDP-SERVER/
```

Uma vez dentro da pasta, certifique-se de que a porta 8080 está liberada, pois a aplicação subirá um servidor nessa porta, e execute o comando:

```sh
$ java -jar gdp-01.jar
```

Um contexto será disponibilizado como a seguir.

 ```sh
http://localhost:8080/gdp/
```

*  GDP-UI

Volte para a raiz do clone e entre na pasta da aplicação cliente.

```sh
$ cd GDP-UI/
```

Dentro da pasta, baixe as dependências do projeto através do NPM.

```sh
$ npm i
```

Após todas as dependências instaladas, certifique-se que a porta 4200 está liberada e  suba a aplicação rodando o comando: 
```sh
$ npm run start
```

A aplicação será disponibilizada na url:

```sh
$ http://localhost:4200
```

#   Login na aplicação.

Para acessar o sistema, será solicitado um login e uma senha, selecione um perfil dentre os desritos na tabela de acessos.


| PERFIL            | LOGIN                     | SENHA  |
| ------            | ------                    |------  |
|ADMIN              |admin                      |123     |
|TRIADOR            |triador                    |123     |
|FINALIZADOR        |finalizador                |123     |



**Yure Kelsey**  : ***yurekesley@gmail.com***
https://www.linkedin.com/in/yurekesley/
https://github.com/yurekesley





    