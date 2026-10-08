package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    Paiement ajouter(Paiement paiement);

    Paiement modifier(Paiement paiement);

    Paiement recupererParId(Long id);

    List<Paiement> recupererTous();

    void supprimer(Long id);
}
