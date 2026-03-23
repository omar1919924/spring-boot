package com.covoiturage.entity;

//missing funcs
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
    private LocalDateTime startban;
    private LocalDateTime finban;

    public Client(double note, LocalDateTime startban, LocalDateTime finban) {
        this.note = note;
        this.startban = startban;
        this.finban = finban;
    }


}
