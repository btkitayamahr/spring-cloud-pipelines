#!/bin/bash

ls
mvn clean install -f ./${APP_NAME}/pom.xml
docker image prune -a -f