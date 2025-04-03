package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Room;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface RoomRepository extends JpaRepository<Room, Long> {
    /*
    Room findByRoomName(String roomName);
    List<Room> findAllByPizzaToppingsContainsIgnoreCase(String topping);
    */
}
