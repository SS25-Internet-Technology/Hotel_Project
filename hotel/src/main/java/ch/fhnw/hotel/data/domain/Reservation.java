package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    private double total;

    private String paymentInfo;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany
    @JoinTable(
        name = "reservation_extra_service", // 중간 테이블 이름
        joinColumns = @JoinColumn(name = "reservation_id"), // 현재 엔티티의 외래 키
        inverseJoinColumns = @JoinColumn(name = "extra_service_id") // 반대 엔티티의 외래 키
    )
    private Set<ExtraService> services;

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
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

    public Set<ExtraService> getServices() {
        return services;
    }

    public void setServices(Set<ExtraService> services) {
        this.services = services;
    }

}
