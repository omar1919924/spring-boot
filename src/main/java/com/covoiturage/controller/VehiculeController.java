package com.covoiturage.controller;

import com.covoiturage.entity.Vehicule;
import com.covoiturage.service.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // POST /api/vehicules/ajouter/{conducteurId}
    @PostMapping("/ajouter/{conducteurId}")
    public ResponseEntity<Vehicule> ajouterVehicule(@PathVariable Long conducteurId, @RequestBody Vehicule vehicule) {
        Vehicule saved = vehiculeService.ajouterVehicule(conducteurId, vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // DELETE /api/vehicules/supprimer/{vehiculeId}/conducteur/{conducteurId}
    @DeleteMapping("/supprimer/{vehiculeId}/conducteur/{conducteurId}")
    public ResponseEntity<Void> supprimerVehicule(@PathVariable Long vehiculeId, @PathVariable Long conducteurId) {
        vehiculeService.supprimerVehicule(vehiculeId, conducteurId);
        return ResponseEntity.noContent().build();
    }
}
