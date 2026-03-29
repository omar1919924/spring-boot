package com.covoiturage.controller;

import com.covoiturage.entity.Client;
import com.covoiturage.service.AdminCompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminCompteService adminCompteService;

    public AdminController (AdminCompteService adminCompteService){
        this.adminCompteService = adminCompteService;
    }
    @PutMapping("/setTempBan/{id}/jours/{jours}")
    public ResponseEntity<Client> setTempBan(@PathVariable Long id,@PathVariable int jours){
        Client saved = adminCompteService.setTempBan(id,jours);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/setLifeBan/{id}")
    public ResponseEntity<Client> setLifeBan(@PathVariable Long id){
        Client saved = adminCompteService.setLifeBan(id);
        return ResponseEntity.ok(saved) ;   }
    @PutMapping("/endLifeBan/{id}")
    public ResponseEntity<Client> endLifeBan(@PathVariable Long id){
        Client saved = adminCompteService.setLifeBan(id);
        return ResponseEntity.ok(saved)  ;  }
    @PutMapping("/endTempBan/{id}")
    public ResponseEntity<Client> endTempBan(@PathVariable Long id){
        Client saved = adminCompteService.setLifeBan(id);
        return ResponseEntity.ok(saved);    }

}
