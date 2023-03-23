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
	void mettreCorbeilleUneDeveloppeurTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur("",nom,prenom));
	}
	@Test
	void mettreCorbeilleUneDeveloppeurTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(null,nom,prenom));
	}
	@Test
	void mettreCorbeilleUneTacheTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(alias,"",prenom));
	}
    @Test
	void mettreCorbeilleUneTacheTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(alias,null,prenom));
	}
    @Test
	void mettreCorbeilleUneTacheTest3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(alias,nom,""));
	}
    @Test
	void mettreCorbeilleUneTacheTest3Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUnDeveloppeur(alias,nom,null));
	}

    @Test
	void mettreCorbeilleUneTacheTest4Jeu1() throws Exception {
    suiPro.ajouterUnDeveloppeur(alias,nom,prenom);
    Developpeur developpeur = suiPro.getDeveloppeurs().get(alias);
    Map <String,PeriodeDeTravail> periodes = developpeur.getPeriodesDeTravail();
    suiPro.mettreCorbeilleUnDeveloppeur(alias, nom, prenom);
    for (var periode : periodes.entrySet()) {
        Assertions.assertTrue(periode.getValue().getCorbeille());
    }   
    Assertions.assertTrue(developpeur.getCorbeille());

	}

    
	

}
