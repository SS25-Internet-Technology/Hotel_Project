package ch.fhnw.hotel.data.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private int price;

    @Column(name = "room_availability")
    private boolean roomAvailability;

    /*
    @ManyToOne
    private Menu menu;
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRoomAvailability() {
        return roomAvailability;
    }
    
    public void setRoomAvailability(boolean roomAvailability) {
        this.roomAvailability = roomAvailability;
    }

    @ManyToMany(mappedBy = "rooms")
    private Set<Reservation> reservations;

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
    
}
