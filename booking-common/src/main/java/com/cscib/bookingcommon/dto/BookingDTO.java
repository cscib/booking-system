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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
    private OffsetDateTime pickupTime;

    private boolean asap;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
    private OffsetDateTime waitingTime;

    private int numberOfPassengers;

    private BigDecimal price;

    private int rating;

    //2011-03-22T09:06:35.561+01:00
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
    private OffsetDateTime createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
    private OffsetDateTime lastModifiedOn;

    private List<TripWaypointDTO> tripWaypoints;

}
