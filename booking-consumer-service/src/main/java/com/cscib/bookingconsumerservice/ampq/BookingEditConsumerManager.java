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
public class BookingEditConsumerManager  extends AbstractBookingConsumerManager {

    @Value("${rabbitmq.bookingEditQueueName}")
    private String bookingEditQueueName;

    public static String getQueueName() {
        return QueueName.get();
    }

    @PostConstruct
    private void initialize(){
        BookingEditConsumerManager.setQueueName(bookingEditQueueName);
    }


    @RabbitListener(queues = "#{T(com.cscib.bookingconsumerservice.ampq.BookingEditConsumerManager).getQueueName()}",
            containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(Message message) {
        process(message,  BookingOperationsEnum.EDIT.name());
    }
}
