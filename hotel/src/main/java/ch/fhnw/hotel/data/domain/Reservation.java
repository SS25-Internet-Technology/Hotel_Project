package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    private BigDecimal total;

    private String paymentInfo;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationExtraService> extraServices = new ArrayList<>();;

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() { 
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ReservationExtraService> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(List<ReservationExtraService> extraServices) {
        this.extraServices = extraServices;
    }

}
