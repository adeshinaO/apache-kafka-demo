FROM openjdk:8-jre-alpine
ADD target/${JAR_FILE} /usr/share/kafka-demo-consumer-2.jar
ENTRYPOINT ["java", "-jar", "/usr/share/kafka-demo-consumer.jar"]
