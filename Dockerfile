FROM openjdk:11
COPY target/ecommerceBi-0.0.1-SNAPSHOT.jar ecommerceBi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/ecommerceBi-0.0.1-SNAPSHOT.jar"]