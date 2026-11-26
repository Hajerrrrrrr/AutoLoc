package tn.esprit.autoloc.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Règle de calcul du montant d'un contrat : tarif journalier x nombre de jours (minimum 1). */
public final class TarifUtils {

    private TarifUtils() {
    }

    public static BigDecimal calculerMontant(BigDecimal tarifJournalier, LocalDate dateDebut, LocalDate dateFin) {
        long jours = Math.max(1, ChronoUnit.DAYS.between(dateDebut, dateFin));
        return tarifJournalier.multiply(BigDecimal.valueOf(jours));
    }
}
