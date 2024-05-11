
FROM openjdk:17-jdk-slim
WORKDIR /app
ADD target/*.jar app.jar
EXPOSE 8761
CMD ["java", "-jar", "app.jar"]