// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import eu.telecomsudparis.csc4102.suipro.Activite;
import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Tache;

class TestTacheMiseCorbeille {

    Tache tache;

    @BeforeEach
    void setUp() {
        tache = new Tache("intitulé", "description", new Activite("intitule", "description"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void miseCorbeilleTacheTest1Jeu1() throws Exception {
        tache.mettreALaCorbeille();
        assert tache.getCorbeille();
    }


    @Test
    void miseCorbeilleTacheTest1Jeu2() throws Exception {
        Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
        PeriodeDeTravail periodeDeTravail = new PeriodeDeTravail(Instant.now(), Instant.now().plus(Duration.ofDays(1))
, tache, developpeur);
        PeriodeDeTravail periodeDeTravail2 = new PeriodeDeTravail(Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(2))
                , tache, developpeur);

        tache.getPeriodesDeTravail().put("première période", periodeDeTravail);
        tache.getPeriodesDeTravail().put("deuxième période", periodeDeTravail2);

        tache.mettreALaCorbeille();

        assert tache.getCorbeille();
        assert periodeDeTravail.getCorbeille();
        assert periodeDeTravail2.getCorbeille();
    }

    void miseCorbeilleTacheTest1Jeu3() throws Exception {
        tache.mettreALaCorbeille();
        tache.mettreALaCorbeille();
        assert tache.getCorbeille();
    }

}
