package com.cscib.bookingconsumerservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="booking")
public class Booking {

    @Id
    private String id;

    @Column(name="passenger_name")
    private String passengerName;

    @Column(name="passenger_contact_number")
    private String passengerContactNumber;

    @Column(name="pickup_time")
    private OffsetDateTime pickupTime;

    private boolean asap;

    @Column(name="waiting_time")
    private OffsetDateTime waitingTime;

    @Column(name="number_of_passengers")
    private int numberOfPassengers;

    private BigDecimal price;

    private int rating;

    @Column(name="created_on")
    private OffsetDateTime createdOn;

    @Column(name="last_modified_on")
    private OffsetDateTime lastModifiedOn;

    @OneToMany(mappedBy="booking")
    private List<TripWaypoint> tripWaypoints;

}
