package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.dto.PaiementDTO;

@Component
public class PaiementMapper {

    public PaiementDTO toDto(Paiement paiement) {
        if (paiement == null) return null;
        return PaiementDTO.builder()
                .idPaiement(paiement.getIdPaiement())
                .montant(paiement.getMontant())
                .datePaiement(paiement.getDatePaiement())
                .modePaiement(paiement.getModePaiement())
                .idContrat(paiement.getContrat() != null ? paiement.getContrat().getIdContrat() : null)
                .build();
    }
}
