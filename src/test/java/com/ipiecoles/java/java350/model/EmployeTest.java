package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

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
}
