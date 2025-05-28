package ch.fhnw.hotel.data.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ch.fhnw.hotel.data.enumtype.RoomType;

@Entity
public class RoomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Room type: SINGLE or DOUBLE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    // Indicates if smoking is allowed in this category
    @Column(nullable = false)
    private boolean smokeAllowed;

    // Seasonal price multiplier (e.g., 1.0 for normal, 1.5 for high season)
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal seasonalMultiplier;

        // One RoomCategory has many Rooms
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
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

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isSmokeAllowed() {
        return smokeAllowed;
    }

    public void setSmokeAllowed(boolean smokeAllowed) {
        this.smokeAllowed = smokeAllowed;
    }

    public BigDecimal getSeasonalMultiplier() {
        return seasonalMultiplier;
    }

    public void setSeasonalMultiplier(BigDecimal seasonalMultiplier) {
        this.seasonalMultiplier = seasonalMultiplier;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}