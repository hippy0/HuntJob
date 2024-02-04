FROM gradle:8.5.0-jdk21
LABEL authors="hippy"

COPY gradle gradle
COPY settings.gradle.kts .
COPY app/build.gradle.kts app/
COPY gradlew .

RUN ./gradlew --no-daemon dependencies

COPY app/src app/src

RUN ./gradlew --no-daemon build

ENV JAVA_OPTS "-Xmx512M -Xms512M"
EXPOSE 7070

CMD java -jar "./app/build/libs/app-1.0.1-RELEASE.jar"