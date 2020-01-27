package co.adeshina.kafka.demo.consumer.service;

import co.adeshina.kafka.demo.consumer.dto.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumerServiceImpl implements KafkaConsumerService<Message> {

    private KafkaConsumer<String, String> consumer;
    private Logger logger = LoggerFactory.getLogger(KafkaMessageConsumerServiceImpl.class);

    private static final String TOPIC = "messages-2020";

    @Autowired
    public KafkaMessageConsumerServiceImpl(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public List<Message> poll() {

        List<Message> result = new ArrayList<>();
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

        for (ConsumerRecord<String, String> record : records.records(TOPIC)) {
            Message message;
            try {
                message = fromJson(record.value());
            } catch (JsonProcessingException e) {
                logger.error("failure", e);
                continue;
            }
            message.setPartition(record.partition());
            result.add(message);
        }

        return result;
    }

    @Override
    public void commitOffsets() {
        consumer.commitSync();
    }

    private Message fromJson(String json) throws JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Message.class);
    }
}
