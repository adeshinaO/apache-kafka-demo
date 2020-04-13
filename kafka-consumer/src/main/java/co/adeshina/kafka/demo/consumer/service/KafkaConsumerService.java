package co.adeshina.kafka.demo.consumer.service;

import java.util.List;

public interface KafkaConsumerService<T> {

    List<T> poll();

    void commitOffsets();
}
