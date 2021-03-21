package com.cscib.bookingconsumerservice.service;

import com.cscib.bookingcommon.dto.BookingDTO;
import com.cscib.bookingconsumerservice.model.Booking;
import com.cscib.bookingconsumerservice.model.TripWaypoint;
import com.cscib.bookingconsumerservice.model.mapper.BookingMapper;
import com.cscib.bookingconsumerservice.repository.BookingRepository;
import com.cscib.bookingconsumerservice.repository.TripWaypointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BookingConsumerService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripWaypointRepository tripWaypointRepository;

    public void saveBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.map(bookingDTO);
        for (TripWaypoint waypoint : booking.getTripWaypoints()) {
            tripWaypointRepository.save(waypoint);
        }
        bookingRepository.save(booking);
        log.info("{} NEW Booking ADDED " + bookingDTO.getId());
    }


    public void updateBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.map(bookingDTO);
        for (TripWaypoint waypoint : booking.getTripWaypoints()) {
            tripWaypointRepository.save(waypoint);
        }
        bookingRepository.save(booking);
        log.info("{} EXISTING Booking UPDATED " + bookingDTO.getId());
    }

    public void deleteBooking(String bookingID) {
        bookingRepository.deleteById(bookingID);
        log.info("{} EXISTING Booking DELETED " + bookingID);
    }

    public void saveAuditLog(BookingDTO bookingDTO) {
        // TODO In the future one may audit transactions in database.
        log.info("{} AUDITING Booking Operation " + bookingDTO.getId());
    }
}
