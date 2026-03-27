package com.covoiturage.service;


import com.covoiturage.entity.Paiement;

public interface PaiementService {
        Paiement payer(Long reservationId);              // client → escrow
        void escrowConducteur(Long trajetId,Long paiementId,Long ratio);              // escrow → conducteur (trajet terminé)
        void escrowClients(Long trajetId);// escrow → tous les clients (annulation par conducteur)
        void escrowClient(Long trajetId,Long paiementId,Double ratio);// escrow → client (annulation par client)

        void transfererCVE(Double montant,Long clientId); //client vers escrow
        void transfererEVC(Double montant,Long clientId); //escrow vers client



}

