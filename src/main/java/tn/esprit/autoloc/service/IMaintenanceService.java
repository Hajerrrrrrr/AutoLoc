package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    Maintenance ajouter(Maintenance maintenance);

    Maintenance modifier(Maintenance maintenance);

    Maintenance recupererParId(Long id);

    List<Maintenance> recupererTous();

    void supprimer(Long id);
}
