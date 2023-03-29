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
    void miseCorbeilleTacheTest1Jeu1() {
        tache.mettreALaCorbeille();
        assert tache.getCorbeille();
    }

    @Test
    void miseCorbeilleTacheTest1Jeu2() throws Exception {
        Developpeur developpeur = new Developpeur("alias", "nom", "prénom");

        Instant i1 = Instant.now();
        Instant i2 = Instant.now().plus(Duration.ofDays(1));
        Instant i3 = Instant.now().plus(Duration.ofDays(2));

        tache.ajouterPeriode(i1, i2, developpeur);
        tache.ajouterPeriode(i2, i3, developpeur);

        tache.mettreALaCorbeille();

        Assertions.assertTrue(tache.getCorbeille());

        String id1 = i1 + i2.toString() + developpeur;
        String id2 = i2 + i3.toString() + developpeur;

        PeriodeDeTravail periodeDeTravail1 = tache.getPeriodesDeTravail().get(id1);
        PeriodeDeTravail periodeDeTravail2 = tache.getPeriodesDeTravail().get(id2);

        Assertions.assertTrue(periodeDeTravail1.getCorbeille());
        Assertions.assertTrue(periodeDeTravail2.getCorbeille());
    }

    void miseCorbeilleTacheTest1Jeu3() {
        tache.mettreALaCorbeille();
        tache.mettreALaCorbeille();
        assert tache.getCorbeille();
    }

}
