FROM maven:3.8.8-eclipse-temurin-21-alpine AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk

COPY --from=build /target/chuck-norris-jokes-0.0.1-SNAPSHOT.jar.original chuck-norris-jokes.jar

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "chuck-norris-jokes.jar"]
