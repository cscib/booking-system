package com.cscib.bookingconsumerservice.ampq;

import com.cscib.bookingconsumerservice.service.BookingConsumerService;
import com.cscib.bookingcommon.dto.BookingMessageDTO;
import com.cscib.bookingcommon.exceptions.BookingConsumerException;
import com.cscib.bookingcommon.enums.BookingOperationsEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class AbstractBookingConsumerManager {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookingConsumerService bookingConsumerService;


    protected static final ThreadLocal<String> QueueName = new ThreadLocal<String>();

    protected static void setQueueName(String name) {
        QueueName.set(name);
    }

    protected void process(Message message, String queueOperation) {
        log.info("[{}] {}",  message.getMessageProperties().getConsumerQueue(),
                new String(message.getBody()));

        BookingMessageDTO booking;
        try {
            booking = objectMapper.readValue(message.getBody(), BookingMessageDTO.class);

            try {
//                Optional.of(queueOperation)
//                        .filter(q -> q.equals("AUDIT"))
//                        .ifPresent(q->processAudit(q, booking));

//                Optional.of(queueOperation)
//                        .filter(q -> q.equals("AUDIT"))
//                        .ifPresentOrElse(this::processAudit,
//                                ()->processBooking(queueOperation,booking));


                Optional.of(booking)
                        .filter(b -> b.getOperation().equals("AUDIT"))
                        .ifPresentOrElse(this::processAudit,
                                ()->processBooking(queueOperation,booking));

            } catch (NullPointerException e) {
                if (booking == null) {
                    log.error("A null message arrived to the ADD queue");
                } else if (booking.getOperation() == null) {
                    log.error("Booking operation is empty for {}", booking.getBookingID() == null ? "" : booking.getBookingID());
                }
            }

        } catch (IOException e) {
            log.error("[IOException when reading message from {}] {}",  message.getMessageProperties().getConsumerQueue(),
                    e.getCause());
        }

    }



    private void processAudit(BookingMessageDTO booking) {
        try {
            bookingConsumerService.saveAuditLog(booking.getBookingDTO());
        } catch (Exception e) {
            log.error("An error occured while trying to do Operation {} on Booking ID {}", "AUDIT",
                    booking != null && booking.getBookingID() != null ? booking.getBookingID() : "Undefined");
        }
    }

    private void processBooking(String queueOperation, BookingMessageDTO booking)  {
        try {
            Optional.of(booking)
                    .filter(b-> (b.getOperation().equals(queueOperation)))
                    .orElseThrow(BookingConsumerException::new);

            switch (BookingOperationsEnum.valueOf(booking.getOperation())) {
                case ADD:  bookingConsumerService.saveBooking(booking.getBookingDTO()); break;
                case EDIT: bookingConsumerService.updateBooking(booking.getBookingDTO()); break;
                case DELETE: bookingConsumerService.deleteBooking(booking.getBookingID()); break;
            }
        } catch (BookingConsumerException e) {
            log.error("[{} - {}] Message arrived to the wrong ADD queue", booking.getBookingID(), booking.getOperation() );
        } catch (Exception e) {
            log.error("An error occured while trying to do Operation {} on Booking ID {}", queueOperation,
                    booking != null && booking.getBookingID() != null ? booking.getBookingID() : "Undefined");
        }
    }
}
