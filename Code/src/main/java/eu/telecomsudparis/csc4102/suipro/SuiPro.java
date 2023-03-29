package eu.telecomsudparis.csc4102.suipro;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import eu.telecomsudparis.csc4102.util.OperationImpossible;


/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public class SuiPro {
	/**
	 * le nom du projet.
	 */
	private final String nomDeProjet;
	/**
	 * la collection de développeurs. La clef est l'identifiant du développeur.
	 */
	private Map<String, Developpeur> developpeurs;
	/**
	 * la collection de activités. La clef est l'identifiant de l'activité.
	 */
	private Map<String, Activite> activites;

	/**
	 * construit une façade.
	 * 
	 * @param nomDeProjet le nom du projet géré par le logiciel.
	 */
	public SuiPro(final String nomDeProjet) {
		this.nomDeProjet = nomDeProjet;
		developpeurs = new HashMap<>();
		activites = new HashMap<>();
	}

	public static void main(String[] args) throws OperationImpossible {
		SuiPro suiPro = new SuiPro("Projet");
		suiPro.scenarioSprint1();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomDeProjet != null && !nomDeProjet.isBlank() && developpeurs != null && activites != null;
	}

	/**
	 * ajoute un développeur.
	 * 
	 * @param alias  l'alias.
	 * @param nom    le nom.
	 * @param prenom le prénom.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */


	public void ajouterUnDeveloppeur(final String alias, final String nom, final String prenom)
			throws OperationImpossible {
		if (alias == null || alias.isBlank()) {
			throw new OperationImpossible("alias ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new OperationImpossible("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new OperationImpossible("prenom ne peut pas être null ou vide");
		}
		if (developpeurs.get(alias) != null) {
			throw new OperationImpossible("développeur déjà dans le système");
		}
		developpeurs.put(alias, new Developpeur(alias, nom, prenom));
		assert invariant();
	}
	/**
	 * ajoute une tâche.
	 *
	 * @param intituleActivite  l'intitulé de l'activité.
	 * @param intituleTache  l'intitulé de la tâche.
	 * @param description  la description de la tâche.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void ajouterUneTache(final String intituleActivite, final String intituleTache, final String description)
			throws OperationImpossible {
		if (intituleActivite == null || intituleActivite.isBlank()) {
			throw new OperationImpossible("intitulé de l'activité ne peut pas être null ou vide");
		}
		if (intituleTache == null || intituleTache.isBlank()) {
			throw new OperationImpossible("intitulé de la tâche ne peut pas être null ou vide");
		}
		Activite activite = activites.get(intituleActivite);
		if (activite == null){
			throw new OperationImpossible("l'activité ne peut pas être null");
		}
		activite.ajouterTache(intituleTache,description);

		assert invariant();
	}
	/**
	 * ajoute une activité.
	 *
	 * @param intituleActivite  l'intitulé de l'activité.
	 * @param description  la description de la tâche.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void ajouterUneActivite(String intituleActivite,String description) throws OperationImpossible{
		if (intituleActivite == null || intituleActivite.isBlank()) {
			throw new OperationImpossible("intituleActivite ne peut pas être null ou vide");
		}
		if (activites.get(intituleActivite) != null) {
			throw new OperationImpossible("Activite déjà dans le système");
		}
		Activite activite = new Activite(intituleActivite, description);
		this.activites.put(intituleActivite, activite);
	}

	/**
	 * ajoute une période de travail.
	 *
	 * @param intituleActivite  l'intitulé de l'activité.
	 * @param intituleTache  l'intitulé de la tâche.
	 * @param aliasDev  l'alias du developpeur.
	 * @param debut  debut de la tâche
	 * @param fin  fin de la tâche
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void ajouterUnePeriode(String intituleActivite, String intituleTache, String aliasDev, Instant debut, Instant fin) throws OperationImpossible{
		if (intituleActivite == null || intituleActivite.isBlank()) {
			throw new OperationImpossible("intituleActivite ne peut pas être null ou vide");
		}
		if (intituleTache == null || intituleTache.isBlank()) {
			throw new OperationImpossible("intituleTache ne peut pas être null ou vide");
		}
		if (aliasDev == null || aliasDev.isBlank()) {
			throw new OperationImpossible("aliasDev ne peut pas être null ou vide");
		}
		if (debut == null) {
			throw new OperationImpossible("debut ne peut pas être null");
		}
		if (fin == null) {
			throw new OperationImpossible("fin ne peut pas être null");
		}

		Activite activite = activites.get(intituleActivite);
		if (activite == null){
			throw new OperationImpossible("l'activité ne peut pas être null");
		}

		Developpeur developpeur = developpeurs.get(aliasDev);
		if (developpeur == null){
			throw new OperationImpossible("le developpeur ne peut pas être null");
		}
		activite.ajouterPeriode(intituleTache, debut, fin, developpeur);
	}

	/**
	 * met à la corbeille une tâche
	 *
	 * @param intituleActivite l'intitulé de l'activité.
	 * @param intituleTache l'intitulé de la tache.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void mettreCorbeilleUneTache(final String intituleActivite, final String intituleTache)
			throws OperationImpossible {
		if (intituleActivite == null || intituleActivite.isBlank()) {
			throw new OperationImpossible("intitulé de l'activité ne peut pas être null ou vide");
		}
		if (intituleTache == null || intituleTache.isBlank()) {
			throw new OperationImpossible("intitulé de la tâche ne peut pas être null ou vide");
		}
		Activite activite = activites.get(intituleActivite);
		if (activite == null ) {
			throw new OperationImpossible("la tache n'existe pas");
		}
		activite.mettreTacheCorbeille(intituleTache);
		assert invariant();
	}

	/**
	 * met à la corbeille un développeur.
	 *
	 * @param alias  l'alias.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void mettreCorbeilleUnDeveloppeur(final String alias)
			throws OperationImpossible {
		//TODO : Retirer dans modelisation MettreCorbeilleDeveloppeur le nom et prenom
		if (alias == null || alias.isBlank()) {
			throw new OperationImpossible("alias ne peut pas être null ou vide");
		}

		if (developpeurs.get(alias) == null) {
			throw new OperationImpossible("développeur n'existe pas");
		}
		developpeurs.get(alias).mettreALaCorbeille();
		assert invariant();
	}

	/**
	 * obtient le nom du projet.
	 * 
	 * @return le nom du projet.
	 */
	public String getNomDeProjet() {
		return nomDeProjet;
	}

	public Map<String,Activite> getActivites() {
		return this.activites;
	}
	@Override
	public String toString() {
		return "SuiPro [nomDeProjet=" + nomDeProjet + "]";
	}


	public Map<String,Developpeur> getDeveloppeurs(){
		return this.developpeurs;
	}



	/**
	 * Affiche chaque développeur qui n'est pas dans la corbeille 
	 * @param dansCorbeille boolean, si True, affiche les développeurs dans la corbeille uniquement
	 * Si false, affiche les développeurs qui ne sont PAS dans la corbeille. 
	 * 
	 */
	public void afficherDeveloppeurs(boolean dansCorbeille){
		if (!dansCorbeille)
		{
		System.out.println("Affichage des développeurs qui ne sont pas dans la corbeille :");
		}

		else 
		{
			System.out.println("Affichage des développeurs qui sont dans la corbeille :");

		}
		System.out.print("{");
		
		for (Map.Entry<String, Developpeur> entry : this.developpeurs.entrySet()) {
			Developpeur dev = entry.getValue();

			if (dev.getCorbeille() == dansCorbeille)
			{
			System.out.print(dev +", ");
			}
		}
		System.out.print("}\n\n");

	}

		/**
	 * Affiche chaque activité qui n'est pas dans la corbeille 
	 * @param dansCorbeille boolean, si True, affiche les activités dans la corbeille uniquement
	 * Si false, affiche les activités qui ne sont PAS dans la corbeille. 
	 * 
	 */
	
	 public void afficherActivites(boolean dansCorbeille){
		if (!dansCorbeille)
		{
		System.out.println("Affichage des activités qui ne sont pas dans la corbeille :");
		}

		else 
		{
		System.out.println("Affichage des activités qui sont dans la corbeille :");

		}
		System.out.println("{");

		for (Map.Entry<String, Activite> entry : this.activites.entrySet()) {
			Activite activite = entry.getValue();

			if (activite.getCorbeille() == dansCorbeille)
			{
			System.out.println(activite+", ");
			}
		}
		System.out.println("}\n\n");

	}

	public void afficherTaches(String intituleActivite, boolean dansCorbeille){

		if (!dansCorbeille)
		{
		System.out.println("Affichage des Tache associées à " + intituleActivite+ " (qui ne sont pas dans la corbeille) :");
		}

		
		Activite activite = this.activites.get(intituleActivite);
		System.out.print("{");

		for (Map.Entry<String, Tache> entry : activite.getTaches().entrySet()) {
			Tache tache = entry.getValue();

			if (tache.getCorbeille() == dansCorbeille)
			{
			System.out.print(tache+ ", ");
			}
		}
		System.out.print("}\n\n");

	}

	public void afficherTachesCorbeille(){
		System.out.println("Affichage de toutes les taches qui sont dans la corbeille :");
		for (Map.Entry<String, Activite> entry : this.activites.entrySet())
		{
			Activite activite = entry.getValue();
			afficherTaches(activite.getIntitule(), true);

		}
	}
	public void afficherPeriodesDeTravail(String intituleActivite,String intituleTache,boolean dansCorbeille){
		if (!dansCorbeille)
		{
		System.out.println("Affichage des periodes de travail associées à " + intituleTache+ " de " + intituleActivite + " (qui ne sont pas dans la corbeille) :");
		}


		Activite activite = this.activites.get(intituleActivite);
		Tache tache = activite.getTaches().get(intituleTache);
		
		System.out.print("{");

		for (Map.Entry<String, PeriodeDeTravail> entry : tache.getPeriodesDeTravail().entrySet()) {
			PeriodeDeTravail periode = entry.getValue();

			if (periode.getCorbeille() == dansCorbeille)
			{
			System.out.print(periode+ ", ");
			}
		}
		System.out.print("}\n\n");
	}


	public void afficherPeriodesDeTravailCorbeille(){
		System.out.println("Affichage de toutes les periodes de travail qui sont dans la corbeille :");
		for (Map.Entry<String, Activite> entry : this.activites.entrySet())
		{
			Activite activite = entry.getValue();

			for (Map.Entry<String, Tache> entry2 : activite.getTaches().entrySet())
			{
				Tache tache = entry2.getValue();

				afficherPeriodesDeTravail(activite.getIntitule(),tache.getIntitule(),true);
			}	

		}
	}
/*
	public void ajouterPeriodeDeTravail(String aliasDeveloppeur, String intituleActivite,String intituleTache,Instant debut, Instant fin){
		Tache tache = this.activites.get(intituleActivite).getTaches().get(intituleTache);
		Developpeur developpeur = this.developpeurs.get(aliasDeveloppeur);
		PeriodeDeTravail periodeDeTravail = new PeriodeDeTravail(debut, fin, tache, developpeur);
		developpeur.ajouterPeriodeDeTravail(periodeDeTravail)
		tache.ajouterPeriode(periodeDeTravail);}

	 */

	public void scenarioSprint1() throws OperationImpossible {
		// Ajout des développeurs
		this.ajouterUnDeveloppeur("pastorel", "Pastorel", "Emmanuel"); // 1
		this.ajouterUnDeveloppeur("duscastel", "Duscastel", "Jean-Baptiste");  // 2
		this.ajouterUnDeveloppeur("vergniaud", "Vergniaud", "Pierre-Victurnien"); // 3
		this.ajouterUnDeveloppeur("viénot-vaublanc", "Viénot-Vaublanc", "Vincent"); // 4

		// Ajout d'une activité et d'une tâche
		this.ajouterUneActivite("cd", "Conception détaillée"); // 5
		this.ajouterUneTache("cd", "dc", "Définition des classes"); // 6
		this.ajouterUneTache("cd", "mi", "Maquettage des interfaces"); // 7

		// Ajout des périodes
		this.ajouterUnePeriode("cd", "dc", "pastorel", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8
		this.ajouterUnePeriode("cd", "dc", "duscastel", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8
		this.ajouterUnePeriode("cd", "dc", "vergniaud", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8
		this.ajouterUnePeriode("cd", "dc", "viénot-vaublanc", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8

		// Erreur
		// TODO
		this.ajouterUnePeriode("cd", "dc", "pastorel", Instant.now(), Instant.now().plus(Duration.ofMinutes(30))); // 9

		// Ajout périodes de travail supplémentaires
		this.ajouterUnePeriode("cd", "mi", "pastorel", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 10
		this.ajouterUnePeriode("cd", "mi", "vergniaud", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 10
		this.ajouterUnePeriode("cd", "dc", "duscastel", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 11
		this.ajouterUnePeriode("cd", "dc", "viénot-vaublanc", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 11

		// Afficher
		this.afficherDeveloppeurs(false); // 12
		this.afficherTaches("cd", false); // 13
		this.afficherPeriodesDeTravail("cd", "dc", false); // 14
		this.afficherPeriodesDeTravail("cd", "mi", false); // 15

		// Mise à la corbeille
		this.mettreCorbeilleUnDeveloppeur("pastorel"); // 1
		this.afficherDeveloppeurs(false); // 2
		this.afficherDeveloppeurs(true); // 3
		this.afficherPeriodesDeTravail("cd", "dc", false); // 4
		this.afficherPeriodesDeTravail("cd", "mi", false); // 5
		this.afficherPeriodesDeTravailCorbeille(); // 6

		//Erreur
		this.ajouterUnePeriode("cd", "dc", "pastorel", Instant.now().plus(Duration.ofDays(2)), Instant.now().plus(Duration.ofDays(2)).plus(Duration.ofHours(1))); // 7

		this.mettreCorbeilleUnDeveloppeur("pastorel"); // 8
		this.afficherDeveloppeurs(false); // 9
		this.afficherDeveloppeurs(true); // 10
	}

	public void scenarioSprint2() throws OperationImpossible {
		// Ajout des développeurs
		this.ajouterUnDeveloppeur("braun", "Braun", "Madeleine"); // 1
		this.ajouterUnDeveloppeur("bureau-bonnard", "Bureau-Bonnard", "Carole");  // 2
		this.ajouterUnDeveloppeur("peyroles", "Peyroles", "Germaine"); // 3
		this.ajouterUnDeveloppeur("braun-pivet", "Braun-Pivet", "Yaël"); // 4

		// Ajout d'une activité et d'une tâche
		this.ajouterUneActivite("cd", "Conception détaillée"); // 5
		this.ajouterUneTache("cd", "dc", "Définition des classes"); // 6
		this.ajouterUneTache("cd", "mi", "Maquettage des interfaces"); // 7

		// Ajout des périodes
		this.ajouterUnePeriode("cd", "dc", "braun", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8
		this.ajouterUnePeriode("cd", "dc", "bureau-bonnard", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8
		this.ajouterUnePeriode("cd", "dc", "peyroles", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8
		this.ajouterUnePeriode("cd", "dc", "braun-pivet", Instant.now(), Instant.now().plus(Duration.ofHours(1))); // 8

		// Erreur
		this.ajouterUnePeriode("cd", "dc", "bureau-bonnard", Instant.now(), Instant.now().plus(Duration.ofMinutes(30))); // 9

		// Ajout périodes de travail supplémentaires
		this.ajouterUnePeriode("cd", "mi", "braun", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 10
		this.ajouterUnePeriode("cd", "mi", "bureau-bonnard", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 10
		this.ajouterUnePeriode("cd", "dc", "braun-pivet", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 11
		this.ajouterUnePeriode("cd", "dc", "peyroles", Instant.now().plus(Duration.ofDays(1)), Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(1))); // 11

		// Afficher
		this.afficherDeveloppeurs(false); // 12
		this.afficherTaches("cd", false); // 13
		this.afficherPeriodesDeTravail("cd", "dc", false); // 14
		this.afficherPeriodesDeTravail("cd", "mi", false); // 15

		// Calcul de durée
		// TODO
		// this.dureeTravailDeveloppeur("braun"); // 16
		// this.dureeTravailDeveloppeur("bureau-bonnard"); // 16
		// this.dureeTravailDeveloppeur("peyroles"); // 16
		// this.dureeTravailDeveloppeur("braun-pivet"); // 16
		// this.dureeTravailTache("cd"); // 17
		// this.dureeTravailActivite("cd"); // 18
		// this.dureeTravail(); // 19

		// Mise à la corbeille
		this.mettreCorbeilleUnDeveloppeur("bureau-bonnard"); // 1
		this.afficherDeveloppeurs(false); // 2
		this.afficherDeveloppeurs(true); // 3
		this.afficherPeriodesDeTravail("cd", "dc", false); // 4
		this.afficherPeriodesDeTravail("cd", "mi", false); // 5
		this.afficherPeriodesDeTravailCorbeille(); // 6

		// Calcul de durée
		// TODO
		// this.dureeTravailDeveloppeur("braun"); // 7
		// this.dureeTravailDeveloppeur("bureau-bonnard"); // 7
		// this.dureeTravailDeveloppeur("peyroles"); // 7
		// this.dureeTravailDeveloppeur("braun-pivet"); // 7
		// this.dureeTravailTache("cd"); // 8
		// this.dureeTravailActivite("cd"); // 9
		// this.dureeTravail(); // 10

		//Erreur
		this.ajouterUnePeriode("cd", "dc", "bureau-bonnard", Instant.now().plus(Duration.ofDays(2)), Instant.now().plus(Duration.ofDays(2)).plus(Duration.ofHours(1))); // 11

		this.mettreCorbeilleUnDeveloppeur("bureau-bonnard"); // 12
		this.afficherDeveloppeurs(false); // 13
		this.afficherDeveloppeurs(true); // 14

		// Restauration
		// TODO
		// this.restaureDeveloppeur("bureau-bonnard"); // 1
		this.afficherDeveloppeurs(false); // 2
		this.afficherDeveloppeurs(true); // 3
		// this.dureeTravail(); // 4
		// this.dureeTravailActivite(intituleActivite); // 5
		// this.dureeTravailDeveloppeur("bureau-bonnard"); // 6

		// Labellisation
		// TODO
		this.ajouterUneTache("cd", "révision", "Révision JAVA"); // 1
		this.ajouterUnePeriode("cd", "révision", "braun-pivet", Instant.now().plus(Duration.ofDays(3)), Instant.now().plus(Duration.ofDays(3)).plus(Duration.ofHours(1))); // 2
		// this.dureeTravail(); // 3
		// this.ajoutLabel("remédiation", "Remédiation"); // 4
		// this.ajoutLabelTache("révision", "remédiation"); // 5
		// this.dureeTravail("remédiation"); // 6
	}
	
}