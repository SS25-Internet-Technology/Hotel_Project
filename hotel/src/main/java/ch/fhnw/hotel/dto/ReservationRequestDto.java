package ch.fhnw.hotel.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class ReservationRequestDto {

    private Long roomId;
    private String paymentInfo;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> extraServiceIds;
}