#!/bin/bash

docker network ls|grep softplan > /dev/null || docker network create --driver bridge softplan

## Start Deps
docker-compose -p softplan-deps -f docker-dependencies.yml up -d

## Start Projects
docker-compose -p softplan-projects -f docker-projects.yml up -d
