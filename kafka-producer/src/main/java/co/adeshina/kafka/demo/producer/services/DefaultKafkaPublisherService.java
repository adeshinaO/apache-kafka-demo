package co.adeshina.kafka.demo.producer.services;

import co.adeshina.kafka.demo.producer.dto.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DefaultKafkaPublisherService implements KafkaPublisherService<String, Message> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Producer<String, String> producer;

    private final static String TOPIC = "messages-2020";

    @Autowired
    public DefaultKafkaPublisherService(Producer<String, String> producer) {
        this.producer = producer;
    }

    @Override
    public void publish(@Nullable String key, Message message) {

        String json;

        try {
            json = toJson(message);
        } catch (JsonProcessingException e) {
            logger.error("Failed to publish message: ", e);
            return;
        }

        ProducerRecord<String, String> record =
                Objects.isNull(key) ? new ProducerRecord<>(TOPIC, json) : new ProducerRecord<>(TOPIC, key, json);

        producer.send(record, ((metadata, exception) -> {
            if (Objects.nonNull(exception)) {
                logger.error("Failed to write message to broker",exception);
            } else {
                logger.info("Successful send. Partition: {}, Topic: {} ", metadata.partition(), metadata.topic());
            }
        }));
    }

    @Override
    public String topic() {
        return TOPIC;
    }

    private String toJson(Message message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }
}
