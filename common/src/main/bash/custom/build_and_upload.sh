#!/bin/bash

ls
mvn clean install -f ./contents/pom.xml
docker image prune -a -f