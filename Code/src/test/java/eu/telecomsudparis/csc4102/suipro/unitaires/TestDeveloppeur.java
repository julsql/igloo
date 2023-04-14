// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Developpeur;

class TestDeveloppeur {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void constructeurDeveloppeurTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur(null, "nom", "prénom"));
	}

	@Test
	void constructeurDeveloppeurTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("", "nom", "prénom"));
	}

	@Test
	void constructeurDeveloppeurTest2Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", null, "prenom"));
	}

	@Test
	void constructeurDeveloppeurTest2Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "", "prenom"));
	}

	@Test
	void constructeurDeveloppeurTest3Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", null));
	}

	@Test
	void constructeurDeveloppeurTest3Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Developpeur("alias", "nom", ""));
	}
	@Test
	void constructeurDeveloppeurTest4() {
		Developpeur developpeur = new Developpeur("alias", "nom", "prénom");
		Assertions.assertNotNull(developpeur);
		Assertions.assertEquals("alias", developpeur.getAlias());
	}
}
