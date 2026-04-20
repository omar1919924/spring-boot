package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Trajet;

import java.time.LocalDateTime;
import java.util.List;

public interface TrajetService {
    Trajet proposerTrajet(Trajet t, Long ConducteurId);
    List<Trajet> rechercherTrajet(String orig, String dest, LocalDateTime date);
    List<Trajet>rechercherTrajetBetween(String orig, String dest, LocalDateTime dateMin, LocalDateTime dateMax);
    void cancelTrajet (Long trajetId , Long ConducteurId);
    List<Trajet> getMesTrajets(Long conducteurId);
    List<Trajet> getFutureTrajt(Long conducteurId);
    List<Trajet>getPastTrajet(Long conducteurId);
}
