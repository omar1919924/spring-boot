package com.covoiturage.repository;

import com.covoiturage.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    // toutes les notes reçues par un conducteur
    List<Rating> findByConducteur_UserId(Long conducteurId);

    // toutes les notes reçues par un passager
    List<Rating> findByPassager_UserId(Long passagerId);

    // vérifier qu'un rating n'existe pas déjà (éviter double notation)
    boolean existsByPassager_UserIdAndTrajet_TrajetId(Long passagerId, Long trajetId);
    boolean existsByConducteur_UserIdAndReservation_ReservationId(Long conducteurId, Long reservationId);
}
