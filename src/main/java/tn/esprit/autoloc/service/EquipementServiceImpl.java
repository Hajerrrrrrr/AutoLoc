package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement ajouter(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement modifier(Equipement equipement) {
        if (!equipementRepository.existsById(equipement.getIdEquipement())) {
            throw new EntityNotFoundException("Équipement introuvable : id " + equipement.getIdEquipement());
        }
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement recupererParId(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Équipement introuvable : id " + id));
    }

    @Override
    public List<Equipement> recupererTous() {
        return equipementRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        equipementRepository.deleteById(id);
    }
}
