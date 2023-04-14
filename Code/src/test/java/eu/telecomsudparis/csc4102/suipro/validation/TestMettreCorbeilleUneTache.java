// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.suipro.validation;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import eu.telecomsudparis.csc4102.suipro.Developpeur;
import eu.telecomsudparis.csc4102.suipro.PeriodeDeTravail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.suipro.SuiPro;
import eu.telecomsudparis.csc4102.util.OperationImpossible;


class TestMettreCorbeilleUneTache {
	private SuiPro suiPro;
	private String intituleTache;
	private String intituleActivite;
	private String alias;

	@BeforeEach
	void setUp() {
		suiPro = new SuiPro("Projet suipro");
		intituleActivite = "intituleActivite";
		intituleTache = "intituleTache";
		alias = "alias";

	}

	@AfterEach
	void tearDown() {
		suiPro = null;
		intituleTache = null;
		intituleActivite = null;
	}

	@Test
	void mettreCorbeilleUneTacheTest1Jeu1() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUneTache(intituleActivite,""));
	}

	@Test
	void mettreCorbeilleUneTacheTest1Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUneTache(intituleActivite,null));
	}

	@Test
	void mettreCorbeilleUneTacheTest2Jeu1() {
		Assertions.assertThrows(OperationImpossible.class, () -> suiPro.mettreCorbeilleUneTache(intituleActivite,"Cette tache n'existe pas"));
	}

	@Test
	void mettreCorbeilleUneTacheTest3Jeu1() throws Exception {
		suiPro.ajouterUneActivite(intituleActivite, intituleActivite);
		suiPro.ajouterUneTache(intituleActivite, intituleTache, intituleActivite);
		Instant now = Instant.now();
		Instant now1h = now.plus(Duration.ofHours(1));
		suiPro.ajouterUnDeveloppeur(alias, "nom", "prenom");
		Developpeur developpeur = suiPro.getDeveloppeurs().get(alias);
		suiPro.ajouterUnePeriode(intituleActivite, intituleTache, alias, now, now1h);
		PeriodeDeTravail periode = suiPro.getDeveloppeurs().get(alias).getPeriodesDeTravail().get(now + now1h.toString() + developpeur);
		suiPro.mettreCorbeilleUneTache(intituleActivite, intituleTache);


		List<String> tachesCorbeille = suiPro.listerTachesCorbeille();
		List<String> CetteTache = new ArrayList<>();
		CetteTache.add(intituleTache);

		Assertions.assertEquals(tachesCorbeille, CetteTache);

		List<PeriodeDeTravail> periodesCorbeille = suiPro.listerPeriodesCorbeille();
		List<PeriodeDeTravail> CettePeriode = new ArrayList<>();
		CettePeriode.add(periode);

		// TODO Patron

		Assertions.assertEquals(periodesCorbeille, CettePeriode);
	}

}
