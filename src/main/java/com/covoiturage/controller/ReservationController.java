package com.covoiturage.controller;

import com.covoiturage.entity.Reservation;
import com.covoiturage.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/creer/trajet/{trajetId}/client/{clientId}")
    public ResponseEntity<Reservation> creerReservation(
            @PathVariable Long trajetId,
            @PathVariable Long clientId) {
        Reservation saved = reservationService.creerReservation(trajetId, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/annuler/{reservationId}/client/{clientId}")
    public ResponseEntity<Void> annulerReservation(
            @PathVariable Long reservationId,
            @PathVariable Long clientId) {
        reservationService.annulerReservation(reservationId, clientId);
        return ResponseEntity.noContent().build();
    }
}