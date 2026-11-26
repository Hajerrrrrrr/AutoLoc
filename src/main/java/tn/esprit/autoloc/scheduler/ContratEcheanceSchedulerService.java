package tn.esprit.autoloc.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.repository.IContratRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Tâche planifiée : identifie les contrats arrivant à échéance sous 48 heures.
 * Un contrat arrive à échéance à la date de fin de sa réservation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ContratEcheanceSchedulerService {

    private final IContratRepository contratRepository;

    // Secondes Minutes Heures Jour-du-mois Mois Jour-de-la-semaine : 7h00, du lundi au vendredi
    @Scheduled(cron = "0 0 7 * * MON-FRI")
    @Transactional(readOnly = true)
    public List<Contrat> identifierContratsEcheanceSous48h() {
        LocalDate aujourdhui = LocalDate.now();
        LocalDate limite = aujourdhui.plusDays(2); // 48 heures

        List<Contrat> contrats = contratRepository
                .findByReservationDateFinBetweenAndReservationStatutNot(aujourdhui, limite, StatutReservation.ANNULEE);

        if (contrats.isEmpty()) {
            log.info("Aucun contrat n'arrive à échéance d'ici le {}", limite);
        } else {
            log.info("{} contrat(s) arrivent à échéance sous 48h :", contrats.size());
            for (Contrat c : contrats) {
                log.info(" - Contrat n°{} | fin de location le {} | montant {}",
                        c.getIdContrat(), c.getReservation().getDateFin(), c.getMontantTotal());
            }
        }
        return contrats;
    }
}
