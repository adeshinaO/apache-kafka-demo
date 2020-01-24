FROM openjdk:8-jre
ADD target/${JAR_FILE} /usr/share/kafka-demo-producer.jar
ENTRYPOINT ["java", "-jar", "/usr/share/kafka-demo-producer.jar"]
