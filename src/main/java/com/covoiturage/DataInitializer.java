package com.covoiturage;

import com.covoiturage.entity.Admin;
import com.covoiturage.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!adminRepository.existsByEmail("admin@covoiturage.com")) {
            Admin admin = new Admin();
            admin.setNom("Admin");
            admin.setPrenom("System");
            admin.setEmail("admin@covoiturage.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // ✅ encoded
            adminRepository.save(admin);
            System.out.println("✅ Admin créé avec succès");
        } else {
            System.out.println("ℹ️ Admin existe déjà");
        }
    }
}