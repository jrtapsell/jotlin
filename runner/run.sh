#!/bin/bash

cd /app

cat header.kt > script.kts
cat - >> script.kts

source /root/.sdkman/bin/sdkman-init.sh

kotlinc -cp app.jar -include-runtime -jvm-target 1.8 -script script.kts -nowarn