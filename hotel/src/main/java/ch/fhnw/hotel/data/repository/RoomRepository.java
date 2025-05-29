package ch.fhnw.hotel.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.hotel.data.domain.Room;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);
}
