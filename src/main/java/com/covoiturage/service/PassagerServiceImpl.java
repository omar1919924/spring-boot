package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Passager;
import com.covoiturage.repository.PassagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassagerServiceImpl implements PassagerService {

    private final PassagerRepository passagerRepository;

    public PassagerServiceImpl(PassagerRepository passagerRepository) {
        this.passagerRepository = passagerRepository;
    }

    @Override
    public Passager inscrirePassager(Passager passager) {
        if (passagerRepository.findByEmail(passager.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        return passagerRepository.save(passager);
    }

    @Override
    public Passager chercherPassagerById(Long id) {
        return passagerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Passager not found"));
    }

    @Override
    @Transactional
    public Passager updatePassager(Long id, Passager passager) {
        Passager existing = passagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passager not found" + id));
        existing.setNom(passager.getNom());
        existing.setPrenom(passager.getPrenom());
        return passagerRepository.save(existing);
    }
}

