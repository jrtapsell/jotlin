#!/bin/bash

cd /app

cat header.kt > script.kts
cat - >> script.kts

source /root/.sdkman/bin/sdkman-init.sh

kotlinc -script script.kts -cp . -include-runtime -d app.jar