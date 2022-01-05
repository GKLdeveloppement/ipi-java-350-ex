package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testGetNombreAnneeAncienneteWhenDateEmbaucheIsNull(){
        //Given = Initialisation des donnée d'entrée
        Employe theEmploye = new Employe();
        theEmploye.setDateEmbauche(null);

        //When
        Integer nbAnnesAnciennete = theEmploye.getNombreAnneeAnciennete();

        //Then = Vérification de ce que fait la méthode
        Assertions.assertThat(nbAnnesAnciennete).isZero();
    }
}
