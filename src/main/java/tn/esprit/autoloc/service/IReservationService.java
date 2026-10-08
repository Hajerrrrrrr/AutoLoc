package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface IReservationService {

    Reservation ajouter(Reservation reservation);

    Reservation modifier(Reservation reservation);

    Reservation recupererParId(Long id);

    List<Reservation> recupererTous();

    void supprimer(Long id);
}
