# Use a base image with Gradle and Java pre-installed
FROM gradle:jdk17 AS builder

# Set the working directory
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle .
COPY settings.gradle .

# Download and cache the dependencies
RUN gradle --no-daemon dependencies

# Copy the source code
COPY src/ src/

# Build the application
RUN gradle --no-daemon build

# Use a lightweight base image for the final application
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/your-app.jar .

# Set the command to run the application
CMD ["java", "-jar", "your-app.jar"]
