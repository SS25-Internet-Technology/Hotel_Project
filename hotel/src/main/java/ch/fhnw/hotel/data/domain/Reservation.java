package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    private double reservationTotal;

    private String paymentInfo;

    private LocalDate startDate;

    private LocalDate endDate;

    
    @ManyToMany
    @JoinTable(
        name = "reservation_room",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<Room> rooms;

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

    public double getReservationTotal() {
        return reservationTotal;
    }

    public void setReservationTotal(double reservationTotal) {
        this.reservationTotal = reservationTotal;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
    
    public Set<ExtraService> getServices() {
        return services;
    }

    public void setServices(Set<ExtraService> services) {
        this.services = services;
    }

}
