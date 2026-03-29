package com.covoiturage.controller;

import com.covoiturage.entity.Trajet;
import com.covoiturage.service.TrajetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {
    private TrajetService trajetService;

    public TrajetController (TrajetService trajetService){
        this.trajetService = trajetService;
    }
    @PostMapping("/processus/{ConducteurId}")
    public ResponseEntity<Trajet> proposerTrajet(@RequestBody Trajet trajet, @PathVariable Long ConducteurId){
        Trajet saved = trajetService.proposerTrajet(trajet,ConducteurId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Trajet>> rechercherTrajet(
            @RequestParam String orig,
            @RequestParam String dest,
            @RequestParam LocalDateTime date) {
        List<Trajet> results = trajetService.rechercherTrajet(orig, dest, date);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/between")
    public ResponseEntity<List<Trajet>> rechercherTrajetBetween(
            @RequestParam String orig,
            @RequestParam String dest,
            @RequestParam LocalDateTime dateMin,
            @RequestParam LocalDateTime dateMax) {
        List<Trajet> results = trajetService.rechercherTrajetBetween(orig, dest, dateMin, dateMax);
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/cancel/{trajetId}/conducteur/{conducteurId}")
    public ResponseEntity<Void> cancelTrajet(@PathVariable Long trajetId, @PathVariable Long conducteurId) {
        trajetService.cancelTrajet(trajetId, conducteurId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conducteur/{conducteurId}")
    public ResponseEntity<List<Trajet>> getMesTrajets(@PathVariable Long conducteurId) {
        List<Trajet> trajets = trajetService.getMesTrajets(conducteurId);
        return ResponseEntity.ok(trajets);
    }
}



