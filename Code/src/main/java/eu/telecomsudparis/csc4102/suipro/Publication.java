package eu.telecomsudparis.csc4102.suipro;

import java.util.Objects;

/**
 * Cette classe definit le type des publications transmises entre les
 * producteurs et les consommateurs.
 *
 */
public class Publication {
    /**
     * l'information.
     */
    private String contenu;

    /**
     * constructeur.
     *
     * @param intituleTache  l'intitulé de la tâche
     * @param alias  l'alias du développeur de la tpache.
     */
    public Publication(final String intituleTache, final String alias) {
        this.contenu = "Mise à la corbeille d'une période de travail de la tâche : " + intituleTache + ", " + alias + " merci de vérifier votre emploi du temps";
    }

    /**
     * obtient le contenu.
     * @return la chaîne de caractères.
     */
    public String getContenu() {
        return contenu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contenu);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Publication other)) {
            return false;
        }
        return Objects.equals(contenu, other.contenu);
    }

    @Override
    public String toString() {
        return "Publication [contenu=" + contenu + "]";
    }
}
