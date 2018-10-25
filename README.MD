<h2>Para rodar a aplicação:</h2>

<strong>Back-end:</strong>
Pressupondo que já tenha o Java instalado e o Maven configurado na maquina:
Vá para a pasta back e execute o comando: mvn package && java -jar target/gerenciador-de-processo-1.0.jar
<br>
<strong>Front-end:</strong>
Pressupondo que tenha o npm e o angular configurado na maquina:
Vá para a pasta front e execute o seguinte comando:
npm i && ng serve   


<h2>Ciclo de vidas: </h2>
<strong>Administrador:</strong> Administrador só tem permissão de cadastrar, editar e excluir usuários. Ele poderá fazer isso logo após efetuar o login. Só não pode excluir ele mesmo.

<strong>Usuário triador:</strong> Triador  este usuário pode, criar um processo, atribuir usuários ao processo, visualizar os 	pareceres dos processos e se todos os usuários do processos tiverem dado o parecer, pode concluir o processo

<strong>Usuário Finalizador:</strong> Esse usuário pode visualizar processos sem parecer e incluir um parecer. 
<br>
<strong>Os casos de uso estão em um arquivo separado na raiz do projeto</strong>
<h2>Exemplo funcional do projeto</h2>
https://gerenciador-app.herokuapp.com/login <br>
Pode haver uma demora pois está no herokuapp e o projeto inicia assim que acessa o link. <br>
Qualquer duvida estou a disposição.<br>
Meu email: emanuellima3105@gmail.com
