
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
    @OneToMany(mappedBy = "conducteur")
    private List<Trajet>trajet;

    @OneToMany(mappedBy = "conducteur")
    private List<Vehicule> vehicules;
}
