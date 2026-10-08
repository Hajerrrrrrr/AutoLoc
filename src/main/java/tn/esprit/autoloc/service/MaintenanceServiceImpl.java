package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance ajouter(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance modifier(Maintenance maintenance) {
        if (!maintenanceRepository.existsById(maintenance.getIdMaintenance())) {
            throw new EntityNotFoundException("Maintenance introuvable : id " + maintenance.getIdMaintenance());
        }
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance recupererParId(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable : id " + id));
    }

    @Override
    public List<Maintenance> recupererTous() {
        return maintenanceRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        maintenanceRepository.deleteById(id);
    }
}
