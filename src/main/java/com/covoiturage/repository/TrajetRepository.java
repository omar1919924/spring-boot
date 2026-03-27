package com.covoiturage.repository;

import com.covoiturage.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {

    // Trajets d'un conducteur
    List<Trajet> findByConducteurId(Long conducteurId);
    Optional<Trajet>findByTrajetId(Long trajetId);
    //depart et destination
    List<Trajet> findByDepartAndDestination(String depart, String destination);

    //Recherche par date
    List<Trajet> findByDateDepartAfter(LocalDateTime date);
    List<Trajet> findByDateDepartBetween(LocalDateTime debut, LocalDateTime fin);

    //Places disponibles
    List<Trajet> findByPlacesLibresGreaterThan(int places);
    List<Trajet> findByDepartAndDestinationAndDateDepartBetween(
            String depart,
            String destination,
            LocalDateTime debut,
            LocalDateTime fin
    );
    //Prix
    List<Trajet> findByPrixBetween(double prixMin, double prixMax);
    List<Trajet> findByPrixLessThanEqual(double prixMax);
}
