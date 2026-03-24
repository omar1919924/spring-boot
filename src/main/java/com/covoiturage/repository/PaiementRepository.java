package com.covoiturage.repository;

import com.covoiturage.entity.Paiement;
import com.covoiturage.model.PaiementStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    List<Paiement> findByPaiementStatut(PaiementStatut statut); //get reservations by statut

}

