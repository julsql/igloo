package eu.telecomsudparis.csc4102.suipro;

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
	 * met à la corbeille une tâche
	 *
	 * @param alias  l'alias.
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

		if (activite.getTaches().get(intituleTache) == null) {
			throw new OperationImpossible("tâche n'existe pas");
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
	public void mettreCorbeilleUnDeveloppeur(final String alias, final String nom, final String prenom)
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

	@Override
	public String toString() {
		return "SuiPro [nomDeProjet=" + nomDeProjet + "]";
	}
}
