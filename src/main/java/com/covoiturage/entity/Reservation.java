package com.covoiturage.entity;
import com.covoiturage.model.ReservationStatut;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateReservation;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private ReservationStatut reservationStatut;

    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Passager passager;        // ← annotation juste au-dessus

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;            // ← manquait aussi

    @OneToOne(mappedBy = "reservation")
    private Paiement paiement;



}
