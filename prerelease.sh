#!/bin/bash
./gradlew test &&
docker build -t jotlin . &&
./runTests.py &&
echo "Checks passed"
