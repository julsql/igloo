// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;
import eu.telecomsudparis.csc4102.util.OperationImpossible;


class TestMettreCorbeilleDeveloppeur {
	private SuiPro suiPro;
	private String alias;
	private String nom;
	private String prenom;

    
	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("Projet suipro ");
        alias = "alias";
		nom = "nom";
        prenom = "prenom";
	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		alias = null;
		nom = null;
        prenom = null;
	}

	@Test
	void mettreCorbeilleUnDeveloppeurTest1Jeu1() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(""));
	}
	@Test
	void mettreCorbeilleUnDeveloppeurTest1Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(null));
	}
	@Test
	void mettreCorbeilleUnDeveloppeurTest2Jeu1() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(alias));
	}

    @Test
	void mettreCorbeilleUnDeveloppeurTest3Jeu1() throws Exception {
    suiPro.ajouterUnDeveloppeur(alias,nom,prenom);
    Developpeur developpeur = suiPro.getDeveloppeurs().get(alias);
    Map <String,PeriodeDeTravail> periodes = developpeur.getPeriodesDeTravail();
    suiPro.mettreCorbeilleUnDeveloppeur(alias);
    for (var periode : periodes.entrySet()) {
        Assertions.assertTrue(periode.getValue().getCorbeille());
    }   
    Assertions.assertTrue(developpeur.getCorbeille());

	}

    
	

}
