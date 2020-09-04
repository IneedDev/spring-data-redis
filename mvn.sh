#!/bin/bash

#   Be sure to add a line: alias mvn="~/SZOP-mvn/mvn.sh" to .bashrc.
#   Then command "mvn" run maven from container instead of local installed maven.
    if [ -z "$CUSTOM_MVN_PWD" ]; then
        CUSTOM_MVN_PWD=$PWD
    fi
    IMAGE_NAME="docker.easypack24.net:5000/inpost/maven-jdk11:3.0.5-10"


    if [ -z "$MVN_OPTIONS" ]; then
        MVN_OPTIONS="-Xmx2560m -client -XX:+TieredCompilation -XX:TieredStopAtLevel=1"
    fi

    echo "[docker maven-container started]"
    echo "directory with pom: $CUSTOM_MVN_PWD"

    docker run -it --rm \
    --env MAVEN_OPTS="$MVN_OPTIONS" \
    --env TZ="Europe/Warsaw" \
    --volume "$CUSTOM_MVN_PWD":/tmp/build \
    --volume "$HOME/.m2":/root/.m2 \
    --net="host" \
    --workdir /tmp/build "$IMAGE_NAME" \
    mvn "$@"

    docker run -it --rm \
    --volume "$CUSTOM_MVN_PWD":/tmp/build \
    --workdir /tmp/build "$IMAGE_NAME" \
    find . -name "target" -type d -exec chmod -f -R 777 {} \;

    echo "[docker maven-container removed]"