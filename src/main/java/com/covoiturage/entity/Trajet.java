package com.covoiturage.entity;
import com.covoiturage.model.TrajetStatut;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    protected long trajetId;
    protected String depart;

    protected String destination;
    protected LocalDateTime dateDepart;
    protected int placesTotal;
    protected int placeLibre;
    protected double prix;

    @Enumerated(EnumType.STRING)
    protected TrajetStatut trajetStatut;

    @JsonIgnore
    @OneToMany(mappedBy = "trajet")
    private List<Reservation> reservations;


    @ManyToOne
    @JoinColumn(name = "conducteurId")
    private Conducteur conducteur;

    @JsonIgnore
    @OneToMany(mappedBy = "trajet")
    private List<Rating> ratings;

}
