package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.dto.MaintenanceDTO;

@Component
public class MaintenanceMapper {

    public MaintenanceDTO toDto(Maintenance maintenance) {
        if (maintenance == null) return null;
        return MaintenanceDTO.builder()
                .idMaintenance(maintenance.getIdMaintenance())
                .dateDebut(maintenance.getDateDebut())
                .dateFin(maintenance.getDateFin())
                .description(maintenance.getDescription())
                .idVehicule(maintenance.getVehicule() != null ? maintenance.getVehicule().getIdVehicule() : null)
                .build();
    }

    public Maintenance toEntity(MaintenanceDTO dto) {
        if (dto == null) return null;
        Maintenance maintenance = new Maintenance();
        updateEntity(dto, maintenance);
        return maintenance;
    }

    public void updateEntity(MaintenanceDTO dto, Maintenance maintenance) {
        maintenance.setDateDebut(dto.getDateDebut());
        maintenance.setDateFin(dto.getDateFin());
        maintenance.setDescription(dto.getDescription());
    }
}
