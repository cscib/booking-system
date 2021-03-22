package com.cscib.bookingcommon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {

    private String id;

    private String passengerName;

    private String passengerContactNumber;

    //2016-12-18@07:53:34.740+0000
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private OffsetDateTime pickupTime;

    private boolean asap;

    //2016-12-18@07:53:34.740+0000
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private OffsetDateTime waitingTime;

    private int numberOfPassengers;

    private BigDecimal price;

    private int rating;

    //2016-12-18@07:53:34.740+0000
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private OffsetDateTime createdOn;

    //2016-12-18@07:53:34.740+0000
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private OffsetDateTime lastModifiedOn;

    private List<TripWaypointDTO> tripWaypoints;

}
