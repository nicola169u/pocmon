package main.java.com.exemple.Model.Case;

import main.java.com.exemple.Model.Case.Case;

/**
 * Classe représentant une case vide du labyrinthe
 */
public class CaseVide extends Case {
    /**
     * Constructeur de la case vide en fonction de ses coordonnées
     * @param posX la position en x
     * @param posY la position en y
     */
    public CaseVide(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public String getType() {
        return "Vide";
    }
}
