FROM openjdk:8

EXPOSE 8080

COPY build/libs/. /usr/src/psmobile-auth-ws

WORKDIR /usr/src/psmobile-auth-ws

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","usuario-service-1.0-SNAPSHOT.jar"]

CMD ["java", "Main"]
