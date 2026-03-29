package com.covoiturage.service;

import com.covoiturage.entity.Client;
import com.covoiturage.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service

public class AdminCompteServiceImpl implements AdminCompteService{

    private final ClientRepository clientRepository;

    public AdminCompteServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }


    @Override
    public Client  setLifeBan(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("client not existing"));
        client.setLifeBan(true);
        return clientRepository.save(client);
    }

    @Override
    public Client  setTempBan(Long clientId , int jour) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("client not existing"));
        client.setStartBan(LocalDateTime.now());
        client.setFinBan(LocalDateTime.now().plusDays(jour));
        return clientRepository.save(client);
    }


    // force stop TempBan before due date
    @Override
    public Client endTempBan(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("client not existing"));
        if (client.getLifeBan()==true){
            throw new RuntimeException("this client have a life ban");
        }
        client.setStartBan(null);
        client.setFinBan(null);
        return clientRepository.save(client);
    }

    @Override
    public Client endLifeBan(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("client not existing"));
        client.setLifeBan(false);
        return clientRepository.save(client);
    }

    @Override
    public Client updateCilent(Client client) {
        return clientRepository.save(client);
    }
}
