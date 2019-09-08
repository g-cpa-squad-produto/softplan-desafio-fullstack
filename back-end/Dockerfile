FROM maven:3.6.0-jdk-8

RUN mkdir /opt/app
RUN mkdir /opt/app/src

COPY start-java /usr/local/bin/

RUN chmod a+x /usr/local/bin/start-java

COPY pom.xml /opt/app
COPY src/ /opt/app/src/

RUN mvn clean install -f /opt/app/pom.xml

EXPOSE 8080

ENTRYPOINT ["start-java"]







