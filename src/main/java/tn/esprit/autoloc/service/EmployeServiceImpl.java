package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe ajouter(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public Employe modifier(Employe employe) {
        if (!employeRepository.existsById(employe.getIdEmploye())) {
            throw new EntityNotFoundException("Employé introuvable : id " + employe.getIdEmploye());
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe recupererParId(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé introuvable : id " + id));
    }

    @Override
    public List<Employe> recupererTous() {
        return employeRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        employeRepository.deleteById(id);
    }
}
