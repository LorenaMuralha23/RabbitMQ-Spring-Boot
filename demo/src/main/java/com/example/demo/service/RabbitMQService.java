package com.example.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String queueName, Object message) {
        try {
            String messageJSON = this.objectMapper.writeValueAsString(message);
            this.rabbitTemplate.convertAndSend(queueName, messageJSON);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RabbitMQService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
