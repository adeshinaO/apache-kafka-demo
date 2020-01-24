FROM openjdk:8-jre
ADD target/${JAR_FILE} /usr/share/kafka-demo-consumer-2.jar
ENTRYPOINT ["java", "-jar", "/usr/share/kafka-demo-consumer-2.jar"]
