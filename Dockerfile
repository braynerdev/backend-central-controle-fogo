# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8088

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
