package com.cscib.bookingproducerservice.api;

import com.cscib.bookingproducerservice.service.BookingService;
import com.cscib.bookingcommon.dto.BookingDTO;
import com.cscib.bookingcommon.dto.TripWaypointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import com.cscib.bookingcommon.util.DateUtils;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = BookingController.class)
@WithMockUser
public class BookingControllerMvcTests extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingService bookingService;

    private BookingDTO mockBookingDTO = new  BookingDTO("Booking1", "John Grisham",
            "+044 12345678", DateUtils.getCurrentTime(), true, null,
            2, new BigDecimal(10), 0, DateUtils.getCurrentTime(), DateUtils.getCurrentTime(),
            Arrays.asList(new TripWaypointDTO(), new TripWaypointDTO()));

    private String mockBookingJson = mapToJson(mockBookingDTO);

    public BookingControllerMvcTests() throws JsonProcessingException {
        log.info(mockBookingJson);
    }

    @Test
    void test(){
        log.info(mockBookingJson);
    }

//    String mockBookingJson = "{\"id\":\"Booking1\",\"passengerName\":\"John Grisham\"," +
//            "\"passengerContactNumber\":\"+044 12345678\",\"pickupTime\":\"2011-03-22T09:06:35.561+01:00\",\"asap\":\"true\"," +
//            "\"waitingTime\":\"\",\"numberOfPassengers\":\"2\",\"price\":\"10\"," +
//            "\"rating\":\"0\",\"createdOn\":\"2011-03-22T09:06:35.561+01:00\",\"lastModifiedOn\":\"2011-03-22T09:06:35.561+01:00\"," +
//            "\"tripWaypoints\":[\"\"]}";


//    @Test
//    void getBooking() throws Exception {
//        Mockito.when(
//                bookingService.getBooking(Mockito.anyString())).thenReturn(mockBookingDTO);
//
//        MvcResult result = mvc.perform(MockMvcRequestBuilders
//                .post("/v1/booking/{bookingId}","Booking1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mockBookingJson))
//                //.andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//
//        log.info("Test getBooking result - {}.",result.getResponse());
//
//       // JSONAssert.assertEquals(mockBookingJson, mapToJson(result.getResponse()), false);
//    }

}
