package tn.esprit.autoloc.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private Long idClient;

    @NotBlank(message = "Le nom du client est obligatoire")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom du client est obligatoire")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
    private String prenom;

    @NotBlank(message = "L'e-mail du client est obligatoire")
    @Email(message = "Le format de l'e-mail est invalide")
    @Size(max = 100, message = "L'e-mail ne doit pas dépasser 100 caractères")
    private String email;

    @Pattern(regexp = "^\\+?[0-9 ]{8,20}$", message = "Le numéro de téléphone est invalide")
    private String telephone;

    @NotBlank(message = "Le numéro de permis est obligatoire")
    @Size(max = 30, message = "Le numéro de permis ne doit pas dépasser 30 caractères")
    private String numPermis;

    @NotNull(message = "La date d'inscription est obligatoire")
    @PastOrPresent(message = "La date d'inscription ne peut pas être dans le futur")
    private LocalDate dateInscription;
}
