# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-jammy

EXPOSE 8080

COPY ./build/libs/taxcalculation-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "taxcalculation-0.0.1-SNAPSHOT.jar"]
