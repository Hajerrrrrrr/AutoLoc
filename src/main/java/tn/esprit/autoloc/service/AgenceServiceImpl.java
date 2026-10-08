package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence ajouter(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifier(Agence agence) {
        if (!agenceRepository.existsById(agence.getIdAgence())) {
            throw new EntityNotFoundException("Agence introuvable : id " + agence.getIdAgence());
        }
        return agenceRepository.save(agence);
    }

    @Override
    public Agence recupererParId(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : id " + id));
    }

    @Override
    public List<Agence> recupererTous() {
        return agenceRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        agenceRepository.deleteById(id);
    }
}
