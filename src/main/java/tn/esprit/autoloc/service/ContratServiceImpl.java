package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    public Contrat ajouter(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifier(Contrat contrat) {
        if (!contratRepository.existsById(contrat.getIdContrat())) {
            throw new EntityNotFoundException("Contrat introuvable : id " + contrat.getIdContrat());
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat recupererParId(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable : id " + id));
    }

    @Override
    public List<Contrat> recupererTous() {
        return contratRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        contratRepository.deleteById(id);
    }
}
