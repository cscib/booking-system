package com.cscib.bookingconsumerservice.config;

import com.cscib.bookingcommon.enums.BookingOperationsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfig {


    @Value("${rabbitmq.queueNonDurable}")
    private boolean isNonDurable;

    @Value("${rabbitmq.messageExchangeName}")
    private String messageExchangeName;

    @Value("${rabbitmq.messageAuditQueueName}")
    private String messageAuditQueueName;

    @Value("${rabbitmq.bookingExchangeName}")
    private String bookingExchangeName;

    @Value("${rabbitmq.bookingAddQueueName}")
    private String bookingAddQueueName;

    @Value("${rabbitmq.bookingEditQueueName}")
    private String bookingEditQueueName;

    @Value("${rabbitmq.bookingDeleteQueueName}")
    private String bookingDeleteQueueName;


    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        return factory;
    }

}


