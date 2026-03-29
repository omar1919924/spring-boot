package com.covoiturage.controller;


import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Passager;
import com.covoiturage.service.ConducteurService;
import com.covoiturage.service.PassagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final ConducteurService conducteurService;
    private final PassagerService passagerService;


    public AuthController(PassagerService passagerService, ConducteurService conducteurService) {
        this.passagerService = passagerService;
        this.conducteurService = conducteurService;
    }
    @PostMapping("/register/passager")
    public ResponseEntity<Passager> registerPassager(@RequestBody Passager passager) {
        Passager saved = passagerService.inscrirePassager(passager);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PostMapping("/register/conducteur")
    public ResponseEntity<Conducteur> registerConducteur(@RequestBody Conducteur conducteur) {
        Conducteur saved = conducteurService.inscrireConducteur(conducteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        //Placeholder until I do the authentification with JWT later
        return ResponseEntity.ok("login endpoint ready");
    }

    static class LoginRequest {
        public String email;
        public String password;
    }
}