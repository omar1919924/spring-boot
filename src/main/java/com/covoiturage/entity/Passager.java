package com.covoiturage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(mappedBy = "passager")
    private List<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy = "passager")
    private List<Rating> notesRecus;    // conducteur note le passager

    @JsonIgnore
    @OneToMany(mappedBy = "conducteur")
    private List<Rating> notesDonnes;

}
