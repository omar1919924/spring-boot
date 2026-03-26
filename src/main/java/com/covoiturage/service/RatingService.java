package com.covoiturage.service;

import com.covoiturage.entity.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> consulterRatingList(Long clientId);
    Double avgRating(Long ClientId);
    void setRating (Long ClientAId , Long ClientBId); // client A note B


}
