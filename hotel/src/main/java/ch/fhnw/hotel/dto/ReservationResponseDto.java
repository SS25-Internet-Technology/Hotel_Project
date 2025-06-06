package ch.fhnw.hotel.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ch.fhnw.hotel.data.domain.PaymentInfo;
import ch.fhnw.hotel.data.domain.Reservation;
import ch.fhnw.hotel.data.enumtype.RoomType;
import lombok.Getter;

@Getter
public class ReservationResponseDto {
    private Long id;
    private String roomNumber;
    private RoomType roomType;
    private boolean smokeAllowed;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<String> extraServices;
    private BigDecimal total;
    private PaymentInfo paymentInfoDetails;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.roomNumber = reservation.getRoom().getRoomNumber();
        this.roomType = reservation.getRoom().getRoomType();
        this.smokeAllowed = reservation.getRoom().isSmokeAllowed();
        this.checkInDate = reservation.getCheckInDate();
        this.checkOutDate = reservation.getCheckOutDate();
        this.extraServices = reservation.getExtraServices().stream()
            .map(link -> link.getExtraService().getType().name())
            .distinct()
            .toList();
        this.total = reservation.getTotal();
        this.paymentInfoDetails = reservation.getPaymentInfo();
    }
}
