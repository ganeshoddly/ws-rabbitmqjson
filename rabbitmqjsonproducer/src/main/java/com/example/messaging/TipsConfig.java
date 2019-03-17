package com.example.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class TipsConfig {
	
	//make sure to use the same version for jackson jars..for all the 3
	/*public static final String EXCHANGE_NAME = "sg_topic_exchange";
	public static final String DEFAULT_PARSING_QUEUE = "sg_parsing_queue";
	public static final String ROUTING_KEY = "sg_routing_key";*/
	
	@Value("${practical.rabbitmq.topicExchange}")
	private String topicExchange;
	
	@Value("${practical.rabbitmq.queue}")
	private String queue;
	
	@Value("${practical.rabbitmq.routingKey}")
	private String routingKey;
	
	
	public String getTopicExchange() {
		return topicExchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	@Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Queue defaultParsingQueue() {
        return new Queue(queue);
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder.bind(defaultParsingQueue()).to(topicExchange()).with(routingKey);
    }
    
    @Bean
    public Jackson2JsonMessageConverter produceMessageConverter() {
    	ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(produceMessageConverter());
        return rabbitTemplate;
    }

}
