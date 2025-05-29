package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.fhnw.hotel.data.enumtype.RoomType;
import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Getter
@Setter
@Table(name = "room_category")
public class RoomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE) // No setter for id, as it is a unique identifier
    private Long id;

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

        // One RoomCategory has many Rooms
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Prevent infinite recursion
    private List<Room> rooms = new ArrayList<>();

    // --- Convenience method to manage bi-directional relationship ---
    public void addRoom(Room room) {
        rooms.add(room);
        room.setCategory(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setCategory(null);
    }
}