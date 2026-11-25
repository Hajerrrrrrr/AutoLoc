package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.dto.EmployeDTO;

@Component
public class EmployeMapper {

    public EmployeDTO toDto(Employe employe) {
        if (employe == null) return null;
        return EmployeDTO.builder()
                .idEmploye(employe.getIdEmploye())
                .nom(employe.getNom())
                .prenom(employe.getPrenom())
                .role(employe.getRole())
                .idAgence(employe.getAgence() != null ? employe.getAgence().getIdAgence() : null)
                .build();
    }

    public Employe toEntity(EmployeDTO dto) {
        if (dto == null) return null;
        Employe employe = new Employe();
        updateEntity(dto, employe);
        return employe;
    }

    public void updateEntity(EmployeDTO dto, Employe employe) {
        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        employe.setRole(dto.getRole());
    }
}
