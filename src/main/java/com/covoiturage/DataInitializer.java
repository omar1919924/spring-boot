package com.covoiturage;

import com.covoiturage.entity.Admin;
import com.covoiturage.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;

    public DataInitializer(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) {
        if (!adminRepository.existsByEmail("admin@covoiturage.com")) {
            Admin admin = new Admin();
            admin.setNom("Admin");
            admin.setPrenom("System");
            admin.setEmail("admin@covoiturage.com");
            admin.setPassword("admin123");
            adminRepository.save(admin);
            System.out.println("✅ Admin créé avec succès");
        } else {
            System.out.println("ℹ️ Admin existe déjà");
        }
    }
}