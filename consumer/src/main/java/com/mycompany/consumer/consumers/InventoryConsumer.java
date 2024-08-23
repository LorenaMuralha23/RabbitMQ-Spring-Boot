package com.mycompany.consumer.consumers;

import com.example.demo.constants.RabbitMQConstants;
import com.example.demo.dtos.InventoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitMQConstants.QUEUE_INVENTORY)
    private void consumer(String message) {
        try {
            // Converte a String JSON para o objeto InventoryDTO
            InventoryDTO inventoryDTO = objectMapper.readValue(message, InventoryDTO.class);
            System.out.println("Product Code: " + inventoryDTO.productCode);
            System.out.println("Quantity: " + inventoryDTO.quantity);
        } catch (Exception e) {
            System.out.println("-----ERROR DEBUG-----");
            e.printStackTrace();
            System.out.println("----------------------");
        }
    }
}
