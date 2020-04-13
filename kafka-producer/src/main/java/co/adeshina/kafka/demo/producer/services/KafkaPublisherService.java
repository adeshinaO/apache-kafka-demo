package co.adeshina.kafka.demo.producer.services;

public interface KafkaPublisherService<K, V> {

    void publish(K key, V message);

    String topic();
}
