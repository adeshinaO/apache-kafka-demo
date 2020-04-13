FROM openjdk:8-jre-alpine
COPY target/kafka-producer-1.0.0.jar /usr/share/kafka-producer.jar
ENTRYPOINT ["java", "-jar", "/usr/share/kafka-producer.jar"]
