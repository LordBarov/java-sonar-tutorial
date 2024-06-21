# Use the official SonarQube image as the base image
FROM sonarqube:latest

# Set the SonarQube home directory as an environment variable
ENV SONAR_HOME=/opt/sonarqube

# Copy the custom rules JAR file to the SonarQube plugins directory
COPY target/java-custom-rules-example-1.0.0-SNAPSHOT.jar $SONAR_HOME/extensions/plugins/
