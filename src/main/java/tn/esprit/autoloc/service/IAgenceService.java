package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence ajouter(Agence agence);

    Agence modifier(Agence agence);

    Agence recupererParId(Long id);

    List<Agence> recupererTous();

    void supprimer(Long id);
}
