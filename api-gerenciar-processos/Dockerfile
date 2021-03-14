FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api.jar
ENTRYPOINT ["java","-jar","/api.jar"]