package tn.esprit.autoloc.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.dto.ReservationRequestDTO;
import tn.esprit.autoloc.dto.ReservationResponseDTO;

@Component
public class ReservationMapper {

    public ReservationResponseDTO toResponseDto(Reservation reservation) {
        if (reservation == null) return null;
        Vehicule vehicule = reservation.getVehicule();
        Client client = reservation.getClient();
        return ReservationResponseDTO.builder()
                .idReservation(reservation.getIdReservation())
                .dateDebut(reservation.getDateDebut())
                .dateFin(reservation.getDateFin())
                .statut(reservation.getStatut())
                .idVehicule(vehicule != null ? vehicule.getIdVehicule() : null)
                .immatriculationVehicule(vehicule != null ? vehicule.getImmatriculation() : null)
                .idClient(client != null ? client.getIdClient() : null)
                .nomClient(client != null ? client.getNom() : null)
                .prenomClient(client != null ? client.getPrenom() : null)
                .idContrat(reservation.getContrat() != null ? reservation.getContrat().getIdContrat() : null)
                .build();
    }

    public Reservation toEntity(ReservationRequestDTO dto) {
        if (dto == null) return null;
        Reservation reservation = new Reservation();
        updateEntity(dto, reservation);
        return reservation;
    }

    public void updateEntity(ReservationRequestDTO dto, Reservation reservation) {
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
    }
}
