package com.covoiturage.service;

import com.covoiturage.entity.Client;
import org.springframework.stereotype.Service;


@Service

public class AdminCompteServiceImpl implements AdminCompteService{
    @Override
    public void setTempBan(Long clientId, int jours) {

    }

    @Override
    public void setLifeBan(Long clientId) {

    }

    @Override
    public Client endTempBan(Long clientId) {
        return null;
    }

    @Override
    public Client endLifeBan(Long clientId) {
        return null;
    }

    @Override
    public Client updateCilent(Client client) {
        return null;
    }
}
