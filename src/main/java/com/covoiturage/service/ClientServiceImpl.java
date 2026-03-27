package com.covoiturage.service;
///for now only saving last note for client.....
import com.covoiturage.entity.Client;
import com.covoiturage.repository.ClientRepository;
import com.covoiturage.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }




}
