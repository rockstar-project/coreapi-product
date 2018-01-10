#!/bin/bash
set +e

cd $APP_HOME/coreapi-product
docker-compose down

docker rmi $DOCKER_NAMESPACE/coreapi-product
mvn clean package -Dmaven.test.skip=true

docker login -u="$DOCKER_USER" -p="$DOCKER_PASSWORD"
docker-compose up -d
docker-compose logs --follow productapi

set -e
