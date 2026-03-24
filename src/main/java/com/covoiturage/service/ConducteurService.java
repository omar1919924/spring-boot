package com.covoiturage.service;

import com.covoiturage.entity.Paiement;
import com.covoiturage.entity.Trajet;
import com.covoiturage.entity.Vehicule;

import java.util.List;

public interface ConducteurService {

    Vehicule ajouterVehicule(Long conducteurId, Vehicule vehicule);

    List<Trajet> getMesTrajets(Long conducteurId);

    List<Paiement> getMesGains(Long conducteurId);

    void noterPassager(Long conducteurId, Long passagerId, double note);

}