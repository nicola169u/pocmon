package main.java.com.exemple.Model;

import main.java.com.exemple.Model.Case;

/**
 * Classe représentant un Tresor
 */
public class Tresor extends Case {
    /**
     * Constructeur de Tresor en fonction de sa position dans le labyrinthe
     * @param posX la position en x
     * @param posY la position en y
     */
    public Tresor(int posX, int posY) {
        super(posX, posY);
    }


    /**
     * Fonction qui indique si une case Tresor ets Tresor
     * @return true
     */
    @Override
    public boolean estTresor() {
        return true;
    }
}