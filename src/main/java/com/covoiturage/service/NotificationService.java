package com.covoiturage.service;

public interface NotificationService {
    void notifierEmail(Long userId,String sujet,String contenue);
}
