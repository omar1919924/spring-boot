package com.covoiturage.service;

import com.covoiturage.entity.User;

import java.util.Optional;

public interface UserService {
    // inscription
    User inscrire(User u);

    //recherche
    Optional<User> searchByMail (String email);
    Optional <User> searchById(Long id);

    //update
    User update (User u);

    //delete
    void delete(User u);
}
