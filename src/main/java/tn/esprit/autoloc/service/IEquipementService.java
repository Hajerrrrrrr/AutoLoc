package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {

    Equipement ajouter(Equipement equipement);

    Equipement modifier(Equipement equipement);

    Equipement recupererParId(Long id);

    List<Equipement> recupererTous();

    void supprimer(Long id);
}
