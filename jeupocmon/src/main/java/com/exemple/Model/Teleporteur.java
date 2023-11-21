package main.java.com.exemple.Model;


/**
 * Classe représentant un Téléporteur
 */
public class Teleporteur extends Case{

    /**
     * Constructeur de Teleporteur en fonction de sa position dans le labyrinthe
     * @param posX la position en x
     * @param posY la position en y
     */
    public Teleporteur(int posX, int posY) {
        super(posX, posY);
    }


    /**
     * Indique si un Teleporteur est Teleporteur
     * @return true
     */
    @Override
    public boolean estTeleporteur(){
        return true;
    }

}
