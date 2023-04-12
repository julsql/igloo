package eu.telecomsudparis.csc4102.suipro;

public class Label {

     /**
     * le nom du label.
     */
    private final String nom;

    /**
     * construit un label.
     * @param nom le nom du label
     */
    public Label(final String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
    
}
