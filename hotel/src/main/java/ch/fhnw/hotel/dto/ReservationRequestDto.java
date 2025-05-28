package ch.fhnw.hotel.dto;

import java.time.LocalDate;
import java.util.List;

public class ReservationRequestDto {

    private Long roomId;
    private String paymentInfo;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<Long> extraServiceIds;

    // Getters and Setters
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(String paymentInfo) { this.paymentInfo = paymentInfo; }

    public List<Long> getExtraServiceIds() { return extraServiceIds; }
    public void setExtraServiceIds(List<Long> extraServiceIds) { this.extraServiceIds = extraServiceIds; }
}