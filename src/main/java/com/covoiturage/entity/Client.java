package com.covoiturage.entity;

//missing funcs
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends User{
    protected double note;
    private LocalDateTime startBan;
    private LocalDateTime finBan;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean lifeBan = false;

    public Client(String nom, String prenom, String email, String password, String telephone,double note, LocalDateTime startBan, LocalDateTime finBan) {
        super(nom, prenom, email, password, telephone);
        this.note = note;
        this.startBan = startBan;
        this.finBan = finBan;
        this.lifeBan = false;
    }


}
