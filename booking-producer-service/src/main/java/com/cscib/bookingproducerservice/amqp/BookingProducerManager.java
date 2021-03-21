package com.cscib.bookingproducerservice.amqp;

import com.cscib.bookingcommon.dto.BookingMessageDTO;
import com.cscib.bookingcommon.exceptions.BookingConsumerException;
import com.cscib.bookingcommon.exceptions.BookingProducerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;


@Slf4j
@EnableAsync
@Component
public class BookingProducerManager {


    @Value("${rabbitmq.messageExchangeName}")
    private String messageExchangeName;

    @Value("${rabbitmq.bookingExchangeName}")
    private String bookingExchangeName;


    @Value("${rabbitmq.bookingAddQueueName}")
    private String bookingAddQueueName;


    @Value("${rabbitmq.bookingEditQueueName}")
    private String bookingEditQueueName;


    @Value("${rabbitmq.bookingDeleteQueueName}")
    private String bookingDeleteQueueName;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @Async
    public Future<Boolean> sendToMessageExchange(BookingMessageDTO message) {

        try {
            String routingKey = getRoutingKey(message.getOperation());
            log.info("[{} - {}] Sending message to Message Exchange {} with Routing Key {}.", message.getBookingID(),
                    message.getOperation(), messageExchangeName, routingKey);

            rabbitTemplate.convertAndSend(messageExchangeName, routingKey, buildMessage(message));
        } catch (JsonProcessingException e) {
            log.error("Error sending queue for booking {} with message.", message.getBookingID());
            return new AsyncResult<Boolean>(false);
        } catch (BookingProducerException e) {
            log.error("Invalid operation type {}", message.getOperation());
        }
        return new AsyncResult<Boolean>(true);
    }

    public Message buildMessage(BookingMessageDTO point) throws JsonProcessingException {

        String orderJson = objectMapper.writeValueAsString(point);
        return MessageBuilder
                .withBody(orderJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
    }


     private String getRoutingKey(String operation) throws BookingProducerException {

        String routingKey = bookingAddQueueName.toLowerCase().contains(operation.toLowerCase())
                                ? bookingAddQueueName
                                :
                                    (bookingEditQueueName.toLowerCase().contains(operation.toLowerCase())
                                            ? bookingEditQueueName
                                            :
                                                (bookingDeleteQueueName.toLowerCase().contains(operation.toLowerCase())
                                                ? bookingDeleteQueueName : ""
                                                )
                                    );

        if (routingKey.isEmpty()) throw new BookingProducerException();
        return routingKey;
    }

}
