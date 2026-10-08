package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {

    Contrat ajouter(Contrat contrat);

    Contrat modifier(Contrat contrat);

    Contrat recupererParId(Long id);

    List<Contrat> recupererTous();

    void supprimer(Long id);
}
