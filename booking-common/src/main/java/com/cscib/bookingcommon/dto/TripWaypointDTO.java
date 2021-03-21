package com.cscib.bookingcommon.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripWaypointDTO {

    private String id;

    private String locality;

    private double latitude;

    private double longitude;

}
