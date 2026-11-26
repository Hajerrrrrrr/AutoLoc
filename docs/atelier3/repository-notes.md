# Notes — Couche Repository (Atelier 3)

## 1. Choix de l'interface

Les neuf repositories étendent `JpaRepository<Entité, Long>` : elle cumule CRUD, listes (`List` au lieu de `Iterable`), tri, pagination et méthodes JPA (`flush`, `saveAndFlush`). Spring Data génère le proxy au démarrage, donc aucune classe d'implémentation ni `@Repository` n'est nécessaire.

| Interface | Étend | Justification |
|---|---|---|
| IAgenceRepository | `JpaRepository<Agence, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IEmployeRepository | `JpaRepository<Employe, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IVehiculeRepository | `JpaRepository<Vehicule, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IEquipementRepository | `JpaRepository<Equipement, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IClientRepository | `JpaRepository<Client, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IReservationRepository | `JpaRepository<Reservation, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IContratRepository | `JpaRepository<Contrat, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IPaiementRepository | `JpaRepository<Paiement, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |
| IMaintenanceRepository | `JpaRepository<Maintenance, Long>` | CRUD complet, `findAll` renvoie une `List`, `saveAndFlush` disponible, tri et pagination inclus. |

Méthodes de recherche ajoutées (générées à partir du nom de la méthode) :
- `IVehiculeRepository` : `findByStatut`, `findByImmatriculation`
- `IClientRepository` : `findByEmail`

## 2. Comportement de `save()` observé dans les logs SQL

| Situation | Opération | SQL observé |
|---|---|---|
| Identifiant `null` | persist | `insert into ...` |
| Identifiant renseigné | merge | `select ...` puis `update ...` si des champs ont changé |

`deleteById` ne lève aucune exception si l'identifiant n'existe pas.

## 3. Anomalies SonarQube for IDE

À compléter avec les anomalies réellement relevées par l'analyse de ton projet.

| Anomalie SonarQube for IDE | Règle / explication | Correction apportée |
|---|---|---|
| Imports inutilisés (ex. `lombok.*` ou `java.util.List` non utilisés) | Code mort : alourdit la lecture | Imports supprimés |
| `System.out.println` dans une classe | Utiliser un logger plutôt que la sortie standard | Remplacé par `@Slf4j` et `log.info(...)` |
| À compléter | À compléter | À compléter |
