package co.adeshina.kafka.demo.consumer.service;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface KafkaConsumerService<T> {

    /**
     *
     * @return
     */
    List<T> poll();

    /**
     *
     */
    void commitOffsets();
}
