# Desafio implementador fullstack

### Tecnologias
#### Backend
- Java 8
- Spring Boot 2
- Banco de Dados H2
- Lombok (Para geração de métodos get/set, construtores, toString, etc)
- MapStruct (Para realizar a conversão de entidades em DTOs e vice-versa)
- Maven (Gerenciador de dependências)

#### Frontend
- Angular 6
- Bootstrap 3
- Npm (Gerenciador de dependências)
- Angular CLI (Para criação dos módulos, componentes e etc, compilação, empacotamento e execução do projeto)

## Desafio
#### Perfis de Acesso
Fiquei com algumas dúvidas sobre os perfis de acesso então posso ter fugido um pouco do esperado. O Administrador por exemplo fala que pode manter usuário, mas não fala nada sobre as operações em processos, mas eu deixei esse perfil com acesso de leitura aos processos, mas ele não pode atribuir **usuário-finalizador** aos processos, essa função ficou exclusiva do perfil **usuário-triador**, assim como a criação dos processos. E o único perfil que é permitido realizar um parecer é o de **usuário-finalizador**

#### Segurança (Autenticação e Autorização)
- No backend foi utilizado Spring Security com autenticação no banco de dados H2 (em memória) para realizar a segurança dos serviços REST
- No frontend foi criado um interceptador para que em todas as requisições HTTP fosse adicionado de forma automática as credenciais do usuário logado, utilizando o protocolo **Authentication Basic**.
- Foi necessário configurar o CORS no projeto de backend para que a aplicação (Angular) fosse capaz de realizar as requisições HTTP por se tratar de requisições entre diferentes hosts. Então para facilitar o desenvolvimento foi habilitado requisições de forma "permit all".
- A forma de autentição na aplicação web (Angular) não está solicitando login e senha, é necessário apenas selecionar o usuário em uma combo e clicar no botão logar, foi tomada essa decisão apenas para facilitar e adiantar o processo de desenolvimento. Porém é importante lembrar que no backend as credenciais são utilizadas e validadas.

## Rodando o projeto
#### Backend
Para rodar o projeto basta executar o seguinte comando na raíz do projeto **sisprocessos-service**:

*$ mvn spring-boot:run*

**Obs.: para executar o comando acima o Maven (mvn) precisa estar configurado nas variáveis de ambiente do SO**

#### Frontend
Como foi utilizado o Angular 6 no frontend tudo que precisa ser feito para rodar o projeto em modo desenvolvimento é executar o seguinte comando na raíz do projeto **sisprocessos-web**:

*$ ng serve*

**Obs.: para executar o comando acima o Angular CLI (ng) precisa estar instalado e configurado nas variáveis de ambiente do SO. A forma mais facil de fazer isso é utilizando o 'npm': npm i -g @angular/cli**

Agora basta acessar a URL: http://localhost:4200

## Considerações Finais
- Gostaria de agradecer pela oportunidade de participar do processo seletivo, mesmo eu não tendo muito tempo atualmente eu fiz o que consegui com o tempo que tinha, ao todo devo ter dedicado de 14h à 16h nesse projeto entre as várias pausas e interrupções
- Infelizmente não deixei o projeto 100% funcional em dispositivos móveis, e nem criei um Dockerfile para o projeto. Como eu falei estou sem tempo atualmente por conta do trabalho e então resolvi submeter para a avaliação dessa forma mesmo, mas se quiserem me dar a chance de fazer ambas as atividades eu ficaria satisfeito, porém creio que só terei tempo no próximo fim de semana
- A mesma justificativa do item acima se aplica para os testes e documentação, esse último eu até estava fazendo das classes abstratas e interfaces (parte arquitetural do projeto) logo no começo do projeto, mas depois resolvi dedicar o tempo que tinha em implementar de fato a solução.
