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
    private Long vehicule_id;
    private String immatriculation;
    private String marque;
    private String modele;
    private Integer nbplaces;
    @ManyToOne
    @JoinColumn (name ="conducteur_id")
    private Conducteur conducteur;

}
