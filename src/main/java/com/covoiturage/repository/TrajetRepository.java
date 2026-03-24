package com.covoiturage.repository;

import com.covoiturage.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {

    // Trajets d'un conducteur
    List<Trajet> findByConducteurId(Long conducteurId);

    //depart et destination
    List<Trajet> findByDepartAndDestination(String depart, String destination);

    //Recherche par date
    List<Trajet> findByDateDepartAfter(LocalDateTime date);
    List<Trajet> findByDateDepartBetween(LocalDateTime debut, LocalDateTime fin);

    //Places disponibles
    List<Trajet> findByPlacesLibresGreaterThan(int places);

    //Prix
    List<Trajet> findByPrixBetween(double prixMin, double prixMax);
    List<Trajet> findByPrixLessThanEqual(double prixMax);
}
