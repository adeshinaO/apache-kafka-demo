package co.adeshina.kafka.demo.consumer.service;

import co.adeshina.kafka.demo.consumer.dto.Message;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MockMessageDatabaseService {

    private final List<Message> messages = new ArrayList<>();

    public void insert(List<Message> messages) {
        this.messages.addAll(messages);
    }

    public List<Message> all() {
        return new ArrayList<>(messages);
    }
}
