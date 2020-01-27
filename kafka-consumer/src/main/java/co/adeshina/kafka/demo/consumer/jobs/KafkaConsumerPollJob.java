package co.adeshina.kafka.demo.consumer.jobs;

import co.adeshina.kafka.demo.consumer.dto.Message;
import co.adeshina.kafka.demo.consumer.service.KafkaConsumerService;
import co.adeshina.kafka.demo.consumer.service.MockMessageDatabaseService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerPollJob {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumerPollJob.class);
    private final List<Message> buffer = new ArrayList<>();
    private static final int MIN_BUFFER_SIZE = 5;

    private MockMessageDatabaseService mockMessageDatabaseService;
    private KafkaConsumerService<Message> kafkaConsumerService;

    @Autowired
    public KafkaConsumerPollJob(KafkaConsumerService<Message> kafkaConsumerService,
            MockMessageDatabaseService mockMessageDatabaseService) {
        this.kafkaConsumerService = kafkaConsumerService;
        this.mockMessageDatabaseService = mockMessageDatabaseService;
    }

    @Scheduled(fixedRate = 3500)
    public void pollBroker() {
        logger.info("Starting scheduled job to poll broker");
        List<Message> messages = kafkaConsumerService.poll();
        buffer.addAll(messages);
        logger.info("Adding {} messages to buffer", messages.size());
    }

    @Scheduled(fixedRate = 35000)
    public void flushBuffer() {
        logger.info("Starting scheduled job to flush buffer");

        if (buffer.size() > MIN_BUFFER_SIZE) {
           mockMessageDatabaseService.insert(new ArrayList<>(buffer));
           kafkaConsumerService.commitOffsets();
           int size = buffer.size();
           buffer.clear();
           logger.info("{} messages written to mock DB. Buffer cleared", size);
        } else {
            logger.info("Buffer size too small. No flush");
        }
    }

}
