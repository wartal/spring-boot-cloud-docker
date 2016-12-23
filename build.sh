#!/bin/bash

cd config-server/ && ./mvnw clean package && cd .. &&
cd twitter-service/ && ./mvnw clean package && cd .. &&
cd webapp/ && ./mvnw clean package && cd .. &&
docker-compose build
