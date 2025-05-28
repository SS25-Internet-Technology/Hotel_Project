package ch.fhnw.hotel.data.repository;

import ch.fhnw.hotel.data.domain.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
    // Additional query methods can be defined here if needed
}