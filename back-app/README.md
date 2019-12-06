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


### Escopo do desafio
- Documentar todas suposições realizadas.
- Desenvolver os módulos de frontend e backend de forma separada.
- O desenvolvimento do backend deve ser feito em Java.
- O desenvolvimento do frontend pode utilizar JavaScript e qualquer framework ou ferramenta que suportem ou utilizem estas tecnologias.
- Preferencialmente utilizar Spring Boot 1.5+ com toda sua stack para o desenvolvimento do backend.
- Preferencialmente utilizar React para o desenvolvimento do frontend.
- É aceitável utilizar algumas respostas estáticas em determinadas porções da aplicação.
- Não é necessário submeter uma aplicação que cumpra cada um dos requisitos descritos, mas o que for submetido deve funcionar.

# Desenvolvimento
### Bibliotecas Utilizadas
- Spring Boot 2.2.1
- Spring Data Jpa
- Lombok
 - Obs: É necessário configurar o projeto Lombok  na IDE para que o projeto funcione corretamente, senão o código apresentará problemas de compilação quando se tentar usar algum método getter ou setter (por exemplo). Caso esteja usando o Eclipse siga os passos descritos no link (https://projectlombok.org/setup/eclipse) e caso esteja usando IntelliJ IDEA instale o plugin descrito no link (https://plugins.jetbrains.com/plugin/6317-lombok-plugin). No link do projeto (https://projectlombok.org/) também pode encontrar os passos para outras IDES e editores ou se preferir, sugiro comentar as anotações : @AllArgsConstructor, @NoArgsConstructor, @Data das classes de entidade e do DTO e gerar seus respectivos getter ou setter.
- H2
	- Obs: Foi utilizado o banco H2 por não haver a necessidade de uma permanencia constante dos dados.


### Funcionalidades implementadas
- Endpoints para o CRUD  Usuários; criar, obter e remover Processos; criar Pareceres

### Recursos
- Para permitir que o a aplicação do frontend acessasse a aplicação do backend, foi necessário desbloquear o CORS utilizando a classe  CorsConfiguration no pacote configuration da aplicação java. Além disso, foi necessário informar a url que está rodando a aplicação de frontend no método: .allowedOrigins("http://localhost:3000")


