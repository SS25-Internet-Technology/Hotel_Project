package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation_extra_service")
public class ReservationExtraService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "extra_service_id")
    private ExtraService extraService;

    // Constructors
    public ReservationExtraService() {}

    public ReservationExtraService(Reservation reservation, ExtraService extraService) {
        this.reservation = reservation;
        this.extraService = extraService;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ExtraService getExtraService() {
        return extraService;
    }

    public void setExtraService(ExtraService extraService) {
        this.extraService = extraService;
    }

}
