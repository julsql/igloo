package eu.telecomsudparis.csc4102.suipro;

public class Label {

    private final String nom;

    /**
     * construit un label
     * @param nom le nom du label
     */

    public Label(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
    
}
