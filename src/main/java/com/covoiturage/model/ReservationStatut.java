package com.covoiturage.model;

public enum ReservationStatut {
    CONFIRMEE("Confirmée "), // client pay
    TERMINE("Terminé"),
    ANNULE("Annulé");

    private final String displayLabel;

    // Constructor
    ReservationStatut(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }
}
