package com.covoiturage.service;

import com.covoiturage.entity.*;
import com.covoiturage.model.TrajetStatut;
import com.covoiturage.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final ReservationRepository reservationRepository;
    private final RatingRepository ratingRepository;

    private final TrajetRepository trajetRepository;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             TrajetRepository trajetRepository,
                             PassagerRepository passagerRepository,
                             ConducteurRepository conducteurRepository,
                             ReservationRepository reservationRepository,
                             ClientRepository clientRepository) {
        this.ratingRepository = ratingRepository;
        this.trajetRepository = trajetRepository;
        this.reservationRepository = reservationRepository;

    }

    @Override
    public List<Rating> getNotesReçuesByConducteur(Long conducteurId) {
        return ratingRepository.findByConducteur_UserId(conducteurId);
    }

    @Override
    public List<Rating> getNotesReçuesByPassager(Long passagerId) {
        return ratingRepository.findByPassager_UserId(passagerId);
    }

    @Override
    public Double getMoyennePassager(Long passagerId) {
        List<Rating> ratings = ratingRepository.findByPassager_UserId(passagerId);
        if (ratings.isEmpty())
            return 0.0;

        double total = 0.0;
        for (Rating rating : ratings) {
            total += rating.getNote();
        }
        return total / ratings.size();
    }
    @Override
    public Double getMoyenneConducteur(Long conducteurId) {
        List<Rating> ratings = ratingRepository.findByConducteur_UserId(conducteurId);
        if (ratings.isEmpty())
            return 0.0;

        double total = 0.0;
        for (Rating rating : ratings) {
            total += rating.getNote();
        }
        return total / ratings.size();
    }

    @Override
    @Transactional
    public Rating rateConducteur(Long trajetId, Long passagerId, Double note) {
        Reservation reservation = reservationRepository.findByPassagerIdAndTrajetId(passagerId, trajetId)
                .orElseThrow(() -> new RuntimeException("the passager has no reservation in this trajet"));

        Trajet trajet = trajetRepository.findByTrajetId(trajetId)
                .orElseThrow(() -> new RuntimeException("trajet not found"));

        if (trajet.getTrajetStatut() != TrajetStatut.TERMINE) {
            throw new RuntimeException("trajet must be termine to rate");
        }

        if (note < 0 || note > 4) {
            throw new RuntimeException("note must be between 0 and 4");
        }

        Rating rating = new Rating();
        rating.setNote(note);
        rating.setPassager(reservation.getPassager());
        rating.setConducteur(trajet.getConducteur());
        rating.setTrajet(trajet);
        return ratingRepository.save(rating);
    }

    @Override
    @Transactional
    public Rating ratePassager(Long reservationId, Long conducteurId, Double note) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("reservation not found"));

        Trajet trajet = reservation.getTrajet();

        if (trajet.getTrajetStatut() != TrajetStatut.TERMINE) {
            throw new RuntimeException("trajet must be termine to rate");
        }

        if (reservation.getTrajet().getConducteur().getUserId()!=(conducteurId)) {
            throw new RuntimeException("you are not the conducteur of this trajet");
        }

        if (note < 0 || note > 4) {
            throw new RuntimeException("note must be between 0 and 4");
        }

        Rating rating = new Rating();
        rating.setNote(note);
        rating.setPassager(reservation.getPassager());
        rating.setConducteur(trajet.getConducteur());
        rating.setTrajet(trajet);
        return ratingRepository.save(rating);
    }
}