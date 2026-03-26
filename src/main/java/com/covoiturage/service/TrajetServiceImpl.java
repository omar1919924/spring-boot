package com.covoiturage.service;

import com.covoiturage.entity.Trajet;
import com.covoiturage.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TrajetServiceImpl implements TrajetService{
    private final TrajetRepository trajetRepository;
    public TrajetServiceImpl (TrajetRepository trajetRepository){
        this.trajetRepository = trajetRepository;
    }

    @Override
    public Trajet proposerTrajet(Trajet t, Long ConducteurId) {
        if (trajetRepository.findById(t.getTrajetId()).isPresent()){
                throw new RuntimeException("Trajet already exists");
        }
        return trajetRepository.save(t);
    }

    @Override
    public List<Trajet> rechercherTrajet(String orig, String dest, LocalDateTime date) {
        return trajetRepository.findByDepartAndDestinationAndDateDepartBetween(
                orig,
                dest,
                date.minusHours(1),  // 1 hour before
                date.plusHours(1)    // 1 hour after
        );
    }
    @Override
    public List<Trajet> rechercherTrajetBetween(String orig, String dest, LocalDateTime dateMin, LocalDateTime dateMax) {
        return trajetRepository.findByDepartAndDestinationAndDateDepartBetween(
                orig,
                dest,
                dateMin,
                dateMax
        );
    }

    @Override
    @Transactional
    public void cancelTrajet(Long trajetId, Long conducteurId) {
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new RuntimeException("Trajet not found"));
        if (trajet.getConducteur().getUserId() != conducteurId) {
            throw new RuntimeException("only the same conducteur that created a trajet can cancel it ");
        }
        if(trajet.getReservations().isEmpty()){
            trajetRepository.delete(trajet);
        }
        if((LocalDateTime.now().plusHours(24)).isBefore(trajet.getDateDepart())){
            //transfererEVC(trajet.getPrix(),trajet.getReservations().getConducteur.getUserId())
        }
        if((LocalDateTime.now().plusHours(24)).isAfter(trajet.getDateDepart())){
            throw new RuntimeException("less than 24h can t cancel");
        }

    }

    @Override
    public List<Trajet> getMesTrajets(Long conducteurId) {
        return List.of();
    }
}
