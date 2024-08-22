package com.mycompany.consumer.consumers;

import com.example.demo.constants.RabbitMQConstants;
import com.example.demo.dtos.InventoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class InventoryConsumer {

    @RabbitListener(queues = RabbitMQConstants.QUEUE_INVENTORY)
    private void consumer(Message message) {
        MessageConverter converter = new Jackson2JsonMessageConverter();
        InventoryDTO inventoryDTO = (InventoryDTO) converter.fromMessage(message);
        System.out.println("Product Code: " + inventoryDTO.productCode);
        System.out.println("Quantity: " + inventoryDTO.quantity);
    }
}
