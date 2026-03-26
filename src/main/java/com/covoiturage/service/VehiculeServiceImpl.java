package com.covoiturage.service;

import com.covoiturage.entity.Vehicule;
import com.covoiturage.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

@Service

public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository){
        this.vehiculeRepository = vehiculeRepository;
    }
    @Override
    public Vehicule ajouterVehicule(Long conducteurId, Vehicule vehicule) {
        conducteurRepository.findById(conducteurId)
                .orElseThrow(()->new RuntimeException("Conducteur not found"));
        return vehiculeRepository.save(vehicule);
    }


}
