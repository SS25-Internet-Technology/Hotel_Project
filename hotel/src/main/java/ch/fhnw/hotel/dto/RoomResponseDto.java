package ch.fhnw.hotel.dto;

import java.math.BigDecimal;

import ch.fhnw.hotel.data.domain.Room;
import lombok.Getter;

@Getter
public class RoomResponseDto {
    private Long id;
    private BigDecimal price;
    private String roomNumber;
    private boolean roomAvailability;
    private String roomType;
    private boolean smokeAllowed;
    private BigDecimal seasonalMultiplier;


    public RoomResponseDto(Room room) {
        this.id = room.getId();
        this.price = room.getPrice();
        this.roomNumber = room.getRoomNumber();
        this.roomAvailability = room.isRoomAvailability();
        this.roomType = room.getRoomType().toString();
        this.smokeAllowed = room.isSmokeAllowed();
        this.seasonalMultiplier = room.getSeasonalMultiplier();
    }   
}
