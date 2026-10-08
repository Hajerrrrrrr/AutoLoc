package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {

    Client ajouter(Client client);

    Client modifier(Long id, Client client);

    Client recupererParId(Long id);

    List<Client> recupererTous();

    Client recupererParEmail(String email);

    void supprimer(Long id);
}
