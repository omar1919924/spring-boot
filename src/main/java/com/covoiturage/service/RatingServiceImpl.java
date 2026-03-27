package com.covoiturage.service;

import com.covoiturage.entity.Passager;
import com.covoiturage.entity.Rating;
import com.covoiturage.entity.Reservation;
import com.covoiturage.entity.Trajet;
import com.covoiturage.model.TrajetStatut;
import com.covoiturage.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{
    private final ReservationRepository reservationRepository;
    private final PassagerRepository passagerRepository;
    private final ConducteurRepository conducteurRepository;
    private final RatingRepository ratingRepository;

    private TrajetRepository trajetRepository;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             TrajetRepository trajetRepository,
                             PassagerRepository passagerRepository,
                             ConducteurRepository conducteurRepository,
                             ReservationRepository reservationRepository){
        this.ratingRepository = ratingRepository;
        this.trajetRepository = trajetRepository;
        this.passagerRepository = passagerRepository;
        this.conducteurRepository = conducteurRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Rating rateConducteur(Long trajetId,Long passagerId,Double note) {
        Reservation reservation = reservationRepository.findByPassagerIdAndTrajetId(passagerId,trajetId)
                .orElseThrow(()->new RuntimeException("the passager has no reservation in this trajet"));
        Passager passager = passagerRepository.findById(passagerId)
                .orElseThrow(()->new RuntimeException("passager not found"));
        Trajet trajet = trajetRepository.findByTrajetId(trajetId)
                .orElseThrow(() -> new RuntimeException("trajet not found"));
        if (trajet.getTrajetStatut() != TrajetStatut.TERMINE) {
            throw new RuntimeException("trajet must be termine to rate");
        }

        if (reservation.getPassager().getUserId() != passagerId) {
            throw new RuntimeException("can't rate a conducteur that passanger wasn't with");
        }
        if (note<0 || note >4){
            throw new RuntimeException("note must be between 0 and 4");
        }
        Rating rating = new Rating();
        rating.setNote(note);
        rating.setPassager(passager);      // qui a donné la note
        rating.setConducteur(trajet.getConducteur());    // qui est noté
        rating.setTrajet(trajet);
        return ratingRepository.save(rating);


    }


    @Override
    public List<Rating> consulterRatingList(Long clientId) {
        return List.of();
    }

    @Override
    public Double avgRating(Long ClientId) {
        return 0.0;
    }

    @Override
    public Rating ratePassager(Long reservationId, Long conducteurId, Double note) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("reservation not found"));

        Trajet trajet = reservation.getTrajet();

        if (trajet.getTrajetStatut() != TrajetStatut.TERMINE) {
            throw new RuntimeException("trajet must be termine to rate");
        }

        if (reservation.getTrajet().getConducteur().getUserId() !=conducteurId) {
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
