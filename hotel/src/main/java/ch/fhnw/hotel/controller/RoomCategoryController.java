package ch.fhnw.hotel.controller;

import ch.fhnw.hotel.business.service.RoomCategoryService;
import ch.fhnw.hotel.data.domain.RoomCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/hotel/roomcategories")
public class RoomCategoryController {

    @Autowired
    private RoomCategoryService roomCategoryService;

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getRoomCategory(@PathVariable Long id) {
        try {
            RoomCategory roomCategory = roomCategoryService.findRoomCategoryById(id);
            return ResponseEntity.ok(roomCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room category found with the given id");
        }
    }

    @GetMapping(produces = "application/json")
    public List<RoomCategory> getRoomCategoryList() {
        return roomCategoryService.getAllRoomCategories();
    }

    @PostMapping(consumes="application/json", produces = "application/json")
    public ResponseEntity addRoomCategory(@RequestBody RoomCategory roomCategory) {
        try {
            roomCategory = roomCategoryService.addRoomCategory(roomCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room Category already exists or invalid data provided");
        }
        return ResponseEntity.ok(roomCategory);
    }

    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateRoomCategory(@PathVariable Long id, @RequestBody RoomCategory roomCategory) {
        try {
            roomCategory = roomCategoryService.updateRoomCategory(id, roomCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No room category found with the given id");
        }
        return ResponseEntity.ok(roomCategory);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteRoomCategory(@PathVariable Long id) {
        try {
            roomCategoryService.deleteRoomCategory(id);
            return ResponseEntity.ok("Room Category with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room Category not found");
        }
    }
}