FROM openjdk:8
ADD target/mediscreen-0.0.1-SNAPSHOT.jar mediscreen_note-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/mediscreen_note-0.0.1-SNAPSHOT.jar"]
