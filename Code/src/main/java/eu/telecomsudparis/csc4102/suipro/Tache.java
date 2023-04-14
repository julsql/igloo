package eu.telecomsudparis.csc4102.suipro;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Cette classe réalise le concept de Tache.
 */
public class Tache {
	/**
	 * l'intitulé de la tâche.
	 */
	private final String intitule;
	/**
	 * la description de la tâche.
	 */
    private final String description;
	/**
	 * les périodes de travail de la tâche.
	 */
    private Map<String, PeriodeDeTravail> periodesDeTravail;
	/**
	 * est-ce que la tâche est dans la corbeille.
	 */
    private boolean dansCorbeille;
	/**
	 * l'activité de la tâche.
	 */
	private final Activite activite;

	/**
	 * construit un développeur.
	 * 
	 * @param intitule  l'alias.
     * @param description la description
	 * @param activite l'activité'
	 */
	public Tache(final String intitule, final String description, final Activite activite) {
		super();
		if (intitule == null || intitule.isBlank()) {
			throw new IllegalArgumentException("intitule ne peut pas être null ou vide");
		}

		this.intitule = intitule;
        this.description = description;
		this.dansCorbeille = false;
		this.periodesDeTravail = new HashMap<>();
		this.activite = activite;
		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return intitule != null && !intitule.isBlank();
	}

	/**
	 * obtient l'alias.
	 * 
	 * @return l'alias.
	 */
	public String getIntitule() {
		return intitule;
	}

    /**
	 * obtient l'alias.
	 * 
	 * @return la description.
	 */
    public String getDescription() {
		return description;
	}
	/**
	 * obtient la mise à la corbeille.
	 *
	 * @return est-ce que la tâche est dans la corbeille.
	 */
	public boolean getCorbeille() {
		return dansCorbeille;
	}


	/**
	 * restaure la tache et toutes les periodes de travail associées pour lesquelle le developpeur n'est PAS dans la corbeille.
	 */
	public void restauration() {
		this.dansCorbeille = false;
		for (var periode : periodesDeTravail.entrySet()) {
			periode.getValue().restauration();
		}
	}

	/**
	 * obtient les périodes de travail.
	 *
	 * @return les périodes de travail.
	 */
    public Map<String, PeriodeDeTravail> getPeriodesDeTravail() {
        return periodesDeTravail;
    }
	/**
	 * ajoute une période de travail.
	 *
	 * @param debut  l'instant du début.
	 * @param fin l'instant de la fin.
	 * @param developpeur le développeur.
	 */
	public void ajouterPeriode(final Instant debut, final Instant fin, final Developpeur developpeur) throws OperationImpossible {
		String id = debut + fin.toString() + developpeur;
		if (periodesDeTravail.get(id) != null) {
			throw new OperationImpossible("Période déjà dans le système des tâches");
		}

		PeriodeDeTravail newPeriode = new PeriodeDeTravail(debut, fin, this, developpeur);
		periodesDeTravail.put(id, newPeriode);
		assert invariant();
	}
	/**
	 * met la tâche à la corbeille.
	 *
	 */
	public void mettreALaCorbeille() throws InterruptedException,OperationImpossible {
        this.dansCorbeille = true;
        Map<String, PeriodeDeTravail> periodesDeTravailASupprimer = getPeriodesDeTravail();
        for (var periode : periodesDeTravailASupprimer.entrySet()) {
            periode.getValue().mettreALaCorbeille();
        }
		assert invariant();
    }
	/**
	 * met à la corbeille les périodes de travail.
	 *
	 * @param debut  l'instant du début.
	 * @param fin l'instant de la fin.
	 * @param developpeur le développeur.
	 * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
	 *                             de décision des tests de validation).
	 * @throws InterruptedException exception levée par le time.sleep
	 */
	public void mettrePeriodeCorbeille(final Instant debut, final Instant fin, final Developpeur developpeur) throws OperationImpossible, InterruptedException {
		String id = debut + fin.toString() + developpeur;

		if (periodesDeTravail.get(id) == null) {
			throw new OperationImpossible("Période n'existe pas");
		}
		periodesDeTravail.get(id).mettreALaCorbeille();
		assert invariant();
	}
	/**
	 * 
	 * @return la somme des durées de chaque période de travail associé à cette tache
	 */
	public Duration dureeTache() {
		Duration duree = Duration.ZERO; // init empty duration
			for (PeriodeDeTravail periode : periodesDeTravail.values()) { //foreach periode de travail associated,
				if (!periode.getCorbeille()) { // ignore les periodes en corbeille
				duree = duree.plus(periode.getIntervalle().calculerDuree());
			} // add its duration
		}
		return duree;
	}

	/**
	 * Supprime les périodes de travail d'une tâche.
	 *
	 */
	public void supprimerPeriodes() {
		for (PeriodeDeTravail periode : periodesDeTravail.values()) { // foreach periode de travail associated,
			if (periode.getCorbeille()) { // ne considère que les période de travaille dans la corbeille
				String id = periode.getIntervalle().getInstantDebut().toString() + periode.getIntervalle().getInstantDebut() + periode.getDeveloppeur();
				periodesDeTravail.remove(id);
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(intitule);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tache other = (Tache) obj;
		return Objects.equals(intitule, other.intitule);
	}

	@Override
	public String toString() {
		return "Tâche [intitulé=" + intitule + ", activité= "+activite+", description=" + description + ", dans la corbeille=" + dansCorbeille + "]";
	}
}
