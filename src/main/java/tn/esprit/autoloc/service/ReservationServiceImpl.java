package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation ajouter(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifier(Reservation reservation) {
        if (!reservationRepository.existsById(reservation.getIdReservation())) {
            throw new EntityNotFoundException("Réservation introuvable : id " + reservation.getIdReservation());
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation recupererParId(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : id " + id));
    }

    @Override
    public List<Reservation> recupererTous() {
        return reservationRepository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        reservationRepository.deleteById(id);
    }
}
