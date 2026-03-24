package com.covoiturage.repository;

import com.covoiturage.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByfinBanAfter(LocalDateTime now);//check if still banned
    List<Client> findByNoteGreaterThanEqual(double note); // list of client with higher marks

}
