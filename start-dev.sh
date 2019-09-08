#!/bin/bash

## Start Database
docker-compose -p softplan-deps -f docker-dependencies.yml up -d

## Start Webpack
cd front-end
npm run serve
