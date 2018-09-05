#!/bin/bash

function testDeploy() {
    echo "I am executing a custom test deploy function"
    ssh -i .ssh/id_rsa centos@52.198.239.61
    ls
}
