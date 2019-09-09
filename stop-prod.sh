#!/bin/bash

## Stop Projects
docker-compose -p softplan-projects -f docker-projects.yml down --volumes

## Stop
docker-compose -p softplan-deps -f docker-dependencies.yml down --volumes
