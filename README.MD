## Tecnologias utilizadas
-- No backend foram seguidas as orientações, utilizado Spring Boot 2.1.5 e JDK 11, para o banco de dados foi utilizado o H2(em memória) e para fins de validação da autenticação foi utilizado JWT pela biblioteca JJWT.

-- No frontend foi utilizado React, conforme solicitado, infelismente a única funcionalidade que foi parcialmente concluída no front é a autenticação.

## Execução do docker
Utilize o seguinte commando para iniciar os containers:<br/>
sudo docker-compose up --build

-- usuário: admin
-- senha: password

O único endpoint implementado é 'http://localhost:8080/api/users/all'

## Considerações
Devido a falta de conhecimento nas tecnologias Spring e React, acabei ficando preso a pequenas peculiaridades que, acabaram por consumir todo o meu tempo de desenvolvimento, apesar de tudo isso, consido essa jornada como concluída, foi uma semana de muito aprendizado e de ótimas lições.

Obrigado.