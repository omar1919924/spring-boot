package com.covoiturage.repository;

import com.covoiturage.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByConducteurUserId(Long userId);



    Optional<Vehicule> findByImmatriculation(String immatriculation);
    List<Vehicule> findByMarque(String marque);

}
