package ch.fhnw.hotel.dto;

import java.time.LocalDate;
import java.util.List;

import ch.fhnw.hotel.data.enumtype.RoomType;
import lombok.Getter;

@Getter
public class ReservationRequestDto {
    private RoomType roomType;
    private boolean smokeAllowed;
    private String paymentInfo;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> extraServiceIds;
}