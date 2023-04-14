// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;

class TestActiviteCorbeille {
	private SuiPro suiPro;


	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("projet suipro");

	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		
	}

	@Test
	void TestScenario() throws Exception {
        suiPro.ajouterUneActivite("Activite 1", "desc");
        suiPro.ajouterUneActivite("Activite 2", "desc");
        suiPro.ajouterUneActivite("Activite 3", "desc");
        suiPro.ajouterUneTache("Activite 1", "Tache 1.1", "desc");
        suiPro.ajouterUneTache("Activite 1", "Tache 1.2", "desc");
        suiPro.ajouterUneTache("Activite 1", "Tache 1.3", "desc");

        suiPro.ajouterUneTache("Activite 3", "Tache 3.1", "desc");
        
        suiPro.mettreCorbeilleUneActivite("Activite 1");
        suiPro.mettreCorbeilleUneActivite("Activite 2");
        List<String> activitesCorbeille = suiPro.listerActivitesCorbeille();
        Assertions.assertEquals(activitesCorbeille,Arrays.asList("Activite 1", "Activite 2"));
        
	}
	
}
