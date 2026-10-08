package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {

    Vehicule ajouter(Vehicule vehicule);

    Vehicule modifier(Long id, Vehicule vehicule);

    Vehicule recupererParId(Long id);

    List<Vehicule> recupererTous();

    List<Vehicule> recupererParStatut(StatutVehicule statut);

    Vehicule recupererParImmatriculation(String immatriculation);

    void supprimer(Long id);
}
