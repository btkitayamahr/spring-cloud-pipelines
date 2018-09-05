#!/bin/bash

mvn clean install -f ./${APP_NAME}/pom.xml
docker image prune -a -f