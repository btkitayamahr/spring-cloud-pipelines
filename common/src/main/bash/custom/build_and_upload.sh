#!/bin/bash

function build() {
    echo "I am executing a custom build function"
    mvn clean install
    docker image prune -a -f
}
