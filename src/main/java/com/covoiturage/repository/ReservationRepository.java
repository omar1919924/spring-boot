package com.covoiturage.repository;

import com.covoiturage.entity.Reservation;
import com.covoiturage.model.ReservationStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByPassagerId(Long passagerId); // reservation d un passager
    List<Reservation> findByReservationStatut(ReservationStatut statut); //get reservations by statut



}
