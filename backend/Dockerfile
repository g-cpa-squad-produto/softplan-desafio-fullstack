# Step : Package
FROM maven:3.6.3-jdk-11 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package -DskipTests

# Step : Package image
FROM openjdk:11-jdk-slim
ENV JAVA_OPTS='-Xmx256mb' PORT=8080
COPY --from=build /build/target/*.jar /app/app.jar
CMD exec java -jar /app/app.jar --server.port=$PORT