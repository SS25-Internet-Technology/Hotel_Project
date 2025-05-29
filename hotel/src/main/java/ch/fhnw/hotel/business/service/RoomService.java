package ch.fhnw.hotel.business.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.hotel.data.domain.Room;
import ch.fhnw.hotel.data.domain.RoomCategory;
import ch.fhnw.hotel.data.repository.RoomCategoryRepository;
import ch.fhnw.hotel.data.repository.RoomRepository;
import ch.fhnw.hotel.dto.RoomRequestDto;
import ch.fhnw.hotel.dto.RoomResponseDto;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomCategoryRepository roomCategoryRepository;

    public RoomResponseDto findRoomById(Long id) {
        try {
            Room room = roomRepository.findById(id).get();
            return new RoomResponseDto(room);
        } catch (Exception e) {
            throw new RuntimeException("Room with id " + id + " not found");
        }
    }

    public List<RoomResponseDto> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream()
            .map(RoomResponseDto::new)
            .toList();
    }

    public RoomResponseDto addRoom(RoomRequestDto dto) throws Exception {
        Room room = new Room();
        validateAndSetRoomFields(room, dto);
        roomRepository.save(room);
        return new RoomResponseDto(room);
    }

    public RoomResponseDto updateRoom(Long id, RoomRequestDto dto) throws Exception {
        Room roomToUpdate = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Room with id " + id + " does not exist"));
        validateAndSetRoomFields(roomToUpdate, dto);
        roomRepository.save(roomToUpdate);
        return new RoomResponseDto(roomToUpdate);
    }
    
    private void validateAndSetRoomFields(Room room, RoomRequestDto dto) throws Exception {
        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Invalid room price. Price must be greater than 0.");
        }
        room.setPrice(dto.getPrice());
        room.setRoomAvailability(dto.isRoomAvailability());
        if (dto.getRoomNumber() == null || dto.getRoomNumber().isEmpty()) {
            throw new Exception("Room number cannot be null or empty.");
        }
        room.setRoomNumber(dto.getRoomNumber());

        RoomCategory roomCategory = roomCategoryRepository.findByRoomTypeAndSmokeAllowed(
            dto.getRoomType(), dto.isSmokeAllowed()
        ).orElseThrow(() -> new RuntimeException("Room category not found"));
        room.setCategory(roomCategory);
    }

    public void deleteRoom(Long id) throws Exception {
        if(roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else
            throw new Exception("Room with id " + id + " does not exist");
    }
        
}
