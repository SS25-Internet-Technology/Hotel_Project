package ch.fhnw.hotel.business.service;

import ch.fhnw.hotel.data.domain.RoomCategory;
import ch.fhnw.hotel.data.repository.RoomCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RoomCategoryService {

    @Autowired
    private RoomCategoryRepository roomCategoryRepository;

    public RoomCategory findRoomCategoryById(Long id) {
        try {
            return roomCategoryRepository.findById(id).orElseThrow(() -> 
                new RuntimeException("RoomCategory with id " + id + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving RoomCategory with id " + id, e);
        }
    }

    public List<RoomCategory> getAllRoomCategories() {
        return roomCategoryRepository.findAll();
    }

    public RoomCategory addRoomCategory(RoomCategory roomCategory) throws Exception {
        return roomCategoryRepository.save(roomCategory);
    }

    public RoomCategory updateRoomCategory(Long id, RoomCategory roomCategory) throws Exception {
        RoomCategory roomCategoryToUpdate = roomCategoryRepository.findById(id).orElseThrow(() -> 
            new Exception("RoomCategory with id " + id + " does not exist"));
        if (roomCategory.getRoomType() != null) {
            roomCategoryToUpdate.setRoomType(roomCategory.getRoomType());
        }
        roomCategoryToUpdate.setSmokeAllowed(roomCategory.isSmokeAllowed());
        roomCategoryToUpdate.setSeasonalMultiplier(roomCategory.getSeasonalMultiplier());
        return roomCategoryRepository.save(roomCategoryToUpdate);
    }

    public void deleteRoomCategory(Long id) throws Exception {
        if (roomCategoryRepository.existsById(id)) {
            roomCategoryRepository.deleteById(id);
        } else {
            throw new Exception("RoomCategory with id " + id + " does not exist");
        }
    }
}