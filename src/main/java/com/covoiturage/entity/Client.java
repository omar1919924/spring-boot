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
    private LocalDateTime startBan;
    private LocalDateTime finBan;

    public Client(double note, LocalDateTime startBan, LocalDateTime finBan) {
        this.note = note;
        this.startBan = startBan;
        this.finBan = finBan;
    }


}
