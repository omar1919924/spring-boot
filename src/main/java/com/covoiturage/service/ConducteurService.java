package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Paiement;
import com.covoiturage.entity.Trajet;
import com.covoiturage.entity.Vehicule;

import java.util.List;
import java.util.Optional;

public interface ConducteurService {

    Conducteur inscrireConducteur(Conducteur conducteur);
    Optional<Conducteur> chercherConducteurId(Long Id);

    Conducteur updateConducteur(Conducteur conducteur);

}