package eu.telecomsudparis.csc4102.suipro;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.SubmissionPublisher;

/**
 * Cette classe réalise le concept de développeur. Un développeur est un élément
 * jetable référençant une collection de période de travail.
 * 
 * @author Denis Conan
 */
public class Developpeur {
	/**
	 * l'alias du développeur.
	 */
	private final String alias;
	/**
	 * le nom du développeur.
	 */
	private String nom;
	/**
	 * le prénom du développeur.
	 */
	private String prenom;
	/**
	 * à la corbeille ou non.
	 */
	private boolean dansCorbeille;
	/**
	 * les périodes de travail.
	 */
	private Map<String, PeriodeDeTravail> periodesDeTravail;
	/**
	 * les producteurs.
	 */
	private SubmissionPublisher<Publication> producteur;

	/**
	 * construit un développeur.
	 * 
	 * @param alias  l'alias.
	 * @param nom    le nom.
	 * @param prenom le prénom.
	 */
	public Developpeur(final String alias, final String nom, final String prenom) {
		super();
		if (alias == null || alias.isBlank()) {
			throw new IllegalArgumentException("alias ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new IllegalArgumentException("prenom ne peut pas être null ou vide");
		}
		this.alias = alias;
		this.nom = nom;
		this.prenom = prenom;
		this.dansCorbeille = false;
		this.periodesDeTravail = new HashMap<>();
		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return alias != null && !alias.isBlank() && nom != null && !nom.isBlank() && prenom != null
				&& !prenom.isBlank() && periodesDeTravail != null;
	}

	/**
	 * obtient l'alias.
	 * 
	 * @return l'alias.
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * obtient le producteur.
	 *
	 * @return le producteur.
	 */
	public SubmissionPublisher<Publication> getProducteur() {
		return producteur;
	}

	/**
	 * obtient le producteur.
	 *
	 * @param producteur le producteur.
	 */
	public void setProducteur(final SubmissionPublisher<Publication> producteur) {
		this.producteur = producteur;
	}

	/**
	 * obtient si c'est dans la corbeille.
	 *
	 * @return l'état de mise en corbeille.
	 */
	public boolean getCorbeille() {
		return dansCorbeille;
	}
	/**
	 * obtient les périodes de travail.
	 *
	 * @return les périodes de travail.
	 */
	public Map<String, PeriodeDeTravail> getPeriodesDeTravail() {
		return periodesDeTravail;
	}
	@Override
	public int hashCode() {
		return Objects.hash(alias);
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
		Developpeur other = (Developpeur) obj;
		return Objects.equals(alias, other.alias);
	}

	/**
	 * met le développeur à la corbeille.
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
	 * ajoute une période de travail.
	 *
	 * @param newPeriode  l'alias.
	 * @param debut    le nom.
	 * @param fin le prénom.
	 */
	public void ajouterUnePeriode(final PeriodeDeTravail newPeriode, final Instant debut, final Instant fin) throws OperationImpossible {

		IntervalleInstants intervalle = new IntervalleInstants(debut, fin);
		for (var periode : periodesDeTravail.entrySet()) {
			if (chevauchement(periode.getValue().getIntervalle(), intervalle)) {
				throw new OperationImpossible("La période chevauche une autre période");
			}
		}
		String id = debut + fin.toString() + this;
		periodesDeTravail.put(id, newPeriode);
		assert invariant();
	}

	/**
	 * calcul la durée de travail d'un développeur.
	 *
	 * @return Duration dirée de travail du développeur.
	 */
	public Duration dureeTravail() {
		Duration duree = Duration.ZERO; // init empty duration
		for (PeriodeDeTravail periode : this.periodesDeTravail.values()) {
			if (!periode.getCorbeille()) { // ignore les periodes en corbeille
				duree = duree.plus(periode.getIntervalle().calculerDuree()); // foreach periode de travail, add its duration
			}
		}
		return duree;
	}

	/**
	 * restaure un developpeur.
	 * on autorise de restaurer un developpeur qui n'est pas dans la corbeille
	 * cette methode n'aura alors pas d'effet sur le developpeur
	 */
	public void restauration() {
		this.dansCorbeille = false;
	}

	/**
	 * regarde s'il y a chevauchement entre deux intervalles.
	 *
	 * @param intervalle1  l'alias.
	 * @param intervalle2  le nom.
	 *
	 * @return s'il y a chevauchement.
	 */
	public boolean chevauchement(final IntervalleInstants intervalle1, final IntervalleInstants intervalle2) {
		// Intervalle1 = [a, b]
		Instant a = intervalle1.getInstantDebut();
		Instant b = intervalle1.getInstantFin();

		// Intervalle1 = [c, d]
		Instant c = intervalle2.getInstantDebut();
		Instant d = intervalle2.getInstantFin();

		// Vérifier si l'intervalle1 commence avant l'intervalle2 et se termine avant l'intervalle2
		if (a.isBefore(c) && b.isBefore(c)) {
			return false;
		}
		// Vérifier si l'intervalle2 commence avant l'intervalle1 et se termine avant l'intervalle1
		return !(c.isBefore(a) && d.isBefore(a));
	}


	@Override
	public String toString() {
		return "Developpeur [alias=" + alias + ", nom=" + nom + ", prénom=" + prenom + ", dans la corbeille=" + dansCorbeille + "]";
	}
}
