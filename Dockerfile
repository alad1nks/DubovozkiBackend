FROM openjdk:17-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
COPY certs/application-prod.crt certs/application-prod.crt
COPY certs/application-prod.key certs/application-prod.key
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]
