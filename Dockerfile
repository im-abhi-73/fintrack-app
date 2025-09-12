# Use the correct base image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/FinTrackSystem-0.0.1-SNAPSHOT.jar FinTrackSystem-v1.0.jar

# Expose the port your Spring Boot app runs on
EXPOSE 9000

# Run the JAR
ENTRYPOINT ["java", "-jar", "FinTrackSystem-v1.0.jar"]
