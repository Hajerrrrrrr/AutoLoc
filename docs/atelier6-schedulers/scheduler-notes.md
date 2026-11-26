# Tâches planifiées
- ContratEcheanceSchedulerService : cron "0 0 7 * * MON-FRI" -> contrats dont la réservation finit d'ici 48h (aujourd'hui, +1, +2 jours), hors ANNULEE ; résultat dans les logs.
- VehiculeSchedulerService : cron "0 0 1 * * *" -> réservations CONFIRMEE dont dateFin < aujourd'hui => TERMINEE ; véhicule LOUE => DISPONIBLE.
- @EnableScheduling dans config/SchedulingConfig.java.
Test rapide : remplacer temporairement le cron par "0/30 * * * * *" (toutes les 30 s), lire la console, puis remettre le cron.
