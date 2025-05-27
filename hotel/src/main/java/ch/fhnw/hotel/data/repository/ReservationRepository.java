package ch.fhnw.hotel.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.hotel.data.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /*
    List<Reservation> findByStartDateBetween(LocalDate start, LocalDate end);
    List<Reservation> findByRooms_RoomName(String roomName);
    */
}
