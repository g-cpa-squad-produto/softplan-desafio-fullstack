#!/bin/bash

## Start Database
docker-compose -p softplan-deps -f docker-dependencies.yml up -d

## Start Webpack
export SOFTPLAN_BACKEND_URL=http://localhost:8080/
cd front-end
npm run serve
