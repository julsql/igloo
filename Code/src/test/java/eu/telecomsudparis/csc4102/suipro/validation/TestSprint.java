// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import eu.telecomsudparis.csc4102.suipro.ConsommateurMiseALaCorbeille;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.time.Duration;
import java.time.Instant;

class TestSprint {
    private SuiPro suiPro;
    private Instant now;
    private Instant now1h;
    private Instant tomorrow;
    private Instant tomorrow1h;
    private Instant totomorrow;
    private Instant totomorrow1h;

    @BeforeEach
    void setUp() {
        suiPro = new SuiPro("Projet suipro");
        now = Instant.now();
        now1h = now.plus(Duration.ofHours(1));
        tomorrow = now.plus(Duration.ofDays(1));
        tomorrow1h = tomorrow.plus(Duration.ofHours(1));
        totomorrow = now.plus(Duration.ofDays(2));
        totomorrow1h = now.plus(Duration.ofDays(2)).plus(Duration.ofHours(1));
    }

    @AfterEach
    void tearDown() {
        suiPro = null;
    }

    @Test
    void sprint1Test1Jeu1() throws Exception {
        // Ajout des développeurs
        suiPro.ajouterUnDeveloppeur("pastorel", "Pastorel", "Emmanuel"); // 1
        suiPro.ajouterUnDeveloppeur("duscastel", "Duscastel", "Jean-Baptiste");  // 2
        suiPro.ajouterUnDeveloppeur("vergniaud", "Vergniaud", "Pierre-Victurnien"); // 3
        suiPro.ajouterUnDeveloppeur("viénot-vaublanc", "Viénot-Vaublanc", "Vincent"); // 4

        // Ajout d'une activité et d'une tâche
        suiPro.ajouterUneActivite("cd", "Conception détaillée"); // 5
        suiPro.ajouterUneTache("cd", "dc", "Définition des classes"); // 6
        suiPro.ajouterUneTache("cd", "mi", "Maquettage des interfaces"); // 7

        // Ajout des périodes
        suiPro.ajouterUnePeriode("cd", "dc", "pastorel", now, now1h); // 8
        suiPro.ajouterUnePeriode("cd", "dc", "duscastel", now, now1h); // 8
        suiPro.ajouterUnePeriode("cd", "dc", "vergniaud", now, now1h); // 8
        suiPro.ajouterUnePeriode("cd", "dc", "viénot-vaublanc", now, now1h); // 8

        // Erreur
        Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnePeriode("cd", "dc", "pastorel", now, now.plus(Duration.ofMinutes(30)))); // 9
        // Ajout périodes de travail supplémentaires
        suiPro.ajouterUnePeriode("cd", "mi", "pastorel", tomorrow, tomorrow1h); // 10
        suiPro.ajouterUnePeriode("cd", "mi", "vergniaud", tomorrow, tomorrow1h); // 10
        suiPro.ajouterUnePeriode("cd", "dc", "duscastel", tomorrow, tomorrow1h); // 11
        suiPro.ajouterUnePeriode("cd", "dc", "viénot-vaublanc", tomorrow, tomorrow1h); // 11

        // Afficher
        suiPro.afficherDeveloppeurs(); // 12
        suiPro.afficherTaches("cd", false); // 13
        suiPro.afficherPeriodesDeTravail("cd", "dc", false); // 14
        suiPro.afficherPeriodesDeTravail("cd", "mi", false); // 15

        // Mise à la corbeille
        suiPro.mettreCorbeilleUnDeveloppeur("pastorel"); // 1
        suiPro.afficherDeveloppeurs(); // 2
        suiPro.afficherDeveloppeursCorbeille(); // 3
        suiPro.afficherPeriodesDeTravail("cd", "dc", false); // 4
        suiPro.afficherPeriodesDeTravail("cd", "mi", false); // 5
        suiPro.afficherPeriodesDeTravailCorbeille(); // 6

        // Erreur
        Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnePeriode("cd", "dc", "pastorel", totomorrow, totomorrow1h)); // 7
        suiPro.mettreCorbeilleUnDeveloppeur("pastorel"); // 8

        suiPro.afficherDeveloppeurs(); // 9
        suiPro.afficherDeveloppeursCorbeille(); // 10
    }
    @Test
    void sprint2Test1Jeu1() throws Exception {
        // Ajout des développeurs
        suiPro.ajouterUnDeveloppeur("braun", "Braun", "Madeleine"); // 1
        suiPro.ajouterConsommateur("braun", new ConsommateurMiseALaCorbeille("braun"));

        suiPro.ajouterUnDeveloppeur("bureau-bonnard", "Bureau-Bonnard", "Carole");  // 2
        suiPro.ajouterConsommateur("bureau-bonnard", new ConsommateurMiseALaCorbeille("bureau-bonnard"));

        suiPro.ajouterUnDeveloppeur("peyroles", "Peyroles", "Germaine"); // 3
        suiPro.ajouterConsommateur("peyroles", new ConsommateurMiseALaCorbeille("peyroles"));

        suiPro.ajouterUnDeveloppeur("braun-pivet", "Braun-Pivet", "Yaël"); // 4
        suiPro.ajouterConsommateur("braun-pivet", new ConsommateurMiseALaCorbeille("braun-pivet"));


        // Ajout d'une activité et d'une tâche
        suiPro.ajouterUneActivite("cd", "Conception détaillée"); // 5
        suiPro.ajouterUneTache("cd", "dc", "Définition des classes"); // 6
        suiPro.ajouterUneTache("cd", "mi", "Maquettage des interfaces"); // 7

        // Ajout des périodes
        suiPro.ajouterUnePeriode("cd", "dc", "braun", now, now1h); // 8
        suiPro.ajouterUnePeriode("cd", "dc", "bureau-bonnard", now, now1h); // 8
        suiPro.ajouterUnePeriode("cd", "dc", "peyroles", now, now1h); // 8
        suiPro.ajouterUnePeriode("cd", "dc", "braun-pivet", now, now1h); // 8

        // Erreur
        Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnePeriode("cd", "dc", "bureau-bonnard", now, now.plus(Duration.ofMinutes(30)))); // 9

        // Ajout périodes de travail supplémentaires
        suiPro.ajouterUnePeriode("cd", "mi", "braun", tomorrow, tomorrow1h); // 10
        suiPro.ajouterUnePeriode("cd", "mi", "bureau-bonnard", tomorrow, tomorrow1h); // 10
        suiPro.ajouterUnePeriode("cd", "dc", "braun-pivet", tomorrow, tomorrow1h); // 11
        suiPro.ajouterUnePeriode("cd", "dc", "peyroles", tomorrow, tomorrow1h); // 11

        // Afficher
        suiPro.afficherDeveloppeurs(); // 12
        suiPro.afficherTaches("cd", false); // 13
        suiPro.afficherPeriodesDeTravail("cd", "dc", false); // 14
        suiPro.afficherPeriodesDeTravail("cd", "mi", false); // 15

        // Calcul de durée
        System.out.println("Braun travaille " + suiPro.dureeTravailDeveloppeur("braun")); // 16
        System.out.println("Bureau-Bonnard travaille " + suiPro.dureeTravailDeveloppeur("bureau-bonnard")); // 16
        System.out.println("Peyroles travaille " + suiPro.dureeTravailDeveloppeur("peyroles")); // 16
        System.out.println("Braun-Pivet travaille " + suiPro.dureeTravailDeveloppeur("braun-pivet")); // 16
        System.out.println("La tâche Définition des classes prend " + suiPro.dureeTravailTache("cd", "dc")); // 17
        System.out.println("La tâche Maquettage des interfaces prend " + suiPro.dureeTravailTache("cd", "mi")); // 17
        System.out.println("L'activité Conception Détaillée dure " + suiPro.dureeTravailActivite("cd")); // 18
        System.out.println("Le projet dure " + suiPro.dureeTravail()); // 19
        System.out.println();

        // Mise à la corbeille
        suiPro.mettreCorbeilleUnDeveloppeur("bureau-bonnard"); // 1
        suiPro.afficherDeveloppeurs(); // 2
        suiPro.afficherDeveloppeursCorbeille(); // 3
        suiPro.afficherPeriodesDeTravail("cd", "dc", false); // 4
        suiPro.afficherPeriodesDeTravail("cd", "mi", false); // 5
        suiPro.afficherPeriodesDeTravailCorbeille(); // 6

        // Calcul de durée
        System.out.println("Braun travaille " + suiPro.dureeTravailDeveloppeur("braun")); // 7
        System.out.println("Bureau-Bonnard travaille " + suiPro.dureeTravailDeveloppeur("bureau-bonnard")); // 7
        System.out.println("Peyroles travaille " + suiPro.dureeTravailDeveloppeur("peyroles")); // 7
        System.out.println("Braun-Pivet travaille " + suiPro.dureeTravailDeveloppeur("braun-pivet")); // 7
        System.out.println("La tâche Définition des classes prend " + suiPro.dureeTravailTache("cd", "dc")); // 8
        System.out.println("La tâche Maquettage des interfaces prend " + suiPro.dureeTravailTache("cd", "mi")); // 8
        System.out.println("L'activité Conception Détaillée dure " + suiPro.dureeTravailActivite("cd")); // 9
        System.out.println("Le projet dure " + suiPro.dureeTravail()); // 10
        System.out.println();

        // Erreur
        Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnePeriode("cd", "dc", "bureau-bonnard", totomorrow, totomorrow1h)); // 11

        suiPro.mettreCorbeilleUnDeveloppeur("bureau-bonnard"); // 12
        suiPro.afficherDeveloppeurs(); // 13
        suiPro.afficherDeveloppeursCorbeille(); // 14

        // Restauration
        suiPro.restaurerDeveloppeur("bureau-bonnard"); // 1
        suiPro.afficherDeveloppeurs(); // 2
        suiPro.afficherDeveloppeursCorbeille(); // 3
        System.out.println("Le projet dure " + suiPro.dureeTravail()); // 3
        System.out.println("L'activité Conception Détaillée dure " + suiPro.dureeTravailActivite("cd")); // 5
        System.out.println("Bureau-Bonnard travaille " + suiPro.dureeTravailDeveloppeur("bureau-bonnard")); // 6

        // Labellisation
        suiPro.ajouterUneTache("cd", "révision", "Révision JAVA"); // 1
        suiPro.ajouterUnePeriode("cd", "révision", "braun-pivet", totomorrow, totomorrow.plus(Duration.ofHours(1))); // 2
        System.out.println("Le projet dure " + suiPro.dureeTravail()); // 3
        // this.ajoutLabel("remédiation", "Remédiation"); // 4
        // this.ajoutLabelTache("révision", "remédiation"); // 5
        // this.dureeTravail("remédiation"); // 6

        System.out.println("MISE A LA CORBEILLE");
        suiPro.mettreCorbeilleUneTache("cd", "dc"); // 1
    }
}
