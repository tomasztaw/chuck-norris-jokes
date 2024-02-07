FROM openjdk:17-jdk

WORKDIR /app

COPY target/chuck-norris-jokes-0.0.1-SNAPSHOT.jar /app/chuck.jar

EXPOSE 8080

CMD ["java", "-jar", "chuck.jar"]
