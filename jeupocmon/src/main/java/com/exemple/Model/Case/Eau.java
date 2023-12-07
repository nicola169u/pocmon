package main.java.com.exemple.Model.Case;

/**
 * Classe qui représente la case remplie d'eau
 */
public class Eau extends Case{

    /**
     * Constructeur de Eau en fonction de sa position dans le labyrinthe
     * @param posX
     * @param posY
     */
    public Eau(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public boolean estEau() {
        return true;
    }

    @Override
    public String getType() {
        return "Eau";
    }
}
