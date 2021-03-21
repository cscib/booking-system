package com.cscib.bookingproducerservice.config;

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
    public Declarables bookingExchangeBindings() {
        Queue messageAuditQueue = new Queue(messageAuditQueueName, isNonDurable);
        Queue bookingAddQueue = new Queue(bookingAddQueueName, isNonDurable);
        Queue bookingEditQueue = new Queue(bookingEditQueueName, isNonDurable);
        Queue bookingDeleteQueue = new Queue(bookingDeleteQueueName, isNonDurable);
        TopicExchange bookingExchange = new TopicExchange(bookingExchangeName,  isNonDurable, false);
        FanoutExchange messageExchange = new FanoutExchange(messageExchangeName);

        return new Declarables(
                messageAuditQueue,
                bookingAddQueue,
                bookingEditQueue,
                bookingDeleteQueue,
                bookingExchange,
                messageExchange,
                BindingBuilder
                        .bind(messageAuditQueue)
                        .to(messageExchange),
                BindingBuilder.bind(bookingExchange).to(messageExchange),
                BindingBuilder
                        .bind(bookingAddQueue)
                        .to(bookingExchange)
                        .with("#" + BookingOperationsEnum.ADD.name().toLowerCase() + "#"),
                BindingBuilder
                        .bind(bookingEditQueue)
                        .to(bookingExchange)
                        .with("#" + BookingOperationsEnum.EDIT.name().toLowerCase() + "#"),
                BindingBuilder
                        .bind(bookingDeleteQueue)
                        .to(bookingExchange)
                        .with("#" + BookingOperationsEnum.DELETE.name().toLowerCase() + "#"));
    }


    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}


