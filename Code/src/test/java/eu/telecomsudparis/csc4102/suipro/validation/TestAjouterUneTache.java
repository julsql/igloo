// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Activite;
import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUneTache {
	private SuiPro suiPro;
	private String intituleTache;
	private String intituleActivite;
	private String descriptionTache;
	private String descriptionActivite;
    private Activite activite;
    
	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("Projet Juliette Nathan");
        intituleTache ="intituleTache";
        intituleActivite = "intituleActivite";
        descriptionTache = "descriptionTache";
        descriptionActivite = "descriptionActivite";
        activite = new Activite(intituleActivite, descriptionActivite)

	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		identifiant = null;
		nom = null;
		prenom = null;
	}

	@Test
	void ajouterUnDeveloppeurTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(null, nom, prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur("", nom, prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> suiPro.ajouterUnDeveloppeur(identifiant, null, prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(identifiant, "", prenom));
	}

	@Test
	void ajouterUnDeveloppeurTest3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(identifiant, nom, null));
	}

	@Test
	void ajouterUnDeveloppeurTest3Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(identifiant, nom, ""));
	}

	@Test
	void ajouterUnDeveloppeurTest5Puis4() throws Exception {
		suiPro.ajouterUnDeveloppeur(identifiant, nom, prenom);
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUnDeveloppeur(identifiant, nom, prenom));
	}
}
