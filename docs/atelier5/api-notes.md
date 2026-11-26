# Notes — API REST et documentation (Atelier 5)

## A. Endpoints

Le préfixe `/api` vient de `server.servlet.context-path=/api` : il n'apparaît pas dans les contrôleurs.

| Contrôleur | Verbe et route | Service appelé | Succès | Erreurs |
|---|---|---|---|---|
| AgenceController | GET `/api/agences` | `findAll()` | 200 | — |
| | GET `/api/agences/{id}` | `findById(id)` | 200 | 404 |
| | POST `/api/agences` | `create(agence)` | 201 | 400 |
| | PUT `/api/agences/{id}` | `update(id, agence)` | 200 | 400, 404 |
| | DELETE `/api/agences/{id}` | `deleteById(id)` | 204 | 404 |
| VehiculeController | GET `/api/vehicules` | `findAll()` | 200 | — |
| | GET `/api/vehicules/{id}` | `findById(id)` | 200 | 404 |
| | POST `/api/vehicules` | `create(vehicule)` | 201 | 400 |
| | PUT `/api/vehicules/{id}` | `update(id, vehicule)` | 200 | 400, 404 |
| | DELETE `/api/vehicules/{id}` | `deleteById(id)` | 204 | 404 |
| ClientController | GET `/api/clients` | `findAll()` | 200 | — |
| | GET `/api/clients/{id}` | `findById(id)` | 200 | 404 |
| | POST `/api/clients` | `create(client)` | 201 | 400 |
| | PUT `/api/clients/{id}` | `update(id, client)` | 200 | 400, 404 |
| | DELETE `/api/clients/{id}` | `deleteById(id)` | 204 | 404 |

Règles qui produisent un 400 : nom d'agence vide, tarif journalier négatif, nom ou e-mail de client vide, identifiant fourni à la création.

## B. Annotations Jackson

| Association | Annotation retenue | Justification |
|---|---|---|
| `Agence.employes`, `Agence.vehicules` | `@JsonIgnore` | Le véhicule et l'employé se créent seuls et désignent leur agence par son identifiant. Sérialiser la liste ferait boucler Agence → Véhicule → Agence. |
| `Vehicule.maintenances`, `Vehicule.reservations`, `Equipement.vehicules`, `Client.reservations`, `Reservation.contrat` | `@JsonIgnore` | Même raison : côté inverse, jamais écrit depuis le JSON du parent. |
| `Contrat.paiements` ↔ `Paiement.contrat` | à compléter à l'étape cascade (paire `@JsonManagedReference` / `@JsonBackReference`) | Composition : les paiements se créent dans le JSON du contrat. |

Différence : `@JsonIgnore` supprime la propriété dans les deux sens (lecture et écriture). `@JsonManagedReference` / `@JsonBackReference` forment une paire : la liste est sérialisée côté parent, la référence arrière est omise côté enfant, et Jackson la rétablit à la lecture du JSON, ce qui permet de créer un contrat avec ses paiements en un seul POST.

## C. Postman et Swagger

- **Postman** sert à tester : requêtes rejouables, scripts de vérification, collection exécutable. Exemple de cet atelier : `GET /api/agences/9999` avec le test `pm.response.to.have.status(404)` valide que le `GlobalExceptionHandler` traduit l'exception en 404 avec un corps `statut / message / horodatage`.
- **Swagger UI** sert à documenter et explorer : le contrat de l'API est généré à partir du code. Exemple : `@ApiResponse(responseCode = "404", ...)` sur `findById` fait apparaître cette réponse dans la page `/api/swagger-ui.html`, sans écrire de documentation séparée.

## D. Anomalies SonarQube for IDE

À compléter avec les anomalies réellement relevées sur `web.controller` (au moins deux).

| Règle | Explication | Correction apportée |
|---|---|---|
| À compléter | À compléter | À compléter |
| À compléter | À compléter | À compléter |

## E. Questions de compréhension

1. **Quel composant reçoit les requêtes ?** Le `DispatcherServlet`. Il interroge le `HandlerMapping`, qui compare le verbe HTTP et l'URL aux annotations `@GetMapping`, `@PostMapping`, etc. pour trouver la méthode du contrôleur.
2. **Pourquoi `IContratService` et non le repository ou l'implémentation ?** Le contrôleur ne contient pas de logique métier : passer par le repository contournerait les règles du service (validations). Dépendre de l'interface permet de changer l'implémentation sans toucher au contrôleur.
3. **201, 204, 404 et non 500 ?** Un POST crée une ressource (201 Created). Un DELETE n'a rien à renvoyer (204 No Content). Une ressource absente est une erreur du client (404) ; un 500 signalerait un bug du serveur. Le contrôleur ne produit pas ces codes : l'exception métier est traduite par le `GlobalExceptionHandler`.
4. **Pourquoi `PaiementController` n'a-t-il que des GET ?** Le paiement est composé dans le contrat : on le crée et on le supprime via le contrat. Un `POST /api/paiements` reçoit un 405 Method Not Allowed (la route existe, pas ce verbe).
5. **Risques de l'exposition des entités, et choix des annotations ?** L'API est couplée à la base, expose des champs internes, risque des boucles de sérialisation et laisse le client renseigner des champs qu'il ne devrait pas maîtriser (identifiant, associations). `Contrat.paiements` utilise `@JsonManagedReference` car les paiements naissent dans le JSON du contrat (composition) ; `Agence.vehicules` utilise `@JsonIgnore` car un véhicule se crée seul en désignant son agence par son identifiant.
