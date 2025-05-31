package ch.fhnw.hotel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.hotel.data.domain.Room;
import ch.fhnw.hotel.data.enumtype.RoomType;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByRoomTypeAndSmokeAllowedAndRoomAvailability(RoomType roomType, boolean smokeAllowed, boolean roomAvailability);
}
