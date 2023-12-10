FROM gradle:8.3.0-jdk17 AS builder
WORKDIR /app

COPY settings.gradle.kts build.gradle.kts ./
COPY gradle ./gradle
COPY gradlew gradlew.bat ./
COPY src ./src
COPY buildSrc ./buildSrc
RUN gradle clean :bootJar --no-daemon

FROM openjdk:17-jdk-slim
COPY --from=builder /app/build/libs/*.jar /app/
WORKDIR /app
EXPOSE 8080
CMD ["/bin/sh", "-c", "java -jar *.jar"]
