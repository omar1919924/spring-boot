package com.covoiturage.service;


import com.covoiturage.entity.Paiement;

public interface PaiementService {
        Paiement payer(Long reservationId);              // client → escrow
        void escrowConducteur(Long trajetId,Long paiementId);              // escrow → conducteur (trajet terminé)
        void escrowClient(Long trajetId,Long paiementId);// escrow → client (annulation)

        void transfererCVE(Double montant,Long clientId); //client vers escrow
        void transfererEVC(Double montant,Long clientId); //escrow vers client


}

