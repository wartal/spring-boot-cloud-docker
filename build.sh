#!/bin/bash

mvn -f config-server/pom.xml clean package &&
mvn -f twitter-service/pom.xml clean package &&
mvn -f webapp/pom.xml clean package &&
docker-compose build
