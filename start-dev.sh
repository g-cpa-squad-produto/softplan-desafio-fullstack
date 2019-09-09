#!/bin/bash

## Start Database
sudo docker-compose -p softplan-deps -f docker-database.yml up -d

## Start Webpack
export SOFTPLAN_BACKEND_URL=http://localhost:8080/
cd front-end
npm run serve
