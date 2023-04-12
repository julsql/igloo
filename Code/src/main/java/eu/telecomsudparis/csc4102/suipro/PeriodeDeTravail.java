package eu.telecomsudparis.csc4102.suipro;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.SubmissionPublisher;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;

/**
 * Cette classe réalise le concept de période de travail. Une période de travail
 * est un élément jetable.
 * 
 * @author Denis Conan
 */
public class PeriodeDeTravail {
	/**
	 * l'intervalle d'instants.
	 */
	private final IntervalleInstants intervalle;
	/**
	 * est-ce que la tâche est dans la corbeille.
	 */
	private boolean dansCorbeille;
	/**
	 * la tâche.
	 */
	private final Tache tache;
	/**
	 * le développeur.
	 */
	private final Developpeur developpeur;

	/**
	 * construit une période de travail.
	 *
	 * @param debut       l'instant de début.
	 * @param fin         l'instant de fin.
	 * @param tache       la tâche
	 * @param developpeur le développeur
	 */
	public PeriodeDeTravail(final Instant debut, final Instant fin, final Tache tache, final Developpeur developpeur) {
		super();
		this.intervalle = new IntervalleInstants(debut, fin);
		this.dansCorbeille = false;
		this.tache = tache;
		this.developpeur = developpeur;

		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 *
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return intervalle != null;
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
	 * obtient le développeur.
	 *
	 * @return le développeur
	 */
	public Developpeur getDeveloppeur() {
		return developpeur;
	}

	/**
	 * met la periode de travail à la corbeille.
	 *
	 */
	public void mettreALaCorbeille() throws InterruptedException {
		dansCorbeille = true;
		final int time = 100;

		if (!developpeur.getCorbeille()) {

			String alias = developpeur.getAlias();

			SubmissionPublisher<Publication> producteur = developpeur.getProducteur();

			Thread.sleep(time);

			producteur.submit(new Publication(tache.getIntitule(), alias));

			Thread.sleep(time);
		}

		assert invariant();
	}

	/**
	 * restaure la periode de travail si le developpeur associé n'est PAS dans la corbeille.
	 * on autorise une restauration d'une periode de travail qui n'était pas dans la corbeille
	 * dans ce cas la la periode n'est pas modifiée
	 */
	public void restauration() {
		if (!developpeur.getCorbeille()) {
			this.dansCorbeille = false;
		}
	}

	/**
	 * obtient l'intervalle d'instants.
	 *
	 * @return l'intervalle d'instants.
	 */
	public IntervalleInstants getIntervalle() {
		return intervalle;
	}

	@Override
	public String toString() {
		return "PeriodeDeTravail [intervalle=" + intervalle + ", développeur=" + developpeur + ", tache=" + tache + ", dans la corbeille=" + dansCorbeille + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(intervalle);
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
        PeriodeDeTravail other = (PeriodeDeTravail) obj;
        return Objects.equals(intervalle, other.intervalle);
    }
}
