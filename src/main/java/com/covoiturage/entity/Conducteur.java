
//not finished
package com.covoiturage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Conducteur extends Client {
    private String permis;
    @OneToMany(mappedBy = "conducteur")
    private List<Trajet>trajets;

    @OneToMany(mappedBy = "conducteur")
    private List<Vehicule> vehicules;

    @OneToMany(mappedBy = "conducteur")
    private List<Rating> notesRecues;

    @OneToMany(mappedBy = "passager")
    private List<Rating> notesDonnees;
}
