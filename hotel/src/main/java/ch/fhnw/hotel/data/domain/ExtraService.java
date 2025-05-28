package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ch.fhnw.hotel.data.enumtype.ExtraServiceType;
import ch.fhnw.hotel.data.link.ReservationExtraService;

@Entity
@Getter
@Setter
@Table(name = "extra_service")
public class ExtraService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE) // No setter for id, as it is a unique identifier
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ExtraServiceType type;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

   @OneToMany(mappedBy = "extraService", cascade = CascadeType.ALL)
    private List<ReservationExtraService> reservationLinks = new ArrayList<>();;

}