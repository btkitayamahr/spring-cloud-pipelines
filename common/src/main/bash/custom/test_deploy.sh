#!/bin/bash

function testDeploy() {
    echo "I am executing a custom test deploy function"
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa ${USERNAME_TEST}@${HOSTNAME_TEST} sudo docker stop ${APP_NAME}
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa ${USERNAME_TEST}@${HOSTNAME_TEST} sudo docker rm ${APP_NAME}
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa ${USERNAME_TEST}@${HOSTNAME_TEST} sudo docker image prune -a -f
    scp -i ${JENKINS_HOME}/.ssh/id_rsa ${WORKSPACE}@config/config-repo/${APP_NAME}/docker/docker-compose-${APP_NAME}-test.yml ${USERNAME_TEST}@${HOSTNAME_TEST}:/home/centos
    ssh -i ${JENKINS_HOME}/.ssh/id_rsa ${USERNAME_TEST}@${HOSTNAME_TEST} sudo docker-compose -f docker-compose-${APP_NAME}-test.yml up -d
}
