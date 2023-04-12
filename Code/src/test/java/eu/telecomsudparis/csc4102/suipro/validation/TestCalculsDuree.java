// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.time.LocalDate;
import java.time.ZoneId;



public class TestCalculsDuree {

    private SuiPro suiPro;
	private String intituleTache;
	private String intituleActivite;
	private String descriptionTache;
	private String descriptionActivite;
	private String alias;
    
    @BeforeEach
	void setUp() throws OperationImpossible{
		suiPro = new SuiPro("Projet suipro");
        intituleTache ="Tache 1 ";
        intituleActivite = "Activité 1";
        descriptionTache = "Bla bla bla";
        descriptionActivite = "blabla";
        alias = "TurboDev";
        suiPro.ajouterUnDeveloppeur(alias, "jean", "PIERRE");

	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		intituleTache = null;
		intituleActivite = null;
		descriptionTache= null;
		descriptionActivite = null;
	}

    @Test
    void TestDureeProjetVide() {
        Assertions.assertEquals(suiPro.dureeTravail(),Duration.ZERO);
    }
    @Test
    void TestScenarioDureeProjet() throws OperationImpossible, InterruptedException {
        suiPro.ajouterUneActivite(intituleActivite, descriptionActivite);
        suiPro.ajouterUneTache(intituleActivite, intituleTache, descriptionActivite);
        LocalDate date1 = LocalDate.of(2015, 1, 11);
        LocalDate date2 = LocalDate.of(2016, 1, 11);

        Instant instant1 = date1.atStartOfDay(ZoneId.of("UTC")).toInstant();
        Instant instant2 = date2.atStartOfDay(ZoneId.of("UTC")).toInstant();

        suiPro.ajouterUnePeriode(intituleActivite, intituleTache,alias,instant1, instant2);

        Assertions.assertEquals(suiPro.dureeTravail(), Duration.ofDays(365)); 

        suiPro.ajouterUneActivite("activité2", descriptionActivite);
        suiPro.ajouterUneTache("activité2", "tache2", descriptionTache);
        LocalDate date3 = LocalDate.of(2017, 1, 12);
        LocalDate date4 = LocalDate.of(2018, 1, 12);
        Instant instant3 = date3.atStartOfDay(ZoneId.of("UTC")).toInstant();
        Instant instant4 = date4.atStartOfDay(ZoneId.of("UTC")).toInstant();
        suiPro.ajouterUnePeriode("activité2", "tache2",alias,instant3, instant4);

        Assertions.assertEquals(suiPro.dureeTravail(), Duration.ofDays(365*2)); // deux taches d'un 1 = 2 ans

        suiPro.mettreCorbeilleUneActivite("activité2");
        Assertions.assertEquals(suiPro.dureeTravail(), Duration.ofDays(365));
        suiPro.restaurerActivite("activité2");

        Assertions.assertEquals(suiPro.dureeTravail(), Duration.ofDays(365*2)); //restauration de l'activité rajoute ses periodes au compteur

        suiPro.mettreCorbeilleUnDeveloppeur(alias);
        Assertions.assertEquals(suiPro.dureeTravail(), Duration.ofDays(0));

        suiPro.restaurerDeveloppeur(alias);
        Assertions.assertEquals(suiPro.dureeTravail(), Duration.ofDays(0)); //restauration dev ne change pas ses PDT
    }
}


