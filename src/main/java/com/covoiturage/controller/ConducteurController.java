package com.covoiturage.controller;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.service.ConducteurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/conducteur")
public class ConducteurController {
    private final ConducteurService conducteurService;

    public ConducteurController (ConducteurService conducteurService){
        this.conducteurService = conducteurService;
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<Conducteur> searchConducteur(@PathVariable   Long id){
    Conducteur saved = conducteurService.chercherConducteurId(id);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Conducteur> miseAJourConducteur(@PathVariable Long id,@RequestBody Conducteur conducteur){
        Conducteur saved = conducteurService.updateConducteur(id,conducteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
