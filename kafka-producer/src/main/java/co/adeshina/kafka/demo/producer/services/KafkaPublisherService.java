package co.adeshina.kafka.demo.producer.services;

import co.adeshina.kafka.demo.producer.exception.PublishFailedException;

import org.apache.kafka.clients.producer.Producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.FutureRecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService implements PublisherService<String, String> {

    private Producer<String, String> producer;

    @Autowired // @Qualifier may be needed on this param later.. wait after testing.
    public KafkaPublisherService(Producer<String, String> producer) {
        this.producer = producer;
    }

    @Override
    public void publish(String key, String message) throws PublishFailedException {
        producer.send(null); // todo: see Javadocs -- it throws exceptions
    }
}

