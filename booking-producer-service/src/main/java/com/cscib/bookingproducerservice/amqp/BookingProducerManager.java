package com.cscib.bookingproducerservice.amqp;

import com.cscib.bookingcommon.dto.BookingMessageDTO;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @Async
    public Future<Boolean> sendToMessageExchange(BookingMessageDTO message) {
        log.info("[{} - {}] Sending message to Message Exchange.", message.getBookingID(), message.getOperation());

        try {
            rabbitTemplate.convertAndSend(messageExchangeName, buildMessage(message));
            //rabbitTemplate.convertAndSend(bookingExchangeName, message.getOperation(), buildMessage(message));
        } catch (JsonProcessingException e) {
            log.error("Error sending queue for booking {} with message.", message.getBookingID());
            return new AsyncResult<Boolean>(false);
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

}
