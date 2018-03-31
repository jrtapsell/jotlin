#!/bin/bash
./gradlew shadowJar &&
./gradlew test &&
docker build -t jotlin . &&
./runTests.py &&
echo "Checks passed"
