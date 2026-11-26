package tn.esprit.autoloc.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Tâche planifiée : à la fin d'une location, le véhicule redevient DISPONIBLE
 * et la réservation passe au statut TERMINEE.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehiculeSchedulerService {

    private final IReservationRepository reservationRepository;
    private final IVehiculeRepository vehiculeRepository;

    // Tous les jours à 1h00 du matin
    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void cloturerLocationsTerminees() {
        LocalDate aujourdhui = LocalDate.now();

        // Locations en cours dont la date de fin est dépassée
        List<Reservation> terminees =
                reservationRepository.findByStatutAndDateFinBefore(StatutReservation.CONFIRMEE, aujourdhui);

        for (Reservation reservation : terminees) {
            reservation.setStatut(StatutReservation.TERMINEE);
            reservationRepository.save(reservation);

            Vehicule vehicule = reservation.getVehicule();
            // On ne touche pas à un véhicule en maintenance
            if (vehicule != null && vehicule.getStatut() == StatutVehicule.LOUE) {
                vehicule.setStatut(StatutVehicule.DISPONIBLE);
                vehiculeRepository.save(vehicule);
            }
        }
        log.info("Clôture des locations : {} réservation(s) passée(s à TERMINEE)", terminees.size());
    }
}
