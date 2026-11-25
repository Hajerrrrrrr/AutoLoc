package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationWithContratRequestDTO {

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    @NotNull(message = "Le véhicule est obligatoire")
    private Long idVehicule;

    @NotNull(message = "Le client est obligatoire")
    private Long idClient;

    @NotNull(message = "La date de signature du contrat est obligatoire")
    private LocalDate dateSignature;
}
