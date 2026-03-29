package com.covoiturage.controller;

import com.covoiturage.entity.Rating;
import com.covoiturage.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // POST /api/ratings/conducteur/{trajetId}/passager/{passagerId}?note=4.5
    @PostMapping("/conducteur/{trajetId}/passager/{passagerId}")
    public ResponseEntity<Rating> rateConducteur(
            @PathVariable Long trajetId,
            @PathVariable Long passagerId,
            @RequestParam Double note) {
        Rating saved = ratingService.rateConducteur(trajetId, passagerId, note);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // POST /api/ratings/passager/{reservationId}/conducteur/{conducteurId}?note=4.5
    @PostMapping("/passager/{reservationId}/conducteur/{conducteurId}")
    public ResponseEntity<Rating> ratePassager(
            @PathVariable Long reservationId,
            @PathVariable Long conducteurId,
            @RequestParam Double note) {
        Rating saved = ratingService.ratePassager(reservationId, conducteurId, note);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/ratings/conducteur/{conducteurId}/notes
    @GetMapping("/conducteur/{conducteurId}/notes")
    public ResponseEntity<List<Rating>> getNotesReçuesByConducteur(@PathVariable Long conducteurId) {
        List<Rating> notes = ratingService.getNotesReçuesByConducteur(conducteurId);
        return ResponseEntity.ok(notes);
    }

    // GET /api/ratings/passager/{passagerId}/notes
    @GetMapping("/passager/{passagerId}/notes")
    public ResponseEntity<List<Rating>> getNotesReçuesByPassager(@PathVariable Long passagerId) {
        List<Rating> notes = ratingService.getNotesReçuesByPassager(passagerId);
        return ResponseEntity.ok(notes);
    }

    // GET /api/ratings/conducteur/{conducteurId}/moyenne
    @GetMapping("/conducteur/{conducteurId}/moyenne")
    public ResponseEntity<Double> getMoyenneConducteur(@PathVariable Long conducteurId) {
        Double moyenne = ratingService.getMoyenneConducteur(conducteurId);
        return ResponseEntity.ok(moyenne);
    }

    // GET /api/ratings/passager/{passagerId}/moyenne
    @GetMapping("/passager/{passagerId}/moyenne")
    public ResponseEntity<Double> getMoyennePassager(@PathVariable Long passagerId) {
        Double moyenne = ratingService.getMoyennePassager(passagerId);
        return ResponseEntity.ok(moyenne);
    }
}
