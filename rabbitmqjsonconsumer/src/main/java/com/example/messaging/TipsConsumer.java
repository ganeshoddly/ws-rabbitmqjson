package com.example.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TipsConsumer {

    private static final Logger log = LoggerFactory.getLogger(TipsConsumer.class);

    @RabbitListener(queues = "${practical.rabbitmq.queue}")
    public void consumeDefaultMessage(final TipsMessage message)  
    {
        log.info("Received message, tip is: {}", message.getText());
        //Output for above is -> Received message, tip is: Always use Immutable classes in Java
        //log.info("Received payload, tip is: {}", message.toString());
    }
}