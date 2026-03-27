package com.covoiturage.repository;

import com.covoiturage.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByClientId(Long ClientId);
    Optional<Rating> findByRatingId(Long ratingId);

}
