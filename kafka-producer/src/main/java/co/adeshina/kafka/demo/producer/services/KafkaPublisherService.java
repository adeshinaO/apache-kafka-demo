package co.adeshina.kafka.demo.producer.services;

// todo: Javadoc
public interface KafkaPublisherService<K, V> {

    // interface for class that writes messages to Kafka
    void publish(K key, V message);

    // returns the topic to which this publisher is sending messages.
    String topic();
}
