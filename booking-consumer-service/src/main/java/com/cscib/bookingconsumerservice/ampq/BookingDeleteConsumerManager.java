package com.cscib.bookingconsumerservice.ampq;

import com.cscib.bookingcommon.enums.BookingOperationsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class BookingDeleteConsumerManager extends AbstractBookingConsumerManager {

    @Value("${rabbitmq.bookingDeleteQueueName}")
    private String bookingDeleteQueueName;


    protected static final ThreadLocal<String> QueueName = new ThreadLocal<String>();

    protected static void setQueueName(String name) {
        QueueName.set(name);
    }

    public static String getQueueName() {
        return QueueName.get();
    }

    @PostConstruct
    private void initialize(){
        BookingDeleteConsumerManager.setQueueName(bookingDeleteQueueName);
        log.info("Initializing Listener for DELETE Operation with Routing Key {} ", getQueueName());
    }

    @RabbitListener(queues = "#{T(com.cscib.bookingconsumerservice.ampq.BookingDeleteConsumerManager).getQueueName()}",
            containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(Message message) {
        process(message,  BookingOperationsEnum.DELETE.name());
    }
}

