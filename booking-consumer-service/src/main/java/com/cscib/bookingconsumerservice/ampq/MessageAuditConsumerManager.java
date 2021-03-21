package com.cscib.bookingconsumerservice.ampq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class MessageAuditConsumerManager extends AbstractBookingConsumerManager {

    @Value("${rabbitmq.messageAuditQueueName}")
    private String messageAuditQueueName;

    public static String getQueueName() {
        return QueueName.get();
    }

    @PostConstruct
    private void initialize(){
        MessageAuditConsumerManager.setQueueName(messageAuditQueueName);
    }

    @RabbitListener(queues = "#{T(com.cscib.bookingconsumerservice.ampq.MessageAuditConsumerManager).getQueueName()}",
            containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(Message message) {

        process(message, "AUDIT");
    }
}
