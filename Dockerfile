ARG JVM=21

FROM eclipse-temurin:${JVM}-jre-jammy
ARG APP_NAME=chatgpt
ARG TMP=/tmp/
ARG CGPT_JAR=${TMP}${APP_NAME}.jar

ENV OPENAI_DEMO_API_KEY ${OPENAI_DEMO_API_KEY}

WORKDIR ${TMP}
COPY target/${APP_NAME}-1.0-SNAPSHOT.jar ${CGPT_JAR}

EXPOSE 8080

ENTRYPOINT ["java"]

CMD ["-jar", "chatgpt.jar"]