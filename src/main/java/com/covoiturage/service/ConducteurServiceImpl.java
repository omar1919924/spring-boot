package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Paiement;
import com.covoiturage.entity.Trajet;
import com.covoiturage.entity.Vehicule;
import com.covoiturage.repository.ConducteurRepository;
import com.covoiturage.repository.UserRepository;
import com.covoiturage.repository.VehiculeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ConducteurServiceImpl implements ConducteurService {

    private final ConducteurRepository conducteurRepository;
    private final VehiculeRepository vehiculeRepository;

    public ConducteurServiceImpl(ConducteurRepository conducteurRepository, VehiculeRepository vehiculeRepository){
        this.conducteurRepository = conducteurRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Conducteur inscrireConducteur(Conducteur conducteur) {
        if(conducteurRepository.findByEmail(conducteur.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
        return conducteurRepository.save(conducteur);

    }

    @Override
    public Optional<Conducteur> chercherConducteurId(Long conducteurId) {
        return conducteurRepository.findById(conducteurId);
    }



    @Override
    @Transactional
    public Conducteur updateConducteur(Conducteur conducteur) {
        conducteurRepository.findById(conducteur.getUserId())
                .orElseThrow(() -> new RuntimeException("Conducteur not found"));
        return conducteurRepository.save(conducteur);
    }
}
