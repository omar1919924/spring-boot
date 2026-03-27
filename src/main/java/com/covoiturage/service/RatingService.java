package com.covoiturage.service;

import com.covoiturage.entity.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> consulterRatingList(Long clientId);
    Double avgRating(Long ClientId);
    Rating ratePassager(Long reservationId, Long conducteurId, Double note); // Conducteur rates Passager
    Rating rateConducteur (Long trajetId , Long passagerId,Double note); // Passager rates Conducteur

}
