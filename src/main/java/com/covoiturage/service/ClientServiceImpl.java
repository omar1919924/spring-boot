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



    @Override
    public void noterClient(Long clientAId, Long clientBId, double note) {
    Client clientA = clientRepository.findById(clientAId)
            .orElseThrow(()->new RuntimeException("Client A not found"));
    Client clientB = clientRepository.findById(clientBId)
            .orElseThrow(()->new RuntimeException("Client B not found"));
    clientB.setNote(note);
    clientRepository.save(clientB);


    }
}
