package com.covoiturage.entity;
import com.covoiturage.model.PaiementStatut;



import jakarta.persistence.*;
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
    protected Long paiementId;
    protected double montant; //might be useless
    @Enumerated(EnumType.STRING)
    private PaiementStatut paiementStatut;
    protected LocalDateTime datePaiement;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;







}

