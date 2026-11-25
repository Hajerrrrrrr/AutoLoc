package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratDTO {

    private Long idContrat;

    @NotNull(message = "La date de signature est obligatoire")
    private LocalDate dateSignature;

    @NotNull(message = "Le montant total est obligatoire")
    @DecimalMin(value = "0.0", message = "Le montant total ne peut pas être négatif")
    @Digits(integer = 8, fraction = 2, message = "Le montant total est invalide (8 entiers, 2 décimales max)")
    private BigDecimal montantTotal;

    private boolean valide;

    private Long idReservation;
}
