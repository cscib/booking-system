package com.cscib.bookingproducerservice.api;

import com.cscib.bookingcommon.dto.BookingDTO;
import com.cscib.bookingproducerservice.service.BookingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @SneakyThrows
    @PostMapping(value = "/v1/booking/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public void createBooking(@RequestBody BookingDTO booking) {
        bookingService.saveBooking(booking);
    }

    @SneakyThrows
    @PostMapping(value = "/v1/booking/{bookingId}/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public void updateBooking(@RequestBody BookingDTO booking) {
        bookingService.updateBooking(booking);
    }

    @SneakyThrows
    @DeleteMapping(value = "/v1/booking/{bookingId}/delete")
    public void deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
    }

}
