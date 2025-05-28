package ch.fhnw.hotel.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.hotel.data.domain.ExtraService;
import ch.fhnw.hotel.data.domain.Reservation;
import ch.fhnw.hotel.data.domain.ReservationExtraService;
import ch.fhnw.hotel.data.domain.Room;
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
        reservation.setRoom(room);
        reservation.setPaymentInfo(dto.getPaymentInfo());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());

        List<ReservationExtraService> extras = new ArrayList<>();
        for (Long serviceId : dto.getExtraServiceIds()) {
            ExtraService service = extraServiceRepository.findById(serviceId)
                    .orElseThrow(() -> new RuntimeException("ExtraService not found"));
            ReservationExtraService link = new ReservationExtraService(reservation, service);
            extras.add(link);
        }        
        reservation.setExtraServices(extras);

        //total is calculated based on the room and extra services
        long days = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
        BigDecimal total = room.getPrice().multiply(BigDecimal.valueOf(days));

        for (ReservationExtraService extra : extras) {
            total = total.add(extra.getExtraService().getPrice());
        }
        reservation.setTotal(total);

        return reservationRepository.save(reservation);
    }


    public Reservation updateReservation(Long reservationId, ReservationRequestDto dto) throws Exception {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId + " does not exist"));
         
        Room room = roomRepository.findById(dto.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room with id " + dto.getRoomId() + " does not exist"));
        
        reservation.setRoom(room);
        reservation.setPaymentInfo(dto.getPaymentInfo());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());

        // Remove existing ExtraService relationship
       reservation.getExtraServices().clear();

       // New settings
       List<ReservationExtraService> updatedExtras = new ArrayList<>();
       for (Long serviceId : dto.getExtraServiceIds()) {
            ExtraService service = extraServiceRepository.findById(serviceId)
                    .orElseThrow(() -> new RuntimeException("ExtraService not found"));
            ReservationExtraService link = new ReservationExtraService(reservation, service);
            updatedExtras.add(link);
        }
        reservation.setExtraServices(updatedExtras);

        //total is calculated based on the room and extra services
        long days = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
        BigDecimal updatedTotal = room.getPrice().multiply(BigDecimal.valueOf(days));

        for (ReservationExtraService extra : updatedExtras) {
            updatedTotal = updatedTotal.add(extra.getExtraService().getPrice());
        }
        reservation.setTotal(updatedTotal);

        return reservationRepository.save(reservation);

    }

    public void deleteReservation(Long id) throws Exception {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new Exception("Reservation with id " + id + " does not exist");
        }
    }
}
