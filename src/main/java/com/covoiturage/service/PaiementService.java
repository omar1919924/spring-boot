package com.covoiturage.service;


    public interface PaiementService {
        void payer(Long reservationId);              // client → escrow
        void transferer(Long trajetId);              // escrow → conducteur (trajet terminé)
        void rembourser(Long reservationId);// escrow → client (annulation)
        boolean aClientPaye(Long reservationId);


    }

