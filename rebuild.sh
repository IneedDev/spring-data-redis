#!/bin/bash

APP_NAME="spring-data-redis"

export CONTAINER_NAME="$APP_NAME-container"
JAR="$APP_NAME.jar"
export DOCKER_IMAGE="profiling/$APP_NAME"
export DOCKER_IMAGE_VERSION="latest"
src
function removeContainer() {
  CONTAINER=$1
  RUNNING=$(docker inspect -f {{.State.Running}} $CONTAINER 2>/dev/null)
  if [ "$RUNNING" == "true" ]; then
    echo "Stopping $CONTAINER" &&
      docker stop "$CONTAINER"
  fi
  echo "Removing $CONTAINER if exists" &&
    docker rm "$CONTAINER" 2>/dev/null
}

removeContainer $CONTAINER_NAME

echo "Rebuilding $JAR" &&
  sh ./mvn.sh clean package &&
  echo "Copying archive to docker directory" &&
  mkdir -p docker/temp &&
  cp target/$JAR docker/temp/ &&
  echo "Rebuilding docker image" &&
  cd docker &&
  docker build --rm -t $DOCKER_IMAGE:$DOCKER_IMAGE_VERSION . &&
  rm -fr temp &&
  rm -fr temp &&
  echo "Running docker" &&
  docker-compose up
