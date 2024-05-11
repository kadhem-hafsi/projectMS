
FROM openjdk:17-jdk-slim
WORKDIR /app
ADD target/*.jar app.jar
EXPOSE 9099
CMD ["java", "-jar", "/app/app.jar"]