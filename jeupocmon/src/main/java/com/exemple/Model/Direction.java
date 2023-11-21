package main.java.com.exemple.Model;

/**
 * Enumération des différentes directions pouvant être prises par un personnage
 */
public enum Direction {
    /**
     * Les différentes directions
     */
    HAUT, BAS, GAUCHE, DROITE;

    /**
     * Fonction toString
     * @return la chaîne de caractère correspondant à la direction
     */
    @Override
    public String toString() {
        switch (this) {
            case HAUT:
                return "Haut";
            case BAS:
                return "Bas";
            case GAUCHE:
                return "Gauche";
            case DROITE:
                return "Droite";
            default:
                throw new IllegalArgumentException();
        }
    }
}


