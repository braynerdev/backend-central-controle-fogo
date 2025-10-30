#!/bin/bash
# Build script for Render deployment

echo "Starting build process..."

# Clean and build the project with Maven
./mvnw clean package -DskipTests

echo "Build completed successfully!"
echo "JAR file location: target/backend-central-controle-fogo-0.0.1-SNAPSHOT.jar"
