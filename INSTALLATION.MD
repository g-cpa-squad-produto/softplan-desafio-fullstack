# Instalação para rodar o teste

## Pré-requisito

- GIT
- Docker
- docker-compose
- Java 11
- MongoDB

## Instalação

1. docker build -t api-docker-image .
2. docker run -d -p 9090:8080 api-docker-image
3. docker-compose up

## Instalação automatica

1. sudo chmod +x setup.sh
2. ./setup.sh

## Comandos

mongod --dbpath {path}/db
mongo mongodb://127.0.0.1:27017

## Documentos

1. desafio-fullstack.postman_collection.json
  Documento com as chamdas feitas na API rest.

2. setup.sh
  Arquivo executavel que faz geração do Dockerfile e executa o docker-compose


