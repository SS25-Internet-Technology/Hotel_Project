package ch.fhnw.hotel.dto;

import java.time.LocalDate;
import java.util.List;

import ch.fhnw.hotel.data.enumtype.RoomType;
import lombok.Getter;

@Getter
public class ReservationRequestDto {
    private RoomType roomType;
    private boolean smokeAllowed;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> extraServiceIds;

    // Payment information fields
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String creditCard;
}