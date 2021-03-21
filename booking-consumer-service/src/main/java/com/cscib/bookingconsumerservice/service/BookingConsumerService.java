package com.cscib.bookingconsumerservice.service;

import com.cscib.bookingcommon.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookingConsumerService {


    public void saveBooking(BookingDTO bookingDTO) {
        // TODO implement method
        log.info("{} NEW Booking ADDED " + bookingDTO.getId());
    }

    public void updateBooking(BookingDTO bookingDTO) {
        // TODO implement method
        log.info("{} EXISTING Booking UPDATED " + bookingDTO.getId());
    }

    public void deleteBooking(String bookingID) {
        // TODO implement method
        log.info("{} EXISTING Booking DELETED " + bookingID);
    }

    public void saveAuditLog(BookingDTO bookingDTO) {
        // TODO implement method
        log.info("{} AUDITING Booking Operation " + bookingDTO.getId());
    }
}
