package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Trajet;

import java.time.LocalDateTime;

public interface TrajetService {
    Trajet proposerTrajet(Trajet t, Long ConducteurId);
    Trajet rechercherTrajet(String orig, String dest, LocalDateTime date);
    void cancelTrajet (Long trajetId , Long ConducteurId);
}
