FROM maven:3.6.0-jdk-11-slim AS build
COPY Backend/src /home/app/src
COPY Backend/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
VOLUME /tmp
COPY --from=build /home/app/target/*.jar /app.jar
HEALTHCHECK --interval=15m --timeout=10s --retries=3 --start-period=1m CMD curl --fail http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java","-jar","/app.jar"]
