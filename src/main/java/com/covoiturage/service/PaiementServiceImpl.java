package com.covoiturage.service;

import com.covoiturage.entity.Paiement;
import com.covoiturage.entity.Reservation;
import com.covoiturage.entity.Trajet;
import com.covoiturage.model.PaiementStatut;
import com.covoiturage.model.ReservationStatut;
import com.covoiturage.model.TrajetStatut;
import com.covoiturage.repository.PaiementRepository;
import com.covoiturage.repository.ReservationRepository;
import com.covoiturage.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaiementServiceImpl implements PaiementService{
    private final PaiementRepository paiementRepository;
    private final ReservationRepository reservationRepository;
    private final TrajetRepository trajetRepository;

    public PaiementServiceImpl (PaiementRepository paiementRepository, ReservationRepository reservationRepository, TrajetRepository trajetRepository){
        this.paiementRepository = paiementRepository;
        this.reservationRepository = reservationRepository;
        this.trajetRepository = trajetRepository;
    }


    @Override
    @Transactional
    public Paiement payer(Long reservationId) {  // escrow
        Reservation res = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation non trouvée"));
        if (!res.getReservationStatut().equals(ReservationStatut.NONCONFIRMEE)){
            throw new RuntimeException("Reservation n'est pas en attente de payement");
        }

        Paiement p = new Paiement();
        p.setMontant(res.getMontant());
        p.setPaiementStatut(PaiementStatut.BLOCKED);
        p.setReservation(res);
        res.setReservationStatut(ReservationStatut.CONFIRMEE);
        transfererCVE(p.getMontant(),p.getReservation().getPassager().getUserId());

        return paiementRepository.save(p);

    }

    @Override
    @Transactional
    public void escrowConducteur(Long trajetId,Long paiementId) {
        Trajet t = trajetRepository.findById(trajetId)
                .orElseThrow(()->new RuntimeException("trajet non touvée"));
        if (!t.getTrajetStatut().equals(TrajetStatut.COMPLET)){
            throw new RuntimeException("Trajet doit etre termine pour le transfer");
        }
        Paiement p = paiementRepository.findById(paiementId)
                .orElseThrow(()->new RuntimeException("paiement not initialized"));
        transfererCVE(p.getMontant(),p.getReservation().getTrajet().getConducteur().getUserId());


    }

    @Override
    public void escrowClient(Long trajetId, Long paiementId) {
        Trajet t = trajetRepository.findById(trajetId)
                .orElseThrow(()->new RuntimeException("trajet non touvée"));
        if (!t.getTrajetStatut().equals(TrajetStatut.ANNULE)){
            throw new RuntimeException("Remboursement ssi Trajet annule par conducteur");
        }
        Paiement p = paiementRepository.findById(paiementId)
                .orElseThrow(()->new RuntimeException("paiement not initialized"));
        transfererEVC(p.getMontant(),p.getReservation().getTrajet().getConducteur().getUserId());
    }
    // SIMULATION PAYEMENT
    @Override
    public void transfererCVE(Double montant, Long clientId) {
        System.out.println("Virement de " + montant + " client --> escrow " + clientId);

    }

    @Override
    public void transfererEVC(Double montant, Long clientId) {
        System.out.println("Virement de " + montant + " DT de escrow --> client " + clientId);

    }


}
