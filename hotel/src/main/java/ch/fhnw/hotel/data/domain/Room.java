package ch.fhnw.hotel.data.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ch.fhnw.hotel.data.enumtype.RoomType;

@Entity
@Getter
@Setter
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE) // No setter for id, as it is a unique identifier
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;

    @Column(name = "room_availability")
    private boolean roomAvailability;

    // Room type: SINGLE or DOUBLE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;

    // Indicates if smoking is allowed in this category
    @Column(nullable = false)
    private boolean smokeAllowed;

    // Seasonal price multiplier (e.g., 1.0 for normal, 1.5 for high season)
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal seasonalMultiplier;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
    
}
