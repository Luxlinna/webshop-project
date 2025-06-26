# Use a JDK image compatible with ARM64 (Apple Silicon)
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy built JAR file
COPY target/webshop-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
