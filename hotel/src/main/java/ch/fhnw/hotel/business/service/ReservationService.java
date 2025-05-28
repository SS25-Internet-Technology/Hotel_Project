package ch.fhnw.hotel.business.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.hotel.data.domain.ExtraService;
import ch.fhnw.hotel.data.domain.Reservation;
import ch.fhnw.hotel.data.domain.Room;
import ch.fhnw.hotel.data.domain.RoomCategory;
import ch.fhnw.hotel.data.link.ReservationExtraService;
import ch.fhnw.hotel.data.repository.ExtraServiceRepository;
import ch.fhnw.hotel.data.repository.ReservationRepository;
import ch.fhnw.hotel.data.repository.RoomRepository;
import ch.fhnw.hotel.dto.ReservationRequestDto;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + id + " not found"));
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(ReservationRequestDto dto) throws Exception {
        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Reservation reservation = new Reservation();
        setReservationFields(reservation, room, dto);
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long reservationId, ReservationRequestDto dto) throws Exception {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId + " does not exist"));

        Room room = roomRepository.findById(dto.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room with id " + dto.getRoomId() + " does not exist"));

        // Remove existing ExtraService relationships
        reservation.getExtraServices().clear();

        setReservationFields(reservation, room, dto);
        return reservationRepository.save(reservation);
    }

    // Helper method to set reservation fields and calculate total
    private void setReservationFields(Reservation reservation, Room room, ReservationRequestDto dto) throws Exception {
        reservation.setRoom(room);
        reservation.setPaymentInfo(dto.getPaymentInfo());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());

        // Set extra services
        List<ReservationExtraService> extras = new ArrayList<>();
        for (Long serviceId : dto.getExtraServiceIds()) {
            ExtraService service = extraServiceRepository.findById(serviceId)
                    .orElseThrow(() -> new RuntimeException("ExtraService not found"));
            ReservationExtraService link = new ReservationExtraService(reservation, service);
            extras.add(link);
        }
        reservation.setExtraServices(extras);

        // Calculate total price based on room and extra services
        long days = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
        BigDecimal total = room.getPrice().multiply(BigDecimal.valueOf(days));
        for (ReservationExtraService extra : extras) {
            total = total.add(extra.getExtraService().getPrice());
        }

            // Apply seasonal multiplier if high season
        RoomCategory category = room.getCategory();
        if (isHighSeason(dto.getCheckInDate()) && category != null && category.getSeasonalMultiplier() != null) {
            total = total.multiply(category.getSeasonalMultiplier());
        }

        reservation.setTotal(total);
    }

    private boolean isHighSeason(LocalDate checkInDate) {
        // Example: July and August are high season
        int month = checkInDate.getMonthValue();
        return (month == 7 || month == 8);
    }

    public void deleteReservation(Long id) throws Exception {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new Exception("Reservation with id " + id + " does not exist");
        }
    }
}
