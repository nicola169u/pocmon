package main.java.com.exemple.Model;

import main.java.com.exemple.Model.Case;

/**
 * Classe représentant un Mur
 */
public class Mur extends Case {
    /**
     * Constructeur de Mur en fonction de sa position dans le labyrinthe
     * @param posX la position en x
     * @param posY la position en y
     */
    public Mur(int posX, int posY) {
        super(posX, posY);
    }

    /**
     * Fontion qui indique si le Mur est un Mur
     * @return true
     */
    @Override
    public boolean estMur() {
        return true;
    }
}
