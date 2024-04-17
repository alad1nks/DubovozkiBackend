FROM openjdk:17-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "app.jar"]
