package com.covoiturage.repository;

import com.covoiturage.entity.Reservation;
import com.covoiturage.model.ReservationStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findByReservationId(Long reservationId);
    List<Reservation> findByPassagerUserId(Long passagerId); // reservation d un passager
    List<Reservation> findByReservationStatut(ReservationStatut statut); //get reservations by statut


    Optional<Reservation> findByPassagerUserIdAndTrajetTrajetId(Long passagerId, Long trajetId);
}
