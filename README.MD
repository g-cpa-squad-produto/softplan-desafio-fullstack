O backend foi desenvolvido utilizando a IDE SpringToolSuite 4, acredito que será necessário a importação do projeto e inicialização pelo springboot;
Antes de iniciar será necessario criar q base com o nome "db_desafio_softplan" no PostgreSQL, o projeto está configurado para criação das tabelas;

Para a autenticacao no backend é somente necessario login e senha;
Foi implementado filtro de autenticacao e CORS
A geração do token de autenticacao pode ser realizada pelo POSTMAN;

O frontend foi desenvolvido(não está totalmente funcional) em Angular com Bootstrap;
O token gerado no backend foi inserido no service dos processos para realizar a comunicação e autenticação com backend;

