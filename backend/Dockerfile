FROM maven:3.6 AS builder

WORKDIR /app_build

COPY . .

RUN mvn package spring-boot:repackage

FROM openjdk:8

COPY --from=builder /app_build/target/prjdemo-0.0.1-SNAPSHOT.jar /opt/app.jar

RUN rm -f /etc/localtime

RUN ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime

ENTRYPOINT ["bash","-c","java -jar -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -Duser.timezone=America/Sao_Paulo /opt/app.jar"]

# docker build -t backend .
