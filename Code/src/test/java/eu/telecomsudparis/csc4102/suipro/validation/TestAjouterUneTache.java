// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUneTache {
	private SuiPro suiPro;
	private String intituleTache;
	private String intituleActivite;
	private String descriptionTache;
	private String descriptionActivite;
    
	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("Projet suipro");
        intituleTache ="intituleTache";
        intituleActivite = "intituleActivite";
        descriptionTache = "descriptionTache";
        descriptionActivite = "descriptionActivite";

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
	void ajouterUneTacheTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUneTache(intituleActivite,"",descriptionTache));
	}

	@Test
	void ajouterUneTacheTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUneTache(intituleActivite,null,descriptionTache));
	}
	@Test
	void ajouterUneTacheTest2Jeu1() throws Exception {
		suiPro.ajouterUneActivite(intituleActivite, descriptionActivite);
		suiPro.ajouterUneTache(intituleActivite, intituleTache, descriptionActivite);
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.ajouterUneTache(intituleActivite,intituleTache,descriptionTache));
	}

}
