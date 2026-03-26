package com.covoiturage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Passager extends Client {
// not sure yet
    protected String moyenPaiement;
    @OneToMany(mappedBy = "passager")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "passager")
    private List<Rating>ratings;

}
