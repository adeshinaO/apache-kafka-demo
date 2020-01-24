package co.adeshina.kafka.demo.producer.services;

import co.adeshina.kafka.demo.producer.exception.PublishFailedException;

// todo: Javadoc
public interface PublisherService<K, V> {

    // interface for class that writes messages to Kafka

    void publish(K key, V message) throws PublishFailedException;

}
