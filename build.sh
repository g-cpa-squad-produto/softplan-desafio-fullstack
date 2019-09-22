#!/bin/bash

echo "[BACKEND] Construindo backend"
cd ./processos-api
mvn clean install
sudo docker build -t adolfo-hengling/processos-api:1.0.0 .
echo "[BACKEND] construção backend finalizada com sucesso."

echo "[FRONTEND] Construindo fronted"
cd ../processos-app
npm install
npm run build
sudo docker build -t adolfo-hengling/processos-app:1.0.0 .
echo "[FRONTEND] construção frontend finalizada com sucesso."