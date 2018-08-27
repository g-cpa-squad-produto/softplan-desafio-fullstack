FROM openjdk:8-alpine
MAINTAINER Danilo Paixao
RUN apk update && apk add bash
RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
COPY server/target/sistemaprocesso-api-0.0.1-SNAPSHOT.jar $PROJECT_HOME/sistemaprocesso-api.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./sistemaprocesso-api.jar"]