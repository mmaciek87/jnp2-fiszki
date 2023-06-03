FROM eclipse-temurin:17-jdk
# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

# Copy the source code
COPY src ./src

# Build the application
RUN chmod +x ./gradlew
RUN ./gradlew build

# Copy the JAR file to the container
RUN cp build/libs/rewards-0.0.1-SNAPSHOT.jar your-app.jar

# Expose the port on which your Spring application runs
EXPOSE 8080

# Define the command to run your application when the container starts
CMD ["java", "-jar", "your-app.jar"]