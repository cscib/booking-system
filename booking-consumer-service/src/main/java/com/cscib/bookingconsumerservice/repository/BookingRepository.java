package com.cscib.bookingconsumerservice.repository;

import com.cscib.bookingconsumerservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}
