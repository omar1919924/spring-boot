package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Paiement;
import com.covoiturage.entity.Trajet;
import com.covoiturage.entity.Vehicule;
import com.covoiturage.model.Role;
import com.covoiturage.repository.ConducteurRepository;
import com.covoiturage.repository.UserRepository;
import com.covoiturage.repository.VehiculeRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ConducteurServiceImpl implements ConducteurService {

    private final ConducteurRepository conducteurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final PasswordEncoder passwordEncoder;
    public ConducteurServiceImpl(ConducteurRepository conducteurRepository,PasswordEncoder passwordEncoder, VehiculeRepository vehiculeRepository){
        this.conducteurRepository = conducteurRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Conducteur inscrireConducteur(Conducteur conducteur) {
        if(conducteurRepository.findByEmail(conducteur.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
        conducteur.setPassword(passwordEncoder.encode(conducteur.getPassword()));
        conducteur.setRole(Role.CONDUCTEUR); //
        return conducteurRepository.save(conducteur);

    }

    @Override
    public Conducteur chercherConducteurId(Long conducteurId) {
        return conducteurRepository.findById(conducteurId)
                .orElseThrow(()->new RuntimeException("Conducteur not found"));
    }



    @Override
    @Transactional
    public Conducteur updateConducteur(Long id, Conducteur conducteur) {
        Conducteur existing = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur not found: " + id));
        existing.setNom(conducteur.getNom());
        existing.setPrenom(conducteur.getPrenom());
        return conducteurRepository.save(existing);
    }
}
