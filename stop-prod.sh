#!/bin/bash

## Start Projects
docker-compose -p softplan-projects -f docker-projects.yml down

## Start Deps
docker-compose -p softplan-deps -f docker-dependencies.yml down
