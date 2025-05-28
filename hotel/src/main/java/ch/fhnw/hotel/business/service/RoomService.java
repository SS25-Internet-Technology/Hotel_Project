package ch.fhnw.hotel.business.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.hotel.data.domain.Room;
import ch.fhnw.hotel.data.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findRoomById(Long id) {
        try {
            Room room = roomRepository.findById(id).get();
            return room;
        } catch (Exception e) {
            throw new RuntimeException("Room with id " + id + " not found");
        }
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList;
    }

    public Room addRoom(Room room) throws Exception {
        if (room.getPrice() == null || room.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new Exception("Invalid room price. Price must be greater than 0.");
        }
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) throws Exception {
        Room roomToUpdate = roomRepository.findById(id).get();
        if(roomToUpdate != null) {
            if(room.getPrice() != null && room.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                roomToUpdate.setPrice(room.getPrice()); 
            }
            roomToUpdate.setRoomAvailability(room.isRoomAvailability());
            return roomRepository.save(roomToUpdate);
        }
        throw new Exception("Room with id " + id + " does not exist");
    }

    public void deleteRoom(Long id) throws Exception {
        if(roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else
            throw new Exception("Room with id " + id + " does not exist");
    }
        
}
