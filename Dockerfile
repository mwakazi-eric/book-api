FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/bookstore-api-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
