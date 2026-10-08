package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe ajouter(Employe employe);

    Employe modifier(Employe employe);

    Employe recupererParId(Long id);

    List<Employe> recupererTous();

    void supprimer(Long id);
}
