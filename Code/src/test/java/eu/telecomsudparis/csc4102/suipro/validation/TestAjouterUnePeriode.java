package eu.telecomsudparis.csc4102.suipro.validation;

import eu.telecomsudparis.csc4102.suipro.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.time.Duration;
import java.time.Instant;

class TestAjouterUnePeriode {
    private SuiPro suiPro;
    private String intituleTache;
    private String intituleActivite;
    private String aliasDev;
    private Instant debut;
    private Instant fin;

    @BeforeEach
    void setUp() throws OperationImpossible {
        suiPro = new SuiPro("projet suipro");
        intituleTache ="intituleTache";
        intituleActivite = "intituleActivite";
        aliasDev = "developpeur";

        suiPro.ajouterUneActivite(intituleActivite, "");
        suiPro.getActivites().get(intituleActivite).ajouterTache(intituleTache, "");
        suiPro.ajouterUnDeveloppeur(aliasDev, "name", "prenom");

        debut = Instant.now();
        fin = Instant.now().plus(Duration.ofDays(1));

        suiPro.ajouterUnePeriode(intituleActivite,intituleTache, aliasDev, debut, fin);
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
        intituleTache = null;
        intituleActivite = null;
        aliasDev = null;
        debut= null;
        fin = null;
    }

    @Test
    void ajouterUneTacheTest1Jeu1() throws Exception {
        PeriodeDeTravail periodeDeTravailActivite = suiPro.getActivites().get(intituleActivite).getTaches().get(intituleTache).getPeriodesDeTravail().get(debut + fin.toString());
        PeriodeDeTravail periodeDeTravailDeveloppeur = suiPro.getDeveloppeurs().get(aliasDev).getPeriodesDeTravail().get(debut + fin.toString());

        Assertions.assertSame(periodeDeTravailActivite, periodeDeTravailDeveloppeur);
    }
    @Test
    void ajouterUneTacheTest1Jeu2() throws Exception {

        PeriodeDeTravail periodeDeTravailActivite = suiPro.getActivites().get(intituleActivite).getTaches().get(intituleTache).getPeriodesDeTravail().get(debut + fin.toString());

        Tache tache = suiPro.getActivites().get(intituleActivite).getTaches().get(intituleTache);
        Developpeur developpeur = suiPro.getDeveloppeurs().get(aliasDev);
        PeriodeDeTravail periodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur);
        Assertions.assertEquals(periodeDeTravail, periodeDeTravailActivite);
    }
}