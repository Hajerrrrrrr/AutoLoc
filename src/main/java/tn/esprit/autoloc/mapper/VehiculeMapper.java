package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.VehiculeDTO;

import java.util.Collections;
import java.util.List;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDto(Vehicule vehicule) {
        if (vehicule == null) return null;
        List<Long> equipementIds = vehicule.getEquipements() == null
                ? Collections.emptyList()
                : vehicule.getEquipements().stream().map(Equipement::getIdEquipement).toList();
        return VehiculeDTO.builder()
                .idVehicule(vehicule.getIdVehicule())
                .immatriculation(vehicule.getImmatriculation())
                .marque(vehicule.getMarque())
                .modele(vehicule.getModele())
                .categorie(vehicule.getCategorie())
                .tarifJournalier(vehicule.getTarifJournalier())
                .statut(vehicule.getStatut())
                .idAgence(vehicule.getAgence() != null ? vehicule.getAgence().getIdAgence() : null)
                .equipementIds(equipementIds)
                .build();
    }

    public Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) return null;
        Vehicule vehicule = new Vehicule();
        updateEntity(dto, vehicule);
        return vehicule;
    }

    public void updateEntity(VehiculeDTO dto, Vehicule vehicule) {
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setStatut(dto.getStatut());
    }
}
