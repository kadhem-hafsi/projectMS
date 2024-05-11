
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY target/*.jar app.jar
EXPOSE 7077
# Run app.jar when the container launches
CMD ["java", "-jar", "app.jar"]