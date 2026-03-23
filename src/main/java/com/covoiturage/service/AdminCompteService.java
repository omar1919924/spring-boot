package com.covoiturage.service;

import com.covoiturage.entity.Client;

public interface AdminCompteService {
    //ban temporaire
    void setTempBan(Long clientId,int jours);
    //ban for life
    void setLifeBan(Long clientId);
    //end temp ban
    Client endTempBan(Long clientId);
    //end life ban
    Client endLifeBan(Long clientId);
    //update
    Client updateCilent(Client client);
}
