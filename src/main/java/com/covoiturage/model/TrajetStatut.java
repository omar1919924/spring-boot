package com.covoiturage.model;

public enum TrajetStatut {
    OUVERT("En attente"), //still places
    COMPLET("Complet"), //full
    ANNULE("Annulé"), //canceled
    TERMINE("Terminé");//got to destination


    private final String displayLabel;

    // Constructor
    TrajetStatut(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }
}
