package com.cscib.bookingconsumerservice.model.mapper;

import com.cscib.bookingcommon.dto.BookingDTO;
import com.cscib.bookingconsumerservice.model.Booking;
import com.cscib.bookingconsumerservice.model.TripWaypoint;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {


    public static Booking map(BookingDTO bookingDTO) {
        List<TripWaypoint> tripWaypoints = bookingDTO.getTripWaypoints().stream()
                .map(tw-> TripWaypoint.builder()
                        .id(tw.getId())
                        .latitude(tw.getLatitude())
                        .longitude(tw.getLongitude())
                        .locality(tw.getLocality())
                        .build())
                .collect(Collectors.toList());

        return Booking.builder()
                .id(bookingDTO.getId())
                .passengerName(bookingDTO.getPassengerName())
                .passengerContactNumber(bookingDTO.getPassengerContactNumber())
                .pickupTime(bookingDTO.getPickupTime())
                .asap(bookingDTO.isAsap())
                .waitingTime(bookingDTO.getWaitingTime())
                .numberOfPassengers(bookingDTO.getNumberOfPassengers())
                .price(bookingDTO.getPrice())
                .rating(bookingDTO.getRating())
                .createdOn(bookingDTO.getCreatedOn())
                .lastModifiedOn(bookingDTO.getLastModifiedOn())
                .tripWaypoints(tripWaypoints)
                .build();
    }
}
