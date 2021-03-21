package com.cscib.bookingproducerservice.service;

import com.cscib.bookingcommon.enums.BookingOperationsEnum;
import com.cscib.bookingcommon.dto.BookingDTO;
import com.cscib.bookingcommon.dto.BookingMessageDTO;
import com.cscib.bookingproducerservice.amqp.BookingProducerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookingService {

    @Autowired
    BookingProducerManager bookingProducerManager;

    public void saveBooking(BookingDTO booking) {
         bookingProducerManager.sendToMessageExchange(BookingMessageDTO.builder()
                .operation(BookingOperationsEnum.ADD.name())
                .bookingID(booking.getId())
                .bookingDTO(booking)
                .build());
    }

    public void updateBooking(BookingDTO booking) {
        bookingProducerManager.sendToMessageExchange(BookingMessageDTO.builder()
                .operation(BookingOperationsEnum.EDIT.name())
                .bookingID(booking.getId())
                .bookingDTO(booking)
                .build());
    }

    public void deleteBooking(String bookingId) {
        bookingProducerManager.sendToMessageExchange(BookingMessageDTO.builder()
                .operation(BookingOperationsEnum.DELETE.name())
                .bookingID(bookingId)
                .bookingDTO(BookingDTO.builder().build())
                .build());
    }
}
