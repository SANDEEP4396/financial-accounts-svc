FROM eclipse-temurin:21-jdk

LABEL "maintainer"="Sandeep"

#Copy the built JAR file into the docker container
COPY target/accounts-svc-0.0.1-SNAPSHOT.jar accounts-svc-0.0.1-SNAPSHOT.jar

# Whenever someone is trying to generate a container from this image, the command to run the JAR file will be executed.
# java - means to run the java application, -jar means that we are running a jar file and then we specify the name of the jar file.
# command : 'java -jar target/accounts-svc-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-jar", "accounts-svc-0.0.1-SNAPSHOT.jar"]