package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.Guest;
import ch.fhnw.pizza.data.repository.GuestRepository;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest findGuestById(Long id) {
        try {
            return guestRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Guest with id " + id + " not found");
        }
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest addGuest(Guest guest) throws Exception {
        if (guest.getFullName() == null || guest.getFullName().isEmpty()) {
            throw new Exception("Invalid guest name. Name must not be empty.");
        }
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Long id, Guest guest) throws Exception {
        Guest guestToUpdate = guestRepository.findById(id).get();
        if (guestToUpdate != null) {
            if (guest.getFullName() != null && !guest.getFullName().isEmpty()) {
                guestToUpdate.setFullName(guest.getFullName());
            }
            guestToUpdate.setGender(guest.getGender());
            guestToUpdate.setDateOfBirth(guest.getDateOfBirth());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhone(guest.getPhone());
            guestToUpdate.setNationality(guest.getNationality());
            guestToUpdate.setDocumentType(guest.getDocumentType());
            guestToUpdate.setDocumentNumber(guest.getDocumentNumber());
            return guestRepository.save(guestToUpdate);
        }
        throw new Exception("Guest with id " + id + " does not exist");
    }

    public void deleteGuest(Long id) throws Exception {
        if (guestRepository.existsById(id)) {
            guestRepository.deleteById(id);
        } else {
            throw new Exception("Guest with id " + id + " does not exist");
        }
    }
}
