# Back-end Softplan Desafio Fullstack

Api da aplicação Softplan desafio

## Framework 

A aplicação back-end foi desenvolvida utilizando a linguagem **Java** e o framework Spring-Boot na versão 2.1.7.

## Facilitadores

Foram utilizadas algumas bibliotecas para facilitar a implementação, são elas:

 - **Lombok** Facilitando a limpesa das classes tirando a necessidade de se escrever `getters` e `setters`.
 - **QueryDLS** Facilitando a execução de consultas dinâmicas, essa bilioteca é poderosa, totalmente compatível com o Spring e JPA.
 - **Gson** Para realizar parse de obejtos JSON na aplicação.

## Autenticação e Autorização

Para realizar a autenticação e autorização na aplicação foi utilizado o padrão JWT. Para implementar esse padrão foi utilizada a biblioteca **jjwt**.
As permissões são carregadas e enviadas como resposta ao serviço de login e ficam armazenadas no acesso do usuário para utilização interna.
Nos serviços foi utilizada a notação `@PreAuthorize` para validar as permissões. 
 
## Estrutura de Dados

Foi pensado a seguinte estrutura de dados:

 - Papel 
 - Permissão
 - Permissão_Papel
 - Usuário
 - Processo
 - Parecer

Sendo um usuário possui apenas um papel.
Cada pepel possui uma relação de permissões.
Cada permissão da acesso a uma determinada função no sistema.
Um ou mais usuários podem conter registro de parecer.
Após obtiverem esse registro podem atualizar o mesmo que possui um campo chamado também de parecer.
A exclusão de usuário é feito logicamente, portanto há nele um campo de situação

## Banco de dados

Para realizar o controle do banco de dados foi utilizado a biblioteca **Liquibase**. 
Os arquivos de construção da estrutura de dados e carga inicial estão no diretŕoios `src/resources/db/changelog/scripts`. São eles:

- __schema.sql__: Constrói um schema de banco chamado **softplan**
- __papel-usuario-permissoes.sql__: Constrói as tabelas de papel, permissão, permissão_papel e usuário 
- __processo-andamento.sql__: Constrói as tabelas de processo e andamento 
- __carga-inicial.sq__:   Faz a carga inicial das permissões em cada pepel conforme a especificação, Incluí usuários de teste para acesso.


## Estutura de Classes 

As classes foram dividas por assunto em três pacotes principais

- __processo__: Todas as classes referentes ao processo inclusive do parecer do processo, uma vez que é registro derivado do mesmo.
- __security__: Todas as classes referentes ao usuário, papel,  permisssões e configurações específicas do spring.
- __utils__: Pacote com utilitários reutilizáveis dentro do projeto


## Mapemanto de serviços

Os serviços estão contidos nas classes `controller` e foram mapeados conforme padrão REST para fornecer ações e recusos a quem for consumir a API. São eles:

| __Serviço__ | __Método HTTP__ | __Parâmetros__ | __Descrição__ |
| --------- | ------------- | ------------- |  ------------- |
| /api/login | POST | email:String, senha:String | Realiza a autenticação do usuário |
| /api/papeis | GET | | Retorna os papéis do sistema |
| /api/usuarios?nome= | GET | | Retorna os usuários do sistema |
| /api/usuarios/:id | GET | | Retorna um usuário do sistema pelo id informado|
| /api/usuarios | POST | nome:String, email:String, papel:Objeto | Cria um usuário do sistema |
| /api/usuarios/:id | PUT | nome:String, email:String, papel:Objeto | Atualizado um usuário do sistema conforme o id |
| /api/usuarios/:id | DELETE | | Remove logicamente um usuário do sistema |
| /api/processos | GET | | Retorna os processo do sistema conforme acesso definido por regra |
| /api/processos | POST | numero:String, descricao:String, | Cria novo processo no sistema |
| /api/processos/:processoId/pareceres | GET | | Retorna os pareceres de um processo conforme o id informado |
| /api/processos/:processoId/pareceres/atual | GET | | Retorna o parecer atual do usuário logado no sistema |
| /api/processos/:processoId/pareceres/adicionarUsuario | POST | id:Inteiro | Cria um registro de parecer para que um usuário possa preenche-lo |
| /api/processos/:processoId/pareceres/incluirParecer | POST | parecer: String | Incluí parecer no registro de parecer do processo |