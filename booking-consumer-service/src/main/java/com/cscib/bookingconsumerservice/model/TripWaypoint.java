package com.cscib.bookingconsumerservice.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="triple_waypoint")
public class TripWaypoint {

    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="booking_id",foreignKey = @ForeignKey(name = "FK_booking_waypoints"))
    private Booking booking;

    private String locality;

    private double latitude;

    private double longitude;

}
