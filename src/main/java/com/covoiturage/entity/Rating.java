package com.covoiturage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    protected double note;

    @ManyToOne
    @JoinColumn(name="passagerId")
    private Passager passager;

    @ManyToOne
    @JoinColumn(name="conducteurId")
    private Conducteur conducteur;

    @ManyToOne
    @JoinColumn(name="trajetId")
    private Trajet trajet;



}
