package com.covoiturage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Vehicule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long vehiculeId;
    private String immatriculation;
    private String marque;
    private String modele;
    private Integer nbPlaces;
    @ManyToOne
    @JoinColumn (name ="conducteurId")
    private Conducteur conducteur;

}
