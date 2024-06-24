# syntax=docker/dockerfile:1.4

FROM maven:3.8.5-eclipse-temurin-17
EXPOSE 8080
ARG JAR_FILE=target/NotesApplication-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]