package tn.esprit.autoloc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class MontantCalculator {

    private MontantCalculator() {
    }

    /** Montant = tarif journalier x nombre de jours (écart entre les dates, minimum 1 jour). */
    public static BigDecimal calculer(LocalDate dateDebut, LocalDate dateFin, BigDecimal tarifJournalier) {
        long jours = Math.max(1, ChronoUnit.DAYS.between(dateDebut, dateFin));
        return tarifJournalier.multiply(BigDecimal.valueOf(jours)).setScale(2, RoundingMode.HALF_UP);
    }
}
