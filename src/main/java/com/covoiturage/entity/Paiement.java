package com.covoiturage.entity;
import com.covoiturage.model.Paiement_statut;



import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long paiement_id;
    protected double montant; //might be useless
    @Enumerated(EnumType.STRING)
    private Paiement_statut paiement_statut;
    protected LocalDateTime date_paiement;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;







}

