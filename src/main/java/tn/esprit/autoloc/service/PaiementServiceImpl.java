package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement ajouter(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement modifier(Paiement paiement) {
        if (!paiementRepository.existsById(paiement.getIdPaiement())) {
            throw new EntityNotFoundException("Paiement introuvable : id " + paiement.getIdPaiement());
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement recupererParId(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement introuvable : id " + id));
    }

    @Override
    public List<Paiement> recupererTous() {
        return paiementRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        paiementRepository.deleteById(id);
    }
}
