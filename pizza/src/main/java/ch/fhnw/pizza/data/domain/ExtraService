package ch.fhnw.pizza.data.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
        name = "reservation_extra_service", // Join table name
        joinColumns = @JoinColumn(name = "extra_service_id"), // Foreign key for the current entity
        inverseJoinColumns = @JoinColumn(name = "reservation_id") // Foreign key for the related entity
    )
    private Set<Reservation> reservations;

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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}