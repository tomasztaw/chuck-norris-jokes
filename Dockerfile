FROM gradle:7.3.3-jdk17 AS build

WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN gradle build

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/build/libs/demo-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
