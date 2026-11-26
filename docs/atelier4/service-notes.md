# Notes — Couche Service (Atelier 4)

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut |
|---|---|---|
| Champ `final` possible ? | Oui | Non |
| Dépendance visible dans la signature de la classe ? | Oui (paramètre du constructeur) | Non (dépendance cachée) |
| Utilisable avec `new` hors conteneur sans dépendance `null` ? | Oui : on est obligé de fournir le repository | Non : le champ reste `null` (NullPointerException à l'appel) |
| Signalé par SonarQube for IDE ? | Non | Oui (règle S6813, à vérifier dans le panneau) |

## B. Services

| Service | Dépendances injectées | Mode | Justification |
|---|---|---|---|
| AgenceServiceImpl | IAgenceRepository | Constructeur | Champ `final`, interface, service sans état ; refuse un nom vide |
| EmployeServiceImpl | IEmployeRepository | Constructeur | Idem ; refuse un nom ou un prénom vide |
| VehiculeServiceImpl | IVehiculeRepository | Constructeur | Idem ; refuse un tarif journalier négatif |
| EquipementServiceImpl | IEquipementRepository | Constructeur | Idem ; refuse un libellé vide |
| ClientServiceImpl | IClientRepository | Constructeur | Idem ; refuse un nom ou un e-mail vide |
| ReservationServiceImpl | IReservationRepository | Constructeur | Idem ; refuse une date de fin antérieure à la date de début |
| ContratServiceImpl | IContratRepository | Constructeur | Idem ; refuse un montant total négatif |
| PaiementServiceImpl | IPaiementRepository | Constructeur | Lecture seule : `findById` et `findAll` uniquement, les écritures passent par le Contrat |
| MaintenanceServiceImpl | IMaintenanceRepository | Constructeur | Idem ; refuse une date de fin antérieure à la date de début |

## C. Messages d'erreur du conteneur

**Message 1** — `UnsatisfiedDependencyException` (cause : `NoSuchBeanDefinitionException`), bean concerné : `ContratController`, qui demande un bean `IContratService`.
- Causes probables : `@Service` oublié sur `ContratServiceImpl`, ou classe placée en dehors du package `tn.esprit.autoloc` (le scan ne la voit pas).
- Correction : ajouter `@Service` sur la classe, ou la déplacer dans le package `tn.esprit.autoloc.service`.

**Message 2** — `NoUniqueBeanDefinitionException`, bean concerné : `NotificationController`, avec deux candidats : `emailNotificateur` et `smsNotificateur`.
- Correction 1 : `@Primary` sur la **classe** du bean à privilégier (par exemple `EmailNotificateur`).
- Correction 2 : `@Qualifier("emailNotificateur")` sur le **paramètre du constructeur** du contrôleur.

**Message 3** — dépendance circulaire (`BeanCurrentlyInCreationException`) entre `clientServiceImpl` et `reservationServiceImpl`.
- Avec l'injection par constructeur, pour créer A il faut déjà avoir B, et pour créer B il faut déjà avoir A : aucun des deux ne peut être construit en premier, et il n'y a pas de contournement possible.
- Piste de conception : extraire la responsabilité commune dans un troisième service, ou faire passer l'un des deux services par un repository au lieu d'appeler l'autre service.

## D. Anomalies SonarQube for IDE

À compléter avec les anomalies réellement relevées (au moins trois).

| Règle | Explication | Correction apportée |
|---|---|---|
| S6813 (à vérifier) | Injection par attribut avec `@Autowired` (étape 2) | Retour à l'injection par constructeur avec `@RequiredArgsConstructor` et champ `final` |
| S112 (à vérifier) | Lever `RuntimeException` générique dans `findById` (étape 4) | Remplacé par `ResourceNotFoundException` |
| À compléter | À compléter | À compléter |

## E. Questions de compréhension

1. **Où est le `new` de `ContratServiceImpl` et qui l'exécute ?** Il n'est écrit nulle part dans notre code : c'est le conteneur Spring qui appelle le constructeur au démarrage, après avoir détecté `@Service` lors du scan.
2. **Pourquoi un contrôleur dépendra-t-il de `IContratService` et non de `ContratServiceImpl` ?** Pour dépendre d'une abstraction : on peut changer l'implémentation sans modifier le contrôleur, et le couplage est faible.
3. **Pourquoi un service singleton doit-il rester sans état ?** Une seule instance est partagée par tous les threads. Exemple de bug : un attribut `contratCourant` modifié par une requête puis lu par une autre ; deux utilisateurs simultanés se mélangent leurs données.
4. **Pourquoi `update` charge-t-il l'entité existante avant `save` ?** Passer directement l'objet reçu écraserait toutes les colonnes (les champs non envoyés deviendraient `null`) et pourrait toucher aux associations. On charge, on recopie les attributs modifiables, puis on sauvegarde.
5. **Différences.**
   - `@Component` se pose sur une classe qu'on écrit, détectée par le scan ; `@Bean` se pose sur une méthode d'une classe `@Configuration`, pour une classe qu'on ne peut pas annoter (bibliothèque, JDK) ou une construction particulière.
   - `@Primary` se pose sur la classe et désigne le candidat par défaut ; `@Qualifier` se pose à l'injection et choisit un bean par son nom.
   - `IPaiementService` n'expose ni création, ni modification, ni suppression, car le Paiement est composé dans le Contrat : le code métier passe par le Contrat pour créer ou retirer un paiement.
