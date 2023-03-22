// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.Tache;

class TestTacheConstruction {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void constructeurTacheTest1Jeu1() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache("",""));
	}
    @Test
	void constructeurTacheTest1Jeu2() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Tache(null, ""));
	}

}
