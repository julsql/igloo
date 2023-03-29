package eu.telecomsudparis.csc4102.suipro;

import java.time.Instant;
import java.util.Objects;

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
	private boolean dansCorbeille;
	private final Tache tache;
	private final Developpeur developpeur;
	/**
	 * construit une période de travail.
	 * 
	 * @param debut       l'instant de début.
	 * @param fin         l'instant de fin.
	 */
	public PeriodeDeTravail(final Instant debut, final Instant fin, Tache tache, Developpeur developpeur)
{
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

	public boolean getCorbeille() {
		return dansCorbeille;
	}

	public void mettreALaCorbeille(){
		dansCorbeille = true;
		assert invariant();
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
        return Objects.equals(intervalle,other.intervalle) && Objects.equals(tache,other.tache) && Objects.equals(developpeur,other.developpeur);
    }
	
}
