package com.ipiecoles.java.java350.acceptance;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeAcceptanceTest {

    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private EmployeService employeService;
    @Step("On vire tous les employés s'il y en a")
    public void vireTousLesEmployes() {
        employeRepository.deleteAll();
    }

    @Step("J'embauche une personne appelée <prenom> <nom> diplômée d'un <diplome> en tant que <poste> à <tempsTravail>")
    public void embaucheEmploye(String prenom, String nom, String diplome, String poste, String tempsTravail) throws EmployeException {
        employeService.embaucheEmploye(nom, prenom,
                Poste.valueOf(poste.toUpperCase()),
                NiveauEtude.valueOf(diplome.toUpperCase()),
                tempsTravail.equals("temps plein") ? 1.0 : 0.5);
    }

    @Step("J'obtiens bien un nouvel employé appelé <prenom> <nom> portant le matricule <matricule> et touchant un salaire de <salaire> €")
    public void checkEmploye(String prenom, String nom, String matricule, Double salaire) {
        Employe employe = employeRepository.findByMatricule(matricule);
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(salaire);
        Assertions.assertThat(employe.getPerformance()).isEqualTo(Entreprise.PERFORMANCE_BASE);
        System.out.println("test");
        //Taux d'activité
    }

    @Step("Soit un employé appelé <prenom> <nom> de matricule <matricule>")
    public void addEmploye(String prenom, String nom, String matricule) {
        employeRepository.save(new Employe(nom, prenom, matricule, LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
    }
}
