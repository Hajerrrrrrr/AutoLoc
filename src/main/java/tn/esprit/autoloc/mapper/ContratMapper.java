package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.dto.ContratDTO;

@Component
public class ContratMapper {

    public ContratDTO toDto(Contrat contrat) {
        if (contrat == null) return null;
        return ContratDTO.builder()
                .idContrat(contrat.getIdContrat())
                .dateSignature(contrat.getDateSignature())
                .montantTotal(contrat.getMontantTotal())
                .valide(contrat.isValide())
                .idReservation(contrat.getReservation() != null ? contrat.getReservation().getIdReservation() : null)
                .build();
    }

    public Contrat toEntity(ContratDTO dto) {
        if (dto == null) return null;
        Contrat contrat = new Contrat();
        updateEntity(dto, contrat);
        return contrat;
    }

    public void updateEntity(ContratDTO dto, Contrat contrat) {
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());
    }
}
