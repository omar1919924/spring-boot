package com.covoiturage.service;

import com.covoiturage.entity.Vehicule;

public interface VehiculeService {
    Vehicule ajouterVehicule(Long conducteurId, Vehicule vehicule);
}


