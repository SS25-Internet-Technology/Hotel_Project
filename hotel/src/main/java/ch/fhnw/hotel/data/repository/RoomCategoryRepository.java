package ch.fhnw.hotel.data.repository;

import ch.fhnw.hotel.data.domain.RoomCategory;
import ch.fhnw.hotel.data.enumtype.RoomType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
    // Additional query methods can be defined here if needed
    Optional<RoomCategory> findByRoomTypeAndSmokeAllowed(RoomType type, boolean smokeAllowed);
}