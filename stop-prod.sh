#!/bin/bash

## Stop Projects
sudo docker-compose -p softplan-projects -f docker-projects.yml down --volumes

## Stop
sudo docker-compose -p softplan-deps -f docker-database.yml down --volumes
