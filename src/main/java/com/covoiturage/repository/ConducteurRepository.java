package com.covoiturage.repository;

import com.covoiturage.entity.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {

    Optional<Conducteur> findByPermis(String permis);
    boolean existsByPermis(String permis);
}
