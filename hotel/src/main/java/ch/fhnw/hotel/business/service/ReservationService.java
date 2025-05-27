package ch.fhnw.hotel.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.hotel.data.domain.Reservation;
import ch.fhnw.hotel.data.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + id + " not found"));
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation) throws Exception {
        if (reservation.getReservationTotal() <= 0) {
            throw new Exception("Invalid reservation total. It must be greater than 0.");
        }
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) throws Exception {
        Optional<Reservation> existingReservationOpt = reservationRepository.findById(id);
        if (existingReservationOpt.isPresent()) {
            Reservation existingReservation = existingReservationOpt.get();
            
            if (updatedReservation.getReservationTotal() > 0) {
                existingReservation.setReservationTotal(updatedReservation.getReservationTotal());
            }
            existingReservation.setPaymentInfo(updatedReservation.getPaymentInfo());
            existingReservation.setStartDate(updatedReservation.getStartDate());
            existingReservation.setEndDate(updatedReservation.getEndDate());
            existingReservation.setRooms(updatedReservation.getRooms());
            
            return reservationRepository.save(existingReservation);
        }
        throw new Exception("Reservation with id " + id + " does not exist");
    }

    public void deleteReservation(Long id) throws Exception {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new Exception("Reservation with id " + id + " does not exist");
        }
    }
}
