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
### Estrutura
- Pasta front-app
	- Aplicação desenvolvida com react que acessa recursos do back-end
- Pasta back-app
	- Api desenvolvida utilizando Spring Boot 2.2.1 e outras bibliotecas informadas no README da pasta back-end
	- Para facilitar o levantamento da aplicação, foi utilizado o banco de dados H2 que salva os dados em memória
	- Para acessar a área de login é necessário utilizar o seguinte insert: insert into usuario values (1,'admin@email.com', 'administrador','admin','administrador');


