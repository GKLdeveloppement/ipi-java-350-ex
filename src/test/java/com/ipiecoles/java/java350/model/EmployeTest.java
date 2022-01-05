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
            "'T12345',1,0,1.0,1000.0"
    })
    public void testGetPrimeAnnuelle(String matricule, Integer performance, Integer nombreAnneeAnciennete, Double tempsPartiel, Double primeAttendue){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nombreAnneeAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double d = employe.getPrimeAnnuelle();
        //Then
        //Prime de base + prime de performance + prime d'ancienneté au pro rata de son activité
        //1000 + 0 + 0 => 1000
        Assertions.assertThat(d).isEqualTo(primeAttendue);
    }
}
