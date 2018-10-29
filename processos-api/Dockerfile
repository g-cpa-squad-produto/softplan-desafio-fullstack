FROM openjdk:8-jdk-slim
VOLUME /tmp
COPY target/processos-api-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]