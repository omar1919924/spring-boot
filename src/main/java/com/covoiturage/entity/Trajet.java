package com.covoiturage.entity;
import com.covoiturage.model.TrajetStatut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Trajet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected long trajet_id;
    protected String depart;

    protected String destination;
    protected LocalDateTime dateDepart;
    protected int placesTotal;
    protected int placeReserve;
    protected double prix;

    @Enumerated(EnumType.STRING)
    protected TrajetStatut trajetStatut;

    @OneToMany(mappedBy = "trajet")
    private List<Reservation> reservations;


    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private Conducteur conducteur;

}
