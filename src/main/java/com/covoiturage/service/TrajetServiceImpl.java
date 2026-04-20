package com.covoiturage.service;

import com.covoiturage.entity.Conducteur;
import com.covoiturage.entity.Reservation;
import com.covoiturage.entity.Trajet;
import com.covoiturage.model.TrajetStatut;
import com.covoiturage.repository.ConducteurRepository;
import com.covoiturage.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TrajetServiceImpl implements TrajetService{
    private final TrajetRepository trajetRepository;
    private final PaiementService paiementService;
    private final ConducteurRepository conducteurRepository;
    public TrajetServiceImpl (TrajetRepository trajetRepository,ConducteurRepository conducteurRepository,PaiementService paiementService){
        this.trajetRepository = trajetRepository;
        this.paiementService = paiementService;
        this.conducteurRepository = conducteurRepository;
    }

    @Override
    public Trajet proposerTrajet(Trajet t, Long conducteurId) {
        Conducteur conducteur = conducteurRepository.findById(conducteurId)
                .orElseThrow(()->new RuntimeException("conducteur not found"));
        if (trajetRepository.findById(t.getTrajetId()).isPresent()) {
            throw new RuntimeException("Trajet already exists");
        }
        t.setTrajetStatut(TrajetStatut.OUVERT);
        t.setConducteur(conducteur);
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
        if (trajet.getConducteur().getUserId()!=conducteurId) {
            throw new RuntimeException("only the same conducteur that created a trajet can cancel it ");
        }

        LocalDateTime cancellationDeadline = LocalDateTime.now().plusHours(24);
        if (cancellationDeadline.isAfter(trajet.getDateDepart())) {
            throw new RuntimeException("less than 24h can t cancel");
        }

        if (trajet.getReservations().isEmpty()) {
            trajetRepository.delete(trajet);
            return;
        }

        // Mark trip as cancelled before refund logic that requires ANNULE status.
        trajet.annuler();
        paiementService.escrowClients(trajetId);
        // penalite
        int numClient = trajet.getReservations().size();
        paiementService.transfererCVE(trajet.getPrix() * numClient * 0.2, conducteurId); //conducteur vers escrow

    }

    @Override
    public List<Trajet> getMesTrajets(Long conducteurId) {
        return trajetRepository.findByConducteurUserId(conducteurId);
    }

    @Override
    public List<Trajet> getFutureTrajt(Long conducteurId) {
        List <Trajet> trajets = getMesTrajets(conducteurId);
        for (Trajet trajet : trajets){
            LocalDateTime time = trajet.getDateDepart();
            if (time.isBefore(LocalDateTime.now())){
                trajets.remove(trajet);
            }
        }
        return trajets;
    }

    @Override
    public List<Trajet> getPastTrajet(Long conducteurId) {
        List <Trajet> trajets = getMesTrajets(conducteurId);
        for (Trajet trajet : trajets){
            LocalDateTime time = trajet.getDateDepart();
            if (time.isAfter(LocalDateTime.now())){
                trajets.remove(trajet);
            }
        }
        return trajets;
    }
}