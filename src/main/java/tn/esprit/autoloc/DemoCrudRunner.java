package tn.esprit.autoloc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.service.IVehiculeService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoCrudRunner implements CommandLineRunner {

    private final IVehiculeService vehiculeService;
    private final IClientService clientService;

    @Override
    public void run(String... args) {
        demoVehicule();
        demoClient();
    }

    private void demoVehicule() {
        // CREATE
        Vehicule v = new Vehicule();
        v.setImmatriculation("123 TUN 4567");
        v.setMarque("Peugeot");
        v.setModele("208");
        v.setCategorie(CategorieVehicule.CITADINE);
        v.setTarifJournalier(new BigDecimal("80.00"));
        v.setStatut(StatutVehicule.DISPONIBLE);
        Vehicule cree = vehiculeService.ajouter(v);
        log.info("Véhicule créé, id = {}", cree.getIdVehicule());

        // READ
        log.info("Lu : {}", vehiculeService.recupererParId(cree.getIdVehicule()).getMarque());
        log.info("Disponibles : {}", vehiculeService.recupererParStatut(StatutVehicule.DISPONIBLE).size());

        // UPDATE
        cree.setStatut(StatutVehicule.LOUE);
        cree.setTarifJournalier(new BigDecimal("90.00"));
        Vehicule modifie = vehiculeService.modifier(cree.getIdVehicule(), cree);
        log.info("Nouveau statut : {}", modifie.getStatut());

        // DELETE
        vehiculeService.supprimer(cree.getIdVehicule());
        log.info("Véhicules restants : {}", vehiculeService.recupererTous().size());
    }

    private void demoClient() {
        // CREATE
        Client c = new Client();
        c.setNom("Ben Ali");
        c.setPrenom("Sarra");
        c.setEmail("sarra.benali@mail.com");
        c.setTelephone("20123456");
        c.setNumPermis("P-2024-001");
        c.setDateInscription(LocalDate.now());
        Client cree = clientService.ajouter(c);
        log.info("Client créé, id = {}", cree.getIdClient());

        // READ
        log.info("Trouvé par email : {}", clientService.recupererParEmail("sarra.benali@mail.com").getNom());

        // UPDATE
        cree.setTelephone("99887766");
        Client modifie = clientService.modifier(cree.getIdClient(), cree);
        log.info("Nouveau téléphone : {}", modifie.getTelephone());

        // DELETE
        clientService.supprimer(cree.getIdClient());
        log.info("Clients restants : {}", clientService.recupererTous().size());
    }
}
