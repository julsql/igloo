package eu.telecomsudparis.csc4102.suipro;

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

    private String description;

    private Map<String,PeriodeDeTravail> periodesDeTravail;

    private boolean dansCorbeille;

	/**
	 * construit un développeur.
	 * 
	 * @param intitule  l'alias.
     * @param description la description
	 */
	public Tache(final String intitule, final String description ) {
		super();
		if (intitule == null || intitule.isBlank()) {
			throw new IllegalArgumentException("intitule ne peut pas être null ou vide");
		}

		this.intitule = intitule;
        this.description = description;
		dansCorbeille = false;
		this.periodesDeTravail = new HashMap<>();

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

    public Map<String,PeriodeDeTravail> getPeriodesDeTravail(){
        return periodesDeTravail;
    }

    public void mettreALaCorbeille(){
        this.dansCorbeille = true;
        Map<String,PeriodeDeTravail> periodesDeTravailASupprimer = getPeriodesDeTravail();
        for (var periode : periodesDeTravailASupprimer.entrySet()) {
            periode.getValue().mettreALaCorbeille();
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
		return "Tache [intitulé=" + intitule + ", description=" + description + ", dans la corbeille=" + dansCorbeille + "]";
	}
}
