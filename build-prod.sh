#!/bin/bash


## Build Back-End
sudo docker build -t softplan-back-end back-end/.

## Build Back-End
sudo docker build -t softplan-front-end front-end/.
