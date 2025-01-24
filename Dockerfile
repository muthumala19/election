FROM openjdk:17
WORKDIR /app
COPY target/votezone-0.0.1-SNAPSHOT.jar /app/votezone-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/app/votezone-0.0.1-SNAPSHOT.jar"]
