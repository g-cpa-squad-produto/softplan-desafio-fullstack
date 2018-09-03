* Passos a serem seguidos.

- OBSERVAÇÕES IMPORTANTES

1. O sistema foi desenvolvido na IDE Eclipse (Versão NEON), utilizando JDK 1.7 e servidor de aplicação jBoss AS 7.1.1.Final.

2. Foi utilizado o Maven para gerenciar as dependências do projeto.

3. Se necessário, seguir primeiramente o documento fornecido "01_Instalacao_MySql.docx"
OBS: É necessário criar um novo schema no mysql com o nome "renancelso"
   
4. É necessário baixar o servidor de aplicação "jboss-as-7.1.1.Final". Link utilizado: "http://jbossas.jboss.org/downloads"

5. É necessário configurar um datasource para a base mysql. Pode-se utilizar o documento "02_Configuracao_jboss-as-7.1.1.Final.docx" fornecido para se ter uma base.
   OBS: Sugiro necessário que seja configurado uma conexão no servidor de aplicação conforme a seguir (standalone.xml):

		<datasource jta="false" jndi-name="java:/jdbc/renancelsoDS" pool-name="renancelsoDS" enabled="true" use-ccm="false">
			<connection-url>jdbc:mysql://localhost:3306/renancelso?autoReconnect=true&amp;amp;useSSL=false</connection-url>
			<driver-class>com.mysql.jdbc.Driver</driver-class>
			<driver>mysql-connector-java-5.1.18.jar</driver>
			<security>
				<user-name>root</user-name>
				<password>avebs4212</password>
			</security>
			<validation>
				<validate-on-match>false</validate-on-match>
				<background-validation>false</background-validation>
			</validation>
			<statement>
				<prepared-statement-cache-size>0</prepared-statement-cache-size>
				<share-prepared-statements>true</share-prepared-statements>
			</statement>
		</datasource>

6. Dentro da pasta TARGET, é encontrado o artefato "renancelso-web.war" da aplicação já pronta pra dar deploy no servidor de aplicação configurado. 

7. Caso o servidor esteja configurado e iniciado, basta colocar o arquivo dentro do diretório "jboss-as-7.1.1.Final\standalone\deployments"
  - Caso esteja utilizando a IDE Eclipse, pode-se fazer deploy através dela também.
  
  OBS: É possível configurar o jBoss AS 7.1.1.Final como serviço no windows seguindo instruções da comunidade no link: 
  "https://developer.jboss.org/wiki/InstalandoJBossAS711ComoServicoNoWindowsServer2008X64"

