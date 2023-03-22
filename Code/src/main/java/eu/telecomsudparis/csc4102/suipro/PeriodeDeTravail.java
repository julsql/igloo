package eu.telecomsudparis.csc4102.suipro;

import java.time.Instant;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.IntervalleInstants;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept de période de travail. Un période de travail
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
	/**
	 * construit une période de travail.
	 * 
	 * @param debut       l'instant de début.
	 * @param fin         l'instant de fin.
	 * @throws OperationImpossible exception levée lorsque ...
	 */
	public PeriodeDeTravail(final Instant debut, final Instant fin)
			throws OperationImpossible {
		super();
		Objects.requireNonNull(debut, "debut ne peut pas être null");
		Objects.requireNonNull(fin, "fin ne peut pas être null");
		this.intervalle = new IntervalleInstants(debut, fin);
		this.dansCorbeille = false;
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return intervalle != null;
	}

	public void mettreALaCorbeille(){
		this.dansCorbeille = true;
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
		return "PeriodeDeTravail [intervalle=" + intervalle + ", dans la corbeille=" + dansCorbeille + "]";
	}
}
