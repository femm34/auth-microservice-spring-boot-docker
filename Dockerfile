FROM ubuntu:latest
FROM openjdk:23-jdk-slim
LABEL authors="fecd"

EXPOSE 8080

WORKDIR /app

COPY ./pom.xml /app

COPY .mvn /app/.mvn
COPY mvnw /app

RUN ./mvnw dependency:go-offline

COPY ./src /app/src

RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java", "-jar", "/app/target/SpringDocker-0.0.1-SNAPSHOT.jar"]

ENTRYPOINT ["top", "-b"]