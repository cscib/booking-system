package com.cscib.bookingcommon.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingMessageDTO {

    private String operation;

    private String bookingID;

    private BookingDTO bookingDTO;
}
