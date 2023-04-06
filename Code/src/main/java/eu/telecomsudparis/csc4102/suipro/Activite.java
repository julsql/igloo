package eu.telecomsudparis.csc4102.suipro;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept d'une activité. Une acvitivé est un élément
 * jetable référençant une collection de période de travail.
 *
 * @author Juliette Debono
 */
public class Activite {
    /**
     * l'intitulé de l'activité.
     */
    private final String intitule;
    /**
     * la desctiption de l'activité.
     */
    private String description;
    /**
     * les tâches de l'activité.
     */
    private Map<String, Tache> taches;
    /**
     * à la corbeille ou non.
     */
    private boolean dansCorbeille;

    /**
     * construit une activité.
     *
     * @param intitule  l'intitulé.
     * @param description la desctiption.
     */
    public Activite(final String intitule, final String description) {
        super();
        if (intitule == null || intitule.isBlank()) {
            throw new IllegalArgumentException("intitule ne peut pas être null ou vide");
        }
        this.intitule = intitule;
        this.description = description;
        dansCorbeille = false;
        this.taches = new HashMap<>();
        assert invariant();
    }

    /**
     * vérifie l'invariant de la classe.
     *
     * @return {@code true} si l'invariant est respecté.
     */
    public boolean invariant() {
        return intitule != null && !intitule.isBlank() && this.taches != null;
    }
    /**
     * obtient l'intitule.
     *
     * @return l'intitule.
     */
    public String getIntitule() {
        return intitule;
    }
    /**
     * obtient la description.
     *
     * @return la description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * obtient les tâches.
     *
     * @return les tâches.
     */
    public Map<String, Tache> getTaches() {
        return taches;
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
    * @param intitule l'intitulé
	* @param description la description
    * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
    *                             de décision des tests de validation).
    */
    public void ajouterTache(final String intitule, final String description)throws OperationImpossible {


        if (this.getTaches().get(intitule) != null) {
			throw new OperationImpossible("tâche déjà dans le système");
		}

        this.getTaches().put(intitule, new Tache(intitule, description, this));
        assert invariant();
    }

    /**
     * Ajoute une période de travail.
     *
     * @param intituleTache l'intitulé de la tâche.
     * @param debut l'instant de début.
     * @param fin l'instant de fin.
     * @param developpeur le développeur.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void ajouterPeriode(final String intituleTache, final Instant debut, final Instant fin, final Developpeur developpeur) throws OperationImpossible {

        Tache tache = this.getTaches().get(intituleTache);
        if (tache == null) {
            throw new OperationImpossible("la tache ne peut pas être null");
        }

        if (tache.getCorbeille()) {
            throw new OperationImpossible("la tâche est dans la corbeille");
        }

        tache.ajouterPeriode(debut, fin, developpeur);
        assert invariant();
    }
    /**
     * Met à la corbeille une tâche.
     *
     * @param intituleTache l'intitulé de la tâche.
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void mettreTacheCorbeille(final String intituleTache) throws OperationImpossible {
        if (this.getTaches().get(intituleTache) == null) {
            throw new OperationImpossible("tâche n'existe pas");
        }
        this.getTaches().get(intituleTache).mettreALaCorbeille();
        assert invariant();
    }
    /**
     * Met à la corbeille l'activité.
     */
    public void mettreALaCorbeille() {
        dansCorbeille = true;
        assert invariant();
    }
    /**
     * Met à la corbeille une période de travail.
     *
     * @param intituleTache l'intitulé de la tâche.
     * @param debut l'instant de début.
     * @param fin l'instant de fin.
     * @param developpeur le développeur.
     *
     * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
     *                             de décision des tests de validation).
     */
    public void mettrePeriodeCorbeille(final String intituleTache, final Instant debut, final Instant fin, final Developpeur developpeur) throws OperationImpossible {
        if (this.getTaches().get(intituleTache) == null) {
            throw new OperationImpossible("tâche n'existe pas");
        }
        this.getTaches().get(intituleTache).mettrePeriodeCorbeille(debut, fin, developpeur);
        assert invariant();
    }

    /**
     * 
     * @return la durée de l'activité
     * on peut prendre en compte les taches a la corbeille, car leur périodes de travail le sont forcément aussi
     * ie pas besoin de filtrer
     */
    public Duration dureeActivite(){

		Duration duree = Duration.ZERO; // init empty duration
		for (Tache tache : taches.values()) {//foreach tache
			duree = duree.plus(tache.dureeTache()); // add its duration
		}
		return duree;
	}
    /**
     * 
     * @return Map<String,Duration> : <intitulé de la tache : durée de la tache>
     */
    public Map<String,Duration> dureeActiviteDetails(){
        Map<String,Duration> durees = new HashMap<>();
		for (Tache tache : taches.values()) {//foreach tache 
			durees.put(tache.getIntitule(), tache.dureeTache()); 
		}
		return durees;
	}


    /**
     * 
     * @return
     */

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
        Activite other = (Activite) obj;
        return Objects.equals(intitule, other.intitule);
    }

    @Override
    public String toString() {
        return "Activité [intitulé=" + intitule + ", desciption=" + description + ", dans la corbeille=" + dansCorbeille + "]";
    }
}
