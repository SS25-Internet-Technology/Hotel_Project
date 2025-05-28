package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "extra_service")
public class ExtraService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ExtraServiceType type;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

   @OneToMany(mappedBy = "extraService", cascade = CascadeType.ALL)
    private List<ReservationExtraService> reservationLinks = new ArrayList<>();;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public ExtraServiceType getType() {
        return type;
    }

    public void setType(ExtraServiceType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ReservationExtraService> getReservationLinks() {
        return reservationLinks;
    }

    public void setReservationLinks(List<ReservationExtraService> reservationLinks) {
        this.reservationLinks = reservationLinks;
    }

}