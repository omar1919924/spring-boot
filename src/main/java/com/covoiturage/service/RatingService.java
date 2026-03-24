package com.covoiturage.service;

public interface RatingService {
    void noterClient(Long clientAId, Long clientBId, double note);// A note B
}
