#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY target/*.jar app.jar

# Instal·la strace
#RUN apt-get update && apt-get install -y strace && apt-get clean

#EXPOSE 8080

# Utilitza strace per monitoritzar l'execució de l'aplicació
#ENTRYPOINT ["strace", "-f", "java", "-jar", "app.jar"]


# Build stage
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
