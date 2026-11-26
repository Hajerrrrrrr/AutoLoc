# AutoLoc – Atelier 6 : DTO, validation, gestion centralisée des erreurs

## 1. DTO
- Requêtes (entrée, avec validation) : `*RequestDTO` ; réponses (sortie) : `AgenceDTO`, `EmployeDTO`, `EquipementDTO`, `ClientDTO`, `VehiculeDTO`, `MaintenanceDTO`, `ReservationResponseDTO`, `ContratDTO`, `PaiementDTO`.
- Les relations sont exposées par identifiants (`agenceId`, `vehiculeId`, `equipementIds`, `contratId`...), plus de boucles JSON.
- Les identifiants envoyés dans le corps sont ignorés ; l'id vient de l'URL.

## 2. Validation (spring-boot-starter-validation)
@NotBlank / @NotNull / @Size / @Email / @Positive / @PositiveOrZero / @PastOrPresent / @Pattern / @Valid (paiements imbriqués). Messages en français.

## 3. Gestion des erreurs (GlobalExceptionHandler)
| Cas | Statut |
|---|---|
| Ressource introuvable | 404 |
| Validation @Valid, JSON illisible, type d'URL invalide, règle métier (IllegalArgumentException) | 400 (`erreurs` = champ -> message) |
| Conflit métier / contrainte d'intégrité | 409 |
| Méthode non supportée etc. | statut Spring conservé (405...) |
| Autre | 500 |
Corps : `{statut, message, horodatage, erreurs?}`

## 4. Services d'affectation
| Service | Endpoint |
|---|---|
| affecterReservationAClient | PUT /api/reservations/{id}/client  {nom, prenom} |
| affecterVehiculeAEquipement | PUT /api/vehicules/{idVehicule}/equipements/{idEquipement} |
| affecterMaintenanceAvehicule | POST /api/vehicules/maintenances {description, immatriculation} |
| affecterContratAReservation | POST /api/contrats/affectation {dateSignature, dateDebut, dateFin} |
| creerReservationEtContratAssocie | POST /api/reservations/avec-contrat |
Hypothèses : le client est retrouvé par nom+prénom (insensible à la casse, 409 si plusieurs) ; la maintenance démarre à la date du jour et passe le véhicule en MAINTENANCE (409 si LOUE) ; le contrat est rattaché à la réservation aux dates exactes sans contrat (404 si aucune, 409 si plusieurs ou déjà sous contrat), montant = tarif x nombre de jours (min. 1).
Les contrôleurs ne contiennent aucune logique métier.

## 5. Captures à faire
Swagger (liste des endpoints) ; un GET et un POST par entité (réponse DTO) ; un 400 avec `erreurs` ; un 404 ; un 409 ; les 5 services d'affectation ; le Collection Runner Postman (tout vert).
