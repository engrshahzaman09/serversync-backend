FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/serversync-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]a", "-jar", "app.jar"]