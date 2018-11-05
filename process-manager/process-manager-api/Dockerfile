FROM openjdk:8u171-jre-alpine
LABEL maintainer="Rafael (Otto) <rafael.whatsthestory@gmail.com>"

ENV JAVA_OPTS=""

WORKDIR /app
EXPOSE 8090

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -jar process-management-api.jar" ]

ADD build/libs/process-management-api.jar .
