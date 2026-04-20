package com.covoiturage.controller;


import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Passager;
import com.covoiturage.service.ConducteurService;
import com.covoiturage.service.PassagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final ConducteurService conducteurService;
    private final PassagerService passagerService;
    private final AuthenticationManager authenticationManager;

    public AuthController(PassagerService passagerService, ConducteurService conducteurService ,AuthenticationManager authenticationManager) {
        this.passagerService = passagerService;
        this.conducteurService = conducteurService;
        this.authenticationManager = authenticationManager;
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
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email, request.password
                    )
            );
            // JWT goes here later — for now just confirm success
            return ResponseEntity.ok("Connecté en tant que: " + auth.getName());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }

    static class LoginRequest {
        public String email;
        public String password;
    }
}