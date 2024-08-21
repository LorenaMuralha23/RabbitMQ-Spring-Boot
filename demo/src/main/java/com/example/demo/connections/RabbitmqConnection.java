package com.example.demo.connections;

import com.example.demo.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConnection {

    private static final String EXCHANGE_NAME = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitmqConnection(AmqpAdmin ampqAdmin) {
        this.amqpAdmin = ampqAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void createQueue() {
        try {
            Queue inventoryQueue = this.queue(RabbitMQConstants.QUEUE_INVENTORY);
            System.out.println("Queue 1: " + inventoryQueue.toString());
            Queue priceQueue = this.queue(RabbitMQConstants.QUEUE_PRICE);
            System.out.println("Queue 2: " + priceQueue.toString());

            DirectExchange exchange = this.directExchange();

            Binding bidingInventory = this.binding(inventoryQueue, exchange);
            Binding bidingPrice = this.binding(priceQueue, exchange);

            //Creating the queues
            this.amqpAdmin.declareQueue(inventoryQueue);
            this.amqpAdmin.declareQueue(priceQueue);

            this.amqpAdmin.declareExchange(exchange);

            this.amqpAdmin.declareBinding(bidingInventory);
            this.amqpAdmin.declareBinding(bidingPrice);
            
        } catch (Exception e) {
            System.out.println("====== DEU ERRO PORRA =======");
            System.out.print("Erro: ");
            e.printStackTrace();
        }
    }
}
