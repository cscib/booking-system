package com.cscib.bookingcommon.bookingproducerservice.service;

import com.cscib.bookingcommon.dto.BookingDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingService {


    public void saveBooking(BookingDTO booking) {

    }

    public void updateBooking(BookingDTO booking) {

    }

    public List<BookingDTO> getBookings() {
        return null;
    }

    public BookingDTO getBooking(String bookingId) {
        return BookingDTO.builder().id(bookingId).build();
    }
}
