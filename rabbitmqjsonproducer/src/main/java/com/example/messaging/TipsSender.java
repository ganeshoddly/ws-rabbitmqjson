package com.example.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class TipsSender {

    private static final Logger log = LoggerFactory.getLogger(TipsSender.class);

    @Autowired
    TipsConfig tipsConfig;
    
    private final RabbitTemplate rabbitTemplate;

    public TipsSender(final RabbitTemplate rabbitTemplate) {
      this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 10000L)
    public void sendPracticalTip() {
        TipsMessage tips = new TipsMessage("Always use Immutable classes in Java", 1, false);
        rabbitTemplate.convertAndSend(tipsConfig.getTopicExchange(), tipsConfig.getRoutingKey(), tips);
        log.info("Tips message sent {}", tips.toString());
    }
}