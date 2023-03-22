package eu.telecomsudparis.csc4102.suipro;

import java.util.Map;
import java.util.Objects;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept de développeur. Un développeur est un élément
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
     * les périodes de travail de l'activité.
     */
    private Map<String, Tache> taches;

    private boolean dansCorbeille;

    /**
     * construit un développeur.
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
     * obtient les périodes de travail.
     *
     * @return les périodes de travail.
     */
    public Map<String, Tache> getTaches() {
        return taches;
    }

    /**
    * @param intitule  
	* @param description    
    * @throws OperationImpossible exception levée en cas d'impossibilité (cf. table
    *                             de décision des tests de validation).
    */
    public void ajouterTache(String intitule, String description)throws OperationImpossible{


        if (this.getTaches().get(intitule) != null) {
			throw new OperationImpossible("tâche déjà dans le système");
		}

        this.getTaches().put(intitule, new Tache(intitule, description));
    }

    public void mettreTacheCorbeille(String intituleTache) throws OperationImpossible
    {
        if (this.getTaches().get(intituleTache) == null) {
            throw new OperationImpossible("tâche n'existe pas");
        }
        this.getTaches().get(intituleTache).mettreALaCorbeille();
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
        Activite other = (Activite) obj;
        return Objects.equals(intitule, other.intitule);
    }

    @Override
    public String toString() {
        return "Activité [intitule=" + intitule + ", description=" + description + "]";
    }
}