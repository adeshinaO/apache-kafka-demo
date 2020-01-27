package co.adeshina.kafka.demo.producer.controller;

import co.adeshina.kafka.demo.producer.dto.Message;
import co.adeshina.kafka.demo.producer.services.KafkaPublisherService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private KafkaPublisherService<String, Message> publisherService;

    @Autowired
    public MessageController(KafkaPublisherService<String, Message> publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public void message(@RequestBody List<Message> messages) {
        messages.forEach(message -> publisherService.publish(message.getSenderName(), message));
    }
}
