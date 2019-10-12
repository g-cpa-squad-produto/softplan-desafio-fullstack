# Desafio implementador fullstack

### Desafio
- Desenvolver uma aplicação web responsável por gerenciar processos.
- *Soluções parcias serão aceitas.*
- Visão de administrador
	- Incluir, excluir, atualizar e visualizar usuários,
- Visão de usuário-triador
	- Incluir e visualizar processos,
	- Atribuir um ou mais usuários a realizar um parecer sobre um processo,
- Visão de usuário-finalizador
	- Visualizar processos pendentes de parecer.
	- Incluir o parecer sobre o processo.

# Desenvolvimento
### Bibliotecas Utilizadas
- Spring Boot 2.1.9
- Spring Data Jpa
- Spring Security
- Json Web Token (JWT)
- Swagger. Habilitado para acompanhamento e testes (/swagger-ui.html), mas as requisições precisam ser autenticadas, então deve-se adicionar o token.
- H2. H2 console habilitado para acompanhamento da avaliação. (/h2-console). Url do banco: jdbc:h2:mem:processmanagement
- slf4j para logs, a principio configurado a nível DEBUG para os controllers, repositories e services
- Obs: Foi utilizado o banco H2 por não haver a necessidade de uma permanencia constante dos dados. O driver do MySql está presendo no pom.xml, e as configurações no application.properties, o que tornaria fácil a mudança caso precisasse. 

### Funcionalidades implementadas
- Endpoints para cadastro de Usuários, Processos, Pareceres e para autenticação
- Os endpoints completos podem ser consultados via Swagger (ainda habilitado)
- Na inicialização da aplicação back-end é feita uma carga inicial de usuários, processos e opiniões, para facilitar análise e testes
- Existe apenas um usuário administrador
- No desenvolvimento foi optado por não permitir o cadastro de mais administradores, mas facilmente modificado
- Visando a simplicidade, neste desenvolvimento, foi optado por não utilizar roles para controle de autorização, mas sim um enum no cadastro do usuário
- Foi criado um Dockerfile, que está na raiz da aplicação back-end, que é chamado pelo docker-compose e inicia a aplicação como um todo (front e back-end)
- A aplicação pode ser iniciada através do jar (java -jar target/processmanagement-0.0.1-SNAPSHOT.jar), mas não comitado por questões de tamanho da pasta, ou utilizando o docker-compose


