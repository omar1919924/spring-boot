package com.covoiturage.service;

import com.covoiturage.entity.Passager;
import java.util.Optional;

public interface PassagerService {

    Passager inscrirePassager(Passager passager);
    Passager chercherPassagerById(Long id);
    Passager updatePassager(Long id,Passager passager);

}