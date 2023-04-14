package eu.telecomsudparis.csc4102.suipro;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.SubmissionPublisher;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

// TODO Tests patron, finir les fonctions de listings

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
		if (activite == null) {
			throw new OperationImpossible("l'activité ne peut pas être null");
		}
		if (activite.getCorbeille()) {
			throw new OperationImpossible("l'activité est dans la corbeille");
		}
		activite.ajouterTache(intituleTache, description);

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
	public void ajouterUneActivite(final  String intituleActivite, final String description) throws OperationImpossible {
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
	public void ajouterUnePeriode(final String intituleActivite, final String intituleTache, final String aliasDev, final Instant debut, final Instant fin) throws OperationImpossible {
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
		if (activite == null) {
			throw new OperationImpossible("l'activité ne put pas être null");
		}

		Developpeur developpeur = developpeurs.get(aliasDev);
		if (developpeur == null) {
			throw new OperationImpossible("le developpeur ne peut pas être null");
		}
		if (developpeur.getCorbeille()) {
			throw new OperationImpossible("le développeur est dans la corbeille");
		}
		activite.ajouterPeriode(intituleTache, debut, fin, developpeur);
		String id = debut + fin.toString() + developpeur;
		PeriodeDeTravail newPeriode = activite.getTaches().get(intituleTache).getPeriodesDeTravail().get(id);
		developpeur.ajouterUnePeriode(newPeriode, debut, fin);
	}

	/**
	 * met à la corbeille une tâche.
	 *
	 * @param intituleActivite l'intitulé de l'activité.
	 * @param intituleTache l'intitulé de la tache.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 * @throws InterruptedException exception levée par le time.sleep
	 */
	public void mettreCorbeilleUneTache(final String intituleActivite, final String intituleTache)
			throws OperationImpossible, InterruptedException {
		if (intituleActivite == null || intituleActivite.isBlank()) {
			throw new OperationImpossible("intitulé de l'activité ne peut pas être null ou vide");
		}
		if (intituleTache == null || intituleTache.isBlank()) {
			throw new OperationImpossible("intitulé de la tâche ne peut pas être null ou vide");
		}
		Activite activite = activites.get(intituleActivite);
		if (activite == null) {
			throw new OperationImpossible("la tache n'existe pas");
		}

		activite.mettreTacheCorbeille(intituleTache);

		assert invariant();
	}

	/**
	 * met à la corbeille une période de travail.
	 *
	 * @param intituleActivite l'intitulé de l'activité.
	 * @param intituleTache l'intitulé de la tache.
	 * @param aliasDev l'alias du développeur.
	 * @param debut l'instant de début de la période de travail.
	 * @param fin l'instant de fin de la période de travail.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void mettreCorbeilleUnePeriode(final String intituleActivite, final String intituleTache, final String aliasDev, final Instant debut, final Instant fin)
			throws OperationImpossible, InterruptedException {
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
		if (activite == null) {
			throw new OperationImpossible("l'activité ne put pas être null");
		}

		Developpeur developpeur = developpeurs.get(aliasDev);
		if (developpeur == null) {
			throw new OperationImpossible("le developpeur ne peut pas être null");
		}

		activite.mettrePeriodeCorbeille(intituleTache, debut, fin, developpeur);
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
			throws OperationImpossible, InterruptedException {
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
	 * met à la corbeille une activité.
	 *
	 * @param intitule  l'intitulé.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void mettreCorbeilleUneActivite(final String intitule)
			throws OperationImpossible, InterruptedException {
		if (intitule == null || intitule.isBlank()) {
			throw new OperationImpossible("intitule ne peut pas être null ou vide");
		}

		if (activites.get(intitule) == null) {
			throw new OperationImpossible("développeur n'existe pas");
		}
		activites.get(intitule).mettreALaCorbeille();
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

	/**
	 * obtient les activités.
	 *
	 * @return les activités.
	 */
	public Map<String, Activite> getActivites() {
		return this.activites;
	}

	/**
	 * obtient les développeurs.
	 *
	 * @return les développeurs.
	 */
	public Map<String, Developpeur> getDeveloppeurs() {
		return this.developpeurs;
	}

	@Override
	public String toString() {
		return "SuiPro [nomDeProjet=" + nomDeProjet + "]";
	}

	/**
	 * Affiche chaque développeur qui n'est pas dans la corbeille .
	 *
	 */
	public void afficherDeveloppeurs() {
		
		
		for (Map.Entry<String, Developpeur> entry : this.developpeurs.entrySet()) {
			Developpeur dev = entry.getValue();

			if (dev.getCorbeille()) {
				System.out.println(dev + ", ");
			}
		}
		System.out.println("\n");
	}

	/**
	 * Affiche les développeurs dans la corbeille.
	 */
	public void afficherDeveloppeursCorbeille() {
		for (Map.Entry<String, Developpeur> entry : this.developpeurs.entrySet()) {
			Developpeur dev = entry.getValue();
			if (!dev.getCorbeille()) {
				System.out.println(dev + ", ");
			}
		}
			System.out.println("\n");
		}

	/**
	 * Affiche chaque activité qui n'est pas dans la corbeille.
	 * @param dansCorbeille boolean, si True, affiche les activités dans la corbeille uniquement
	 * Si false, affiche les activités qui ne sont PAS dans la corbeille. 
	 * 
	 */
	 public void afficherActivites(final boolean dansCorbeille) {
		if (!dansCorbeille) {
			System.out.println("Affichage des activités qui ne sont pas dans la corbeille :");
		} else {
			System.out.println("Affichage des activités qui sont dans la corbeille :");
		}
		System.out.println("{");

		for (Map.Entry<String, Activite> entry : this.activites.entrySet()) {
			Activite activite = entry.getValue();

			if (activite.getCorbeille() == dansCorbeille) {
				System.out.println(activite + ", ");
			}
		}
		System.out.println("}\n");
	}

	/**
	 * Affiche les activités dans la corbeille.
	 */
	public void afficherActivitesCorbeille() {
		afficherActivites(true);
	}

	/**
	 * Affiche les taches.
	 *
	 * @param intituleActivite intitulé de l'activité
	 * @param dansCorbeille boolean ; est dans la corbeille
	 */
	public void afficherTaches(final String intituleActivite, final boolean dansCorbeille) {

		if (!dansCorbeille) {
			System.out.println("Affichage des tâches associées à " + intituleActivite + " (qui ne sont pas dans la corbeille) :");
		}

		Activite activite = this.activites.get(intituleActivite);
		System.out.println("{");

		for (Map.Entry<String, Tache> entry : activite.getTaches().entrySet()) {
			Tache tache = entry.getValue();

			if (tache.getCorbeille() == dansCorbeille) {
				System.out.println(tache + ", ");
			}
		}
		System.out.println("}\n");	
	}
	/**
	 * Affiche les taches dans la corbeille.
	 */
	public void afficherTachesCorbeille() {
		System.out.println("Affichage de toutes les tâches qui sont dans la corbeille :");
		for (Map.Entry<String, Activite> entry : this.activites.entrySet()) {
			Activite activite = entry.getValue();
			afficherTaches(activite.getIntitule(), true);
		}
	}
	/**
	 * Affiche les taches dans la corbeille.
	 *
	 * @param intituleActivite intitulé de l'activité
	 * @param intituleTache intitulé de la tâche
	 * @param dansCorbeille est dans la corbeille
	 */
	public void afficherPeriodesDeTravail(final String intituleActivite, final String intituleTache, final boolean dansCorbeille) {
		Activite activite = this.activites.get(intituleActivite);
		Tache tache = activite.getTaches().get(intituleTache);
		 if (!dansCorbeille) {
			System.out.println("Affichage des périodes de travail associées à " + intituleTache + " de " + intituleActivite + " (qui ne sont pas dans la corbeille) :");
			System.out.println("{");
		}
		for (Map.Entry<String, PeriodeDeTravail> entry : tache.getPeriodesDeTravail().entrySet()) {
			PeriodeDeTravail periode = entry.getValue();

			if (periode.getCorbeille() == dansCorbeille) {
				System.out.println(periode + ", ");
			}
		}
		if (!dansCorbeille) {
			 System.out.println("}\n");
		}
	}

	/**
	 * Affiche les toutes les periodes de travail dans la corbeille.
	 */
	public void afficherPeriodesDeTravailCorbeille() {
		System.out.println("Affichage de toutes les périodes de travail qui sont dans la corbeille :");
		System.out.println("{");
		for (Map.Entry<String, Activite> entry : this.activites.entrySet()) {
			Activite activite = entry.getValue();

			for (Map.Entry<String, Tache> entry2 : activite.getTaches().entrySet()) {
				Tache tache = entry2.getValue();
				afficherPeriodesDeTravail(activite.getIntitule(), tache.getIntitule(), true);
			}
		}
		System.out.println("}\n");
	}
	/**
	 * Liste toutes les taches qui sont dans la corbeille.
	 * @return List<String> liste des intitulés des activités à la corbeille
	 **/
	public List<String> listerTachesCorbeille() {
		List<String> listIntitule = new ArrayList<>();
		
		for (Map.Entry<String, Activite> entry : this.activites.entrySet()) {
			Activite activite = entry.getValue();
			Collection<Tache> taches = activite.getTaches().values();

			taches.stream().filter(s -> s.getCorbeille());
			taches.forEach((tache) -> listIntitule.add(tache.getIntitule()));
			
		}
		return listIntitule;
		
	}
	/**
	 *
	 * @return List<String> liste des alias des developpeurs à la corbeille
	 */
	public List<String> listerDeveloppeursCorbeille() {
		List<String> listAlias = new ArrayList<>();
		Collection<Developpeur> developpeursCorbeille =  this.developpeurs.values();

		developpeursCorbeille.stream()							 .filter(s -> s.getCorbeille())
							 .forEach((dev) -> listAlias.add(dev.getAlias()));
		return listAlias;

	}

	/**
	 *
	 * @return List<String> liste des intitulés des activités à la corbeille
	 */
	public List<String> listerActivitesCorbeille() throws OperationImpossible {
		List<String> listIntitule = new ArrayList<>();
		Collection<Activite> activitesCorbeille =  this.activites.values();

		activitesCorbeille.stream()
        .filter(activite -> activite.getCorbeille())
		.forEach((activite) -> listIntitule.add(activite.getIntitule()));
		return listIntitule;

	}

	/**
	 *
	 * @return List<String> liste des alias des periodes à la corbeille
	 */
	public List<PeriodeDeTravail> listerPeriodesCorbeille() {
		List<PeriodeDeTravail> listPeriodesCorbeille = new ArrayList<>();
		for (Activite activite : activites.values()) {
			for (Tache tache : activite.getTaches().values()) {
				Collection<PeriodeDeTravail> listeperiode = tache.getPeriodesDeTravail().values();
				listeperiode.stream()
							.filter(periode -> periode.getCorbeille())
							.forEach(periode -> listPeriodesCorbeille.add(periode));
			}
			
		}
		return listPeriodesCorbeille;

	}

	/**
	 *
	 * @return Duration durée du projet
	 */
	public Duration dureeTravail() {

		Duration duree = Duration.ZERO; // init empty duration
		for (Activite activite : activites.values()) { //foreach activite
			duree = duree.plus(activite.dureeActivite());
			 // add its duration
		}
		return duree;
	}

	/**
	 * @param alias alias du développeur
	 *
	 * @return Duration durée du développeur
	 */
	public Duration dureeTravailDeveloppeur(final String alias) {
		Developpeur developpeur = developpeurs.get(alias);
		return developpeur.dureeTravail();
	}

	/**
	 * @param intituleActivite intitulé de l'activité
	 * @param intituleTache intitulé de la tâche
	 *
	 * @return Duration durée d'une tâche
	 */
	public Duration dureeTravailTache(final String intituleActivite, final String intituleTache) {
		Activite activite = activites.get(intituleActivite);
		Tache tache = activite.getTaches().get(intituleTache);
		return tache.dureeTache();
	}

	/**
	 * @param intituleActivite intitulé de l'activité
	 *
	 * @return Duration durée d'une acvtivité
	 */
	public Duration dureeTravailActivite(final String intituleActivite) {
		Activite activite = activites.get(intituleActivite);
		return activite.dureeActivite();
	}
	/**
	 * restaure le developpeur ayant l'alias alias.
	 * @param alias
	 */
	public void restaurerDeveloppeur(final String alias) {
		developpeurs.get(alias).restauration();
	}
		/**
	 * restaure la tache  ayant l'intitule associé.
	 * @param intitule
	 */
	public void restaurerActivite(final String intitule) {
		activites.get(intitule).restauration();
	}

	/**
	 * Vider la corbeille.
	 *
	 */
	public void viderLaCorbeille() {
		for (Developpeur developpeur : developpeurs.values()) { // foreach periode de travail associated,
			if (developpeur.getCorbeille()) { // ne considère que les période de travaille dans la corbeille
				developpeurs.remove(developpeur.getAlias());
			}
		}

		for (Activite activite : activites.values()) { // foreach periode de travail associated,
			if (activite.getCorbeille()) { // ne considère que les période de travaille dans la corbeille
				activites.remove(activite.getIntitule());
			}
		}

		System.out.println("Corbeille vidée");
		assert invariant();
	}

	/**
	 * @param alias alias de développeur
	 * @param consommateur consommateur
	 *
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 */
	public void ajouterConsommateur(final String alias, final ConsommateurMiseALaCorbeille consommateur) throws OperationImpossible {
		if (alias == null || alias.isBlank()) {
			throw new OperationImpossible("intitule ne peut pas être null ou vide");
		}
		if (consommateur == null) {
			throw new OperationImpossible("consommateur null");
		}
		if (developpeurs.get(alias) == null) {
			throw new OperationImpossible("développeur n'existe pas");
		}
		SubmissionPublisher<Publication> producteur = new SubmissionPublisher<>();
		producteur.subscribe(consommateur);
		developpeurs.get(alias).setProducteur(producteur);
	}
}
