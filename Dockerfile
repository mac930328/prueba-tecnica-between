# Multi-stage Dockerfile for building and running the Spring Boot application

# Build stage - uses Maven with JDK 21 to build the project
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /workspace
COPY pom.xml .
# copy source
COPY src ./src
# run mvn package (skip tests to speed up image build, tests/jacoco are handled separately by compose services)
RUN mvn -DskipTests package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
VOLUME /tmp
ARG JAR_FILE=/workspace/target/*.jar
COPY --from=builder /workspace/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
