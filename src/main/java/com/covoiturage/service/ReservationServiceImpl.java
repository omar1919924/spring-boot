package com.covoiturage.service;

import com.covoiturage.entity.Passager;
import com.covoiturage.entity.Reservation;
import com.covoiturage.entity.Trajet;
import com.covoiturage.model.ReservationStatut;
import com.covoiturage.repository.PassagerRepository;
import com.covoiturage.repository.ReservationRepository;
import com.covoiturage.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final PaiementService paiementService;
    private final ReservationRepository reservationRepository;
    private final TrajetRepository trajetRepository;
    private final PassagerRepository passagerRepository;
    public ReservationServiceImpl( PassagerRepository passagerRepository,ReservationRepository reservationRepository,TrajetRepository trajetRepository,PaiementService paiementService){
        this.reservationRepository = reservationRepository;
        this.trajetRepository = trajetRepository;
        this.paiementService = paiementService;
        this.passagerRepository = passagerRepository;
    }

    @Override
    @Transactional
    public Reservation creerReservation(Long trajetId, Long passagerId) {
        Passager passager = passagerRepository.findById(passagerId)
                .orElseThrow(()->new RuntimeException("passager not found"));

        Trajet trajet = trajetRepository.findByTrajetId(trajetId)
                .orElseThrow(()->new RuntimeException("trajet not found"));
        if(trajet.getPlaceLibre()<=0){
            throw new RuntimeException("aucune place libre");
        }
        Reservation reservation = new Reservation();
        reservation.setDateReservation(LocalDateTime.now());
        reservation.setReservationStatut(ReservationStatut.NONCONFIRMEE);
        trajet.setPlaceLibre(trajet.getPlaceLibre()-1);
        reservation.setPassager(passager);
        reservation.setTrajet(trajet);
        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public void annulerReservation(Long reservationId, Long clientId) {
        Reservation reservation = reservationRepository.findByReservationId(reservationId)
                .orElseThrow(()->new RuntimeException("reservation non existante"));
        if (reservation.getPassager().getUserId()!= clientId){
            throw new RuntimeException("seulement le passager reservant peut annuler");
        }
        if (!reservation.getReservationStatut().equals(ReservationStatut.CONFIRMEE)){
            throw new RuntimeException("La reservation n'est pas Confirmee");
        }
        Trajet trajet = reservation.getTrajet();
        //remoubresement total
        if(LocalDateTime.now().plusHours(24).isBefore(trajet.getDateDepart())){
            paiementService.escrowClient(trajet.getTrajetId(),reservation.getPaiement().getPaiementId(),1.0);
            reservation.setReservationStatut(ReservationStatut.ANNULE);
            trajet.setPlaceLibre(trajet.getPlaceLibre()+1);

        }
        // remboursement ave penalite 50%
        else if (LocalDateTime.now().plusHours(24).isAfter(trajet.getDateDepart())){
            paiementService.escrowClient(trajet.getTrajetId(),reservation.getPaiement().getPaiementId(),0.5);
            reservation.setReservationStatut(ReservationStatut.ANNULE);
            trajet.setPlaceLibre(trajet.getPlaceLibre()+1);
        }
    }



}
