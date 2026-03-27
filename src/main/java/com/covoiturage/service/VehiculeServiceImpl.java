package com.covoiturage.service;

import com.covoiturage.entity.Vehicule;
import com.covoiturage.repository.ConducteurRepository;
import com.covoiturage.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

@Service

public class VehiculeServiceImpl implements VehiculeService {
    private final ConducteurRepository conducteurRepository;
    private final VehiculeRepository vehiculeRepository;
    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository,ConducteurRepository conducteurRepository){

        this.vehiculeRepository = vehiculeRepository;
        this.conducteurRepository = conducteurRepository;
    }
    @Override
    public Vehicule ajouterVehicule(Long conducteurId, Vehicule vehicule) {
        conducteurRepository.findById(conducteurId)
                .orElseThrow(()->new RuntimeException("Conducteur not found"));
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void supprimerVehicule(Long vehiculeId,Long conducteurId) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(()->new RuntimeException("vehicule not found"));
        if (vehicule.getConducteur().getUserId() != conducteurId ){
            throw new RuntimeException("only the owner can delete his vehicule");
        }

        vehiculeRepository.delete(vehicule);
    }


}
