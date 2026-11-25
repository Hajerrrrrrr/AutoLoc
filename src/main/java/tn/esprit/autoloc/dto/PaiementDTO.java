package tn.esprit.autoloc.dto;

import lombok.*;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Paiement : exposé en lecture seule, aucune contrainte de saisie nécessaire. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementDTO {

    private Long idPaiement;
    private BigDecimal montant;
    private LocalDate datePaiement;
    private ModePaiement modePaiement;
    private Long idContrat;
}
