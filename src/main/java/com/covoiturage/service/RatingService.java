package com.covoiturage.service;

import com.covoiturage.entity.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    Rating rateConducteur(Long trajetId, Long passagerId, Double note);
    Rating ratePassager(Long reservationId, Long conducteurId, Double note);
    List<Rating> getNotesReçuesByConducteur(Long conducteurId);
    List<Rating> getNotesReçuesByPassager(Long passagerId);
    Double getMoyenneConducteur(Long conducteurId);
    Double getMoyennePassager(Long passagerId);
}

