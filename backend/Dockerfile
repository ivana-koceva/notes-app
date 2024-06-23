# Example Dockerfile snippet
FROM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /workdir/server
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /workdir/server/src
RUN mvn package -DskipTests  # Only package the application, skip tests during build

# Use a lighter base image for runtime
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=builder /workdir/server/target/NotesApplication-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "NotesApplication-0.0.1-SNAPSHOT.jar"]
