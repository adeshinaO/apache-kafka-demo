package co.adeshina.kafka.demo.consumer.controller;

import co.adeshina.kafka.demo.consumer.dto.Message;
import co.adeshina.kafka.demo.consumer.service.MockMessageDatabaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    private MockMessageDatabaseService databaseService;

    @Autowired
    public MessageController(MockMessageDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping
    public String messages(Model model) {
        List<Message> messages = databaseService.all();
        model.addAttribute("messages", messages);
        return "messages";
    }
}
