FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Build the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar /app/conferenceRoomBooking.jar

# Set JVM options
ENV JAVA_OPTS="-XX:+UseG1GC -Xmx1024m"

# Command to run the application
CMD java $JAVA_OPTS -jar /app/conferenceRoomBooking.jar
