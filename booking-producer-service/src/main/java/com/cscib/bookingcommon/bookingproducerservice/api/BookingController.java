package com.cscib.bookingcommon.bookingproducerservice.api;

import com.cscib.bookingcommon.dto.BookingDTO;
import com.cscib.bookingcommon.bookingproducerservice.service.BookingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/v1/booking/{bookingId}")
    BookingDTO getBooking(@PathVariable String bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @GetMapping("/v1/booking/all")
    List<BookingDTO> getBookings() {
        return bookingService.getBookings();
    }

}
