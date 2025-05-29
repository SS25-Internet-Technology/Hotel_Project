package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch.fhnw.hotel.data.link.ReservationExtraService;
import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE) // No setter for id, as it is a unique identifier
    private Long id;

    private BigDecimal total;

    private String paymentInfo;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationExtraService> extraServices = new ArrayList<>();;

}
