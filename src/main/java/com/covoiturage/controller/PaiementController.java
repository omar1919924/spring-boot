package com.covoiturage.controller;

import com.covoiturage.entity.Paiement;
import com.covoiturage.service.PaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping("/payer/{reservationId}")
    public ResponseEntity<Paiement> payer(@PathVariable Long reservationId) {
        Paiement saved = paiementService.payer(reservationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/escrow/conducteur/{trajetId}/{paiementId}/{ratio}")
    public ResponseEntity<Void> escrowConducteur(
            @PathVariable Long trajetId,
            @PathVariable Long paiementId,
            @PathVariable Long ratio) {
        paiementService.escrowConducteur(trajetId, paiementId, ratio);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/escrow/clients/{trajetId}")
    public ResponseEntity<Void> escrowClients(@PathVariable Long trajetId) {
        paiementService.escrowClients(trajetId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/escrow/client/{trajetId}/{paiementId}/{ratio}")
    public ResponseEntity<Void> escrowClient(
            @PathVariable Long trajetId,
            @PathVariable Long paiementId,
            @PathVariable Double ratio) {
        paiementService.escrowClient(trajetId, paiementId, ratio);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transfert/cve/{clientId}")
    public ResponseEntity<Void> transfererCVE(
            @RequestParam Double montant,
            @PathVariable Long clientId) {
        paiementService.transfererCVE(montant, clientId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/transfert/evc/{clientId}")
    public ResponseEntity<Void> transfererEVC(@RequestParam Double montant, @PathVariable Long clientId) {
        paiementService.transfererEVC(montant, clientId);
        return ResponseEntity.noContent().build();
    }
}