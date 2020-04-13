FROM openjdk:8-jre-alpine
COPY target/kafka-consumer-1.0.0.jar /usr/share/kafka-consumer.jar
ENTRYPOINT ["java", "-jar", "/usr/share/kafka-consumer.jar"]
