package ch.fhnw.hotel.controller;

import ch.fhnw.hotel.business.service.RoomService;
import ch.fhnw.hotel.data.domain.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/hotel")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(path="/rooms/{id}", produces = "application/json")
    public ResponseEntity getRoom(@PathVariable Long id) {
        try{
            Room room = roomService.findRoomById(id);
            return ResponseEntity.ok(room);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No room found with given id");
        }
    }

    @GetMapping(path="/rooms", produces = "application/json")
    public List<Room> getRoomList() {
        List<Room> roomList = roomService.getAllRooms();

        return roomList;
    }

    @PostMapping(path="/rooms", consumes="application/json", produces = "application/json")
    public ResponseEntity addRoom(@RequestBody Room room) {
        try{
            room = roomService.addRoom(room);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room already exists or invalid data provided");
        }
        return ResponseEntity.ok(room);
        
    }

    @PutMapping(path="/rooms/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateRoom(@PathVariable Long id, @RequestBody Room room) {
        try{
            room = roomService.updateRoom(id, room);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No room found with given id");

        }
        return ResponseEntity.ok(room);
        
    }

    @DeleteMapping(path="/rooms/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        try{
            roomService.deleteRoom(id);
            return ResponseEntity.ok("Room with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }
/*
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<Menu> getMenu(@RequestParam String location) {
        Menu menu = menuService.getMenuByLocation(location);
        return ResponseEntity.ok(menu);      
    }
*/    
}
