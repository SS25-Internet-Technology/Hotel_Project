package ch.fhnw.pizza.controller;

import ch.fhnw.pizza.business.service.ReservationService;
import ch.fhnw.pizza.data.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/hotel")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path="/reservations/{id}", produces = "application/json")
    public ResponseEntity getReservation(@PathVariable Long id) {
        try {
            Reservation reservation = reservationService.findReservationById(id);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservation found with given id");
        }
    }

    @GetMapping(path="/reservations", produces = "application/json")
    public List<Reservation> getReservationList() {
        return reservationService.getAllReservations();
    }

    @PostMapping(path="/reservations", consumes="application/json", produces = "application/json")
    public ResponseEntity addReservation(@RequestBody Reservation reservation) {
        try {
            reservation = reservationService.addReservation(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid reservation data provided");
        }
        return ResponseEntity.ok(reservation);
    }

    @PutMapping(path="/reservations/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        try {
            reservation = reservationService.updateReservation(id, reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No reservation found with given id");
        }
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping(path="/reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found");
        }
    }
}
