// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Tache;
import eu.telecomsudparis.csc4102.suipro.Activite;

class TestTacheConstruction {
	Activite activite;
	@BeforeEach
	void setUp() {
		activite = new Activite("Intitulé", "desctption");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void constructeurTacheTest1Jeu1() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("","",activite));
	}
    @Test
	void constructeurTacheTest1Jeu2() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache(null, "",activite));
	} 
	
	   @Test
	void constructeurTacheTest2Jeu1() throws Exception {
		Tache tache = new Tache("intitulétache", "description", activite);
		Assertions.assertNotNull((tache));
		Assertions.assertEquals("intitulétache", tache.getIntitule());
		Assertions.assertEquals("description", tache.getDescription());
	}

}
