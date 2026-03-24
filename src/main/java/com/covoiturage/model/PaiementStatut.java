package com.covoiturage.model;


public enum PaiementStatut {
    EN_ATTENTE_PAIEMENT("En attente"), // le client n a pas encore paye
    BLOCKED("blocké"),//bloque ches la societe
    TERMINE("Terminé"),//argent envoyer au conducteur
    ANNULE("Annulé");//argent rembourse au passager

    private final String displayLabel;

    // Constructor
    PaiementStatut(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }
}
