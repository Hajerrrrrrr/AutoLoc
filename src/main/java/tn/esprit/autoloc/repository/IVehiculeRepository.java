package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByStatut(StatutVehicule statut);

    Optional<Vehicule> findByImmatriculation(String immatriculation);
}
