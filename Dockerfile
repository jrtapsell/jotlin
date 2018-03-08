FROM gradle:alpine
USER root
ADD . ./

RUN gradle jar

FROM openjdk

WORKDIR /app

COPY --from=0 /home/gradle/build/libs/* app.jar
COPY runner/* ./
RUN chmod +x run.sh
RUN apt-get update && apt-get install zip
RUN curl -s "https://get.sdkman.io" | bash
RUN ["/bin/bash", "-c", "source /root/.sdkman/bin/sdkman-init.sh;sdk install kotlin"]

ENTRYPOINT ["/app/run.sh"]
