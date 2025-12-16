package com.example.hotel.service;

import com.example.hotel.model.Reservation;
import com.example.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setClient(reservationDetails.getClient());
            reservation.setChambre(reservationDetails.getChambre());
            reservation.setDateDebut(reservationDetails.getDateDebut());
            reservation.setDateFin(reservationDetails.getDateFin());
            reservation.setPreferences(reservationDetails.getPreferences());
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
