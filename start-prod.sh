#!/bin/bash

sudo docker network ls|grep softplan > /dev/null || sudo docker network create --driver bridge softplan

## Start Deps
sudo docker-compose -p softplan-deps -f docker-database.yml up -d

## Start Projects
sudo docker-compose -p softplan-projects -f docker-projects.yml up -d
