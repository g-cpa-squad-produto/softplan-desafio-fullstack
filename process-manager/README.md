### Backend ###

This is an API to perform process management, I developed this API using Intellij IDE and these technologies:

Springboot;
Gradle;
Oracle Database;
H2 (memory database);
Swagger;
Lombok;
Actuator;
Spring rest.

This API allow these operations:


Create a user: http://localhost:8090/process-management/user/create
List all users: http://localhost:8090/process-management/user/users
Get an user by user name: http://localhost:8090/process-management/user/user
Edit an user: http://localhost:8090/process-management/user/editUser

Create a process: http://localhost:8090/process-management/process/create
Finish a process: http://localhost:8090/process-management/process/finish
Find all process: http://localhost:8090/process-management/process/findAll
Find by process by number: http://localhost:8090/process-management/process/findByProcessNumber
Include process opinion: http://localhost:8090/process-management/process/includeProcessOpinion
Authorize an user to management process: http://localhost:8090/process-management/process/authorizeUserToManageProcess
Unauthorize an user to management process: http://localhost:8090/process-management/process/unauthorizeUserToManageProcess


You can check the all rest operations and the payloads through Swagger, to do it you need to running the application and access this url:http://localhost:8090/process-management/swagger-ui.html

This project have these kind of tests:

Integration Tests (48 tests): ProcessManageApiIntegrationTest class;
Unity Tests (32 tests) for all services classes.

The integrantion tests are using memory database (H2) and create the data, you can verify it on seed.sql and purge.sql files.

Note: You can run the tests through Intellij IDE or call direct in Gradle (execute the command "gradlew clean test" in cmd prompt)

Step to running the project:

Intellij IDE:
    Import the project like Gradlew Project;
    Mark the check box Enable Annotation Processing in Settings->Build->Execution->Deployment->Compiler->Annotation Processor
    Run the major class ProcessManagerApiApplication.
	
	*Detail: if you want to use Oracle database connection you need to configure the connection on application-prod.properties and use this profile or if you want just use memory database (H2) you just need to use the default profile.

Without an IDE:
    Execute the command "gradlew clean build" inside the project in a cmd prompt;
    Execute command "java -jar process-manager-api.jar" in process-manager-api\build\libs" directory.

In both of cases you can check if the application is running accessing this url http://localhost:8090/process-management/actuator/health (actuator feature);

Important Note: This project can be configured to create the database tables structure every time that the application is started, if you want to do it, you just need to uncomment (#) this line "spring.jpa.hibernate.ddl-auto=update" on application.properties.

Also, you can generate a docker image using the buildDocker task on Gradle (important you need to run it on a machine that has docker installed), you can see the configuration used to generate this image on dockerFile in /process-management-api/


### Frontend ###

In development process


If you have questions, please feel free to contact me.
