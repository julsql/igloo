// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUnDeveloppeur {
	private SuiPro suiPro;
	private String alias;
	private String nom;
	private String prenom;

	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("projet suipro");
		alias = "developpeur1";
		nom = "nom1";
		prenom = "prenom1";
	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		alias = null;
		nom = null;
		prenom = null;
	}

	@Test
	void ajouterUnDeveloppeurTest1Jeu1() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(null, nom, prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest1Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur("", nom, prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest2Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(alias, null, prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest2Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(alias, "", prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest3Jeu1() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(alias, nom, null));
	}

	@Test
	void ajouterUnDeveloppeurTest3Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(alias, nom, ""));
	}

	@Test
	void ajouterUnDeveloppeurTest5Puis4() throws Exception {
		suiPro.ajouterUnDeveloppeur(alias, nom, prenom);
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(alias, nom, prenom));
	}
}
