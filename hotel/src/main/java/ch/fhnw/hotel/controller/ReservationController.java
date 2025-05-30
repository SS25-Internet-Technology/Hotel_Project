package ch.fhnw.hotel.controller;

import ch.fhnw.hotel.business.service.ReservationService;
import ch.fhnw.hotel.dto.ReservationRequestDto;
import ch.fhnw.hotel.dto.ReservationResponseDto;

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
    public ResponseEntity<?> getReservation(@PathVariable Long id) {
        try {
            ReservationResponseDto dto = reservationService.findReservationById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reservation found with given id");
        }
    }

    @GetMapping(path="/reservations", produces = "application/json")
    public List<ReservationResponseDto> getReservationList() {
        return reservationService.getAllReservations();
    }

    @PostMapping(path="/reservations", consumes="application/json", produces = "application/json")
    public ResponseEntity<?> addReservation(@RequestBody ReservationRequestDto dto) {
        try {
            ReservationResponseDto reservation = reservationService.createReservation(dto);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(path="/reservations/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDto dto) {
        try {
            ReservationResponseDto updated = reservationService.updateReservation(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
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
