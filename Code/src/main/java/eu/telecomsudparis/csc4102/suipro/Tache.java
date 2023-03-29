package eu.telecomsudparis.csc4102.suipro;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Cette classe réalise le concept de Tache.
 */
public class Tache {
	/**
	 * l'intitulé de la tache.
	 */
	private final String intitule;

    private final String description;

    private Map<String,PeriodeDeTravail> periodesDeTravail;

    private boolean dansCorbeille;
	private final Activite activite;

	/**
	 * construit un développeur.
	 * 
	 * @param intitule  l'alias.
     * @param description la description
	 */
	public Tache(final String intitule, final String description, Activite activite) {
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

	public boolean getCorbeille() {
		return dansCorbeille;
	}

    public Map<String,PeriodeDeTravail> getPeriodesDeTravail(){
        return periodesDeTravail;
    }

	public void ajouterPeriode(Instant debut, Instant fin, Developpeur developpeur) throws OperationImpossible {
		String id = debut + fin.toString() + developpeur;
		if (periodesDeTravail.get(id) != null) {
			throw new OperationImpossible("Période déjà dans le système des tâches");
		}

		PeriodeDeTravail newPeriode = new PeriodeDeTravail(debut, fin, this, developpeur);
		periodesDeTravail.put(id, newPeriode);
		assert invariant();
	}

	public void mettreALaCorbeille(){
        this.dansCorbeille = true;
        Map<String,PeriodeDeTravail> periodesDeTravailASupprimer = getPeriodesDeTravail();
        for (var periode : periodesDeTravailASupprimer.entrySet()) {
            periode.getValue().mettreALaCorbeille();
        }
		assert invariant();
    }

	public void mettrePeriodeCorbeille(Instant debut, Instant fin, Developpeur developpeur) throws OperationImpossible {
		String id = debut + fin.toString() + developpeur;

		if (periodesDeTravail.get(id) == null) {
			throw new OperationImpossible("Période n'existe pas");
		}
		periodesDeTravail.get(id).mettreALaCorbeille();
		assert invariant();
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
		return Objects.equals(intitule, other.intitule) && Objects.equals(activite, other.activite);
	}

	@Override
	public String toString() {
		return "Tâche [intitulé=" + intitule + ", desciption=" + description + ", dans la corbeille=" + dansCorbeille + "]";
	}
}
