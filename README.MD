### Instruções para rodar o projeto

Obs: Tentei deixar o projeto do front-end rodando junto com o servidor do SpringBoot mas não 
consegui fazer o mesmo endpoint redirecionar para o front.

- mvn clean install `vai demorar porque instala as dependencias do NPM`
- cd backend `<- 1* server`
- mvn spring-boot:run -Dspring.profiles.active=prd
- cd ..
- cd frontend
- npm start `<- 2* server`


# Estrutura de modelagem
**Usuario**
- Nome
- Perfil ('ADM', 'TRI', 'FIN') // Administrador, Triador, Finalizador

**Processo**
- numero
- parecer
- status ('PDT', 'FNL')
- List<Usuarios>

**processo_usuario**
- Para o relacionamento das tabelas.

### Pontos de destaque
- Realizei a cobertura quase 100% dos testes da aplicação, apenas faltando validação de models e classes
sem regra de negócio.
- Foi muito legal realizar o desafio pois acabei de fazer um curso de React básico e já coloquei em prática e tive diversas dúvidas que com certeza vão me impulsionar nos estudos.

### Desafio encontrados

- Realizar a alteração do usuário/processo, pois com meu conhecimento de React não consegui 
fazer com que os Forms recebessem a informação do Table.
- Rodar os dois projetos em apensa um servidor, pois não redirecionava a url para o frontend. 
Provavelmente algum problema nas configurações do maven e/ou bean.
- Fazer o redirect corretamente quando faz o login, o menu não atualiza. Tem que dar refresh na página
e aí ele atualiza. =(
- Não fiz a exclusão do usuário no front, foquei tempo demais na solução da edição do 
processo que acabei esquecendo de fazer a simples chamada para a exclusao do usuário e atualização do grid.