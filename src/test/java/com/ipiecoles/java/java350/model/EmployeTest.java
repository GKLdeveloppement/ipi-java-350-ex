package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    //cas 1 : DateNow = 05/01/2022 | dateEmbauche = 05/01/2023 | nbAnneeAnciennete : 0
    @Test
    public void testGetNombreAnneeAncienneteWhenDateEmbaucheFuture(){
        //Given = Initialisation des donnée d'entrée
        LocalDate dateEmbauche = LocalDate.now().plusYears(2);
        Employe theEmploye = new Employe("Doe","John","T12345", dateEmbauche, Entreprise.SALAIRE_BASE, 4 , 1d);

        //When
        Integer nbAnnesAnciennete = theEmploye.getNombreAnneeAnciennete();

        //Then = Vérification de ce que fait la méthode
        Assertions.assertThat(nbAnnesAnciennete).isZero();
    }

    //cas 2 : DateNow = 05/01/2022 | dateEmbauche = 05/01/2022 | nbAnneeAnciennete : 0
    @Test
    public void testGetNombreAnneeAncienneteWhenDateEmbaucheNow(){
        //Given = Initialisation des donnée d'entrée
        LocalDate dateEmbauche = LocalDate.now();
        Employe theEmploye = new Employe("Doe","John","T12345", dateEmbauche, Entreprise.SALAIRE_BASE, 4 , 1d);

        //When
        Integer nbAnnesAnciennete = theEmploye.getNombreAnneeAnciennete();

        //Then = Vérification de ce que fait la méthode
        Assertions.assertThat(nbAnnesAnciennete).isZero();
    }


    //cas 3 : DateNow = 05/01/2022 | dateEmbauche = 05/01/2021 | nbAnneeAnciennete : 1
    @Test
    public void testGetNbAnneeAncienneteWithDateEmbauchePasse(){
        //Given
        LocalDate dateEmbauche = LocalDate.now().minusYears(2);

        Employe employe = new Employe("Doe", "John", "T12345", dateEmbauche, Entreprise.SALAIRE_BASE, 4, 1d);
        //When
        Integer nbAnneeAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneeAnciennete).isEqualTo(2);
    }

    //cas 4 : DateNow = now | dateEmbauche = null | nbAnneeAnciennete : 0
    @Test
    public void testGetNbAnneeAncienneteWithDateEmbaucheNull(){
        //Given
        LocalDate dateEmbauche = null;

        Employe employe = new Employe("Doe", "John", "T12345", dateEmbauche, Entreprise.SALAIRE_BASE, 4, 1d);
        //When
        Integer nbAnneeAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneeAnciennete).isZero();
    }

    //cas test paramétré
    @ParameterizedTest(name = "Employé de matricule {0}, de performance {1}, avec {2} années d'ancienneté, taux d'activité {3} : prime {4}")
    @CsvSource({
            "'T12345',1,0,1.0,1000.0",
            ",1,0,1.0,1000.0",
            "'T12345',,0,1.0,1000.0",
            "'M12345',1,0,1.0,1700.0",
            "'T12345',2,0,1.0,2300.0"
    })
    public void testGetPrimeAnnuelle(String matricule, Integer performance, Integer nombreAnneeAnciennete, Double tempsPartiel, Double primeAttendue){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nombreAnneeAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double d = employe.getPrimeAnnuelle();
        //Then
        //Prime de base + prime de performance + prime d'ancienneté au pro rata de son activité
        Assertions.assertThat(d).isEqualTo(primeAttendue);
    }

    //TP : test NbRtt
    @ParameterizedTest(name = "Pour {0}, en temps partiel de {1}, on a {2} nbRTT.")
    @CsvSource({
            "2019, 1.0, 8",
            "2021, 0.5, 5",
            "2022, 1.0, 11",
            "2032, 1.0, 10"
    })
    void testGetNbRtt(Integer annee, Double tpPartiel, Integer nbRttFinal) {
        // Given
        Employe employe = new Employe();
        LocalDate date = LocalDate.of(annee, 1, 1);
        employe.setTempsPartiel(tpPartiel);
        // When
        Integer NbRtt = employe.getNbRtt(date);
        // Then
        Assertions.assertThat(NbRtt).isEqualTo(nbRttFinal);
    }


    //TP : test augmenterSalaire()

    @Test
    public void testAugmenterSalaire() {
        //Given
        Employe employe = new Employe("Zlatan", "Ibra", "T25252", LocalDate.now(), 1500.00d, 1, 1.0);

        //When -- on augmente son salaire de 5%
        employe.augmenterSalaire(5.0);
        System.out.println(employe.getSalaire());

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1575.00d);
    }


    @Test
    public void testAugmenterSalaireWithSalaireEqual0() {
        //Given
        Employe employe = new Employe("Doe", "John", "T25253", LocalDate.now(), 0.00, 1, 1.0);

        //When -- on augmente son salaire de 5%
        employe.augmenterSalaire(5.0);
        System.out.println(employe.getSalaire());

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(0.00);
    }


    @Test
    public void testAugmenterSalaireWithSalaireNegative() {
        //Given
        Employe employe = new Employe("Doe", "John", "T25253", LocalDate.now(), -500.00, 1, 1.0);

        //When -- on augmente son salaire de 5%
        employe.augmenterSalaire(5.0);
        System.out.println(employe.getSalaire());

        //Then -- le salaire de l'employé ne bouge pas exemple si il est endetté de jours de congés aupres de l'employeur
        Assertions.assertThat(employe.getSalaire()).isEqualTo(-500.00);
    }


    @Test
    public void testAugmenterSalaireWithPourcentageNegative() {
        //Given
        Employe employe = new Employe("Doe", "John", "T25253", LocalDate.now(), 1500.00, 1, 1.0);

        //When -- on augmente son salaire de 5%
        employe.augmenterSalaire(-5.0);
        System.out.println(employe.getSalaire());

        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1500d);

    }
}
