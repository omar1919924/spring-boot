package com.covoiturage.repository;

import com.covoiturage.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByConducteurId(Long conducteurId);
    List<Rating> findByPassagerId(Long passagerId);
    Optional<Rating> findByRatingId(Long ratingId);
}
