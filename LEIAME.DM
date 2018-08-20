Segue os passos de como deve ser executado o projeto

SENHA DO USUÁRIO É : processos

1) O sistema esta configuerado para rodar com portgres
2) Para configura banco de dados Abra DataConfiguration.java
3) Deve ser criado antes de executar o sistema uma base o postgres com nome "processos", se quiser alterar este nome deve ser alterado também no DataConfiguration.java
4) Rodar o arquivo setup.sql no banco de dados criado está no diretório SETUP raiz.
5) Para fazer os testes será necessário usar o postman ou algo semelhante, visto que não consegui fazer o frontend neste final de semana
6) Acessar o diretório e rodar o projeto poder ser com Ex: "mvn spring-boot:run".
7) Para os serviços:
- Visão de usuário-triador;
- Visão de usuário-finalizador
  Deve ser passado o login do usuário que está executando visto que não tem ainda usuário autenticado.
8) Pode ser visto os serviços disponíveis em "http://localhost:8080/swagger-ui.html"
9) Bom foi o que consegui fazer aguardo parecer obrigado.
