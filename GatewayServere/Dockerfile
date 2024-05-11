
FROM openjdk:8-jre-alpine

WORKDIR /app

ADD target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]