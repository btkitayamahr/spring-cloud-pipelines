#!/bin/bash

function testDeploy() {
    echo "I am executing a custom test deploy function"
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa centos@52.198.239.61 sudo docker stop ${APP_NAME}
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa centos@52.198.239.61 sudo docker rm ${APP_NAME}
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa centos@52.198.239.61 sudo docker image prune -a -f
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa centos@52.198.239.61 sudo docker-compose -f docker-compose-${APP_NAME}-test.yml up -d
    ls
}
