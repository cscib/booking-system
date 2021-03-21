package com.cscib.bookingconsumerservice.repository;

import com.cscib.bookingconsumerservice.model.TripWaypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripWaypointRepository extends JpaRepository<TripWaypoint, String> {
}
