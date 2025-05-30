package ch.fhnw.hotel.controller;

import ch.fhnw.hotel.business.service.GuestService;
import ch.fhnw.hotel.data.domain.Guest;
import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@RestController
@RequestMapping(path = "/hotel")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping(path = "/guests/{id}", produces = "application/json")
    public ResponseEntity getGuest(@PathVariable Long id) {
        try {
            Guest guest = guestService.findGuestById(id);
            return ResponseEntity.ok(guest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No guest found with given id");
        }
    }

    @GetMapping(path = "/guests", produces = "application/json")
    public List<Guest> getGuestList() {
        return guestService.getAllGuests();
    }

    @PostMapping(path = "/guests", consumes = "application/json", produces = "application/json")
    public ResponseEntity addGuest(@RequestBody Guest guest) {
        try {
            guest = guestService.addGuest(guest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid guest data provided");
        }
        return ResponseEntity.ok(guest);
    }

    @PutMapping(path = "/guests/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        try {
            guest = guestService.updateGuest(id, guest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No guest found with given id");
        }
        return ResponseEntity.ok(guest);
    }

    @DeleteMapping(path = "/guests/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable Long id) {
        try {
            guestService.deleteGuest(id);
            return ResponseEntity.ok("Guest with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guest not found");
        }
    }
}
