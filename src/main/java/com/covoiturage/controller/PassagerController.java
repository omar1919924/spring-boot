package com.covoiturage.controller;


import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Passager;
import com.covoiturage.service.PassagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Passager")
public class PassagerController {
    private final PassagerService passagerService;

    public PassagerController (PassagerService passagerService){
        this.passagerService =passagerService;
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<Passager> searchPassager(@PathVariable Long id){
        Passager saved = passagerService.chercherPassagerById(id);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Passager> miseAJourPassager(@PathVariable Long id,@RequestBody Passager passager){
        Passager saved = passagerService.updatePassager(id,passager);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }





}
