#!/bin/bash
./mvnw clean package -DskipTests 
docker build -f Dockerfile -t softplan/processos-api .
docker run -d --rm --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=processosdb" mysql:5.6
docker run -it --rm --name processos-api --link docker-mysql:mysql -p 8080:8080 -e DATABASE_HOST=docker-mysql -e DATABASE_PORT=3306 -e DATABASE_NAME=processosdb -e DATABASE_USER=root -e DATABASE_PASSWORD=root softplan/processos-api