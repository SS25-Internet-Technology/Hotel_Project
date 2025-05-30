package ch.fhnw.hotel.dto;

import java.math.BigDecimal;

import ch.fhnw.hotel.data.enumtype.RoomType;
import lombok.Getter;

@Getter
public class RoomRequestDto {
    private BigDecimal price;
    private String roomNumber;
    private boolean roomAvailability;
    private RoomType roomType;
    private boolean smokeAllowed;
    private BigDecimal seasonalMultiplier;
}