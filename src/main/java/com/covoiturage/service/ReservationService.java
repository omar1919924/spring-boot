package com.covoiturage.service;

import com.covoiturage.entity.Reservation;

public interface ReservationService {
    Reservation creerReservation(Long trajetId,Long clientId);
    void annulerReservation(Long reservationId,Long clientId);

}
