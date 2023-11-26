package main.java.com.exemple.Model.Case;

/**
 * Classe abstraite Case
 */
public abstract class Case {
    /**
     * Position en x de la case dans le labyrinthe
     */
    protected int posX;
    /**
     * Position en y de la case dans le labyrinthe
     */
    protected int posY;
    /**
     * Constante qui définit la taille d'une case
     */
    public static int TAILLE = 30;

    /**
     * Constructeur de Case en fonction de ses coordonnées posX et posY
     * @param posX la position en x
     * @param posY la position en y
     */
    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Getter de la position x
     * @return la position x de la case dans le labyrinthe
     */
    public int getPosX(){
        return posX;
    }

    /**
     * Getter de la position y
     * @return la position y de la case dans le labyrinthe
     */
    public int getPosY(){
        return posY;
    }

    /**
     * Indique si la case est un trésor
     * @return true si la case est un trésor, false sinon
     */
    public boolean estTresor(){
        return false;
    }

    /**
     * Indique si la case est un mur
     * @return true si la case est un mur, false sinon
     */
    public boolean estMur(){
        return false;
    }

    /**
     * Indique si la case est un teleporteur
     * @return true si la case est un teleporteur, false sinon
     */
    public boolean estTeleporteur(){
        return false;
    }


    /**
     * Getter de la taille de la case
     * @return la taille de la case
     */
    public static int getTAILLE() {
        return TAILLE;
    }

    public boolean estMine(){
        return false;
    }

    public boolean estPiege(){
        return false;
    }

    public boolean estPotionForce(){
        return false;
    }

    public boolean estPotionVie(){
        return false;
    }

    public boolean estEtoile(){
        return false;
    }

    /**
     * Retourne le type d'une case
     * @return le type d'une case
     */
    public abstract String getType();
}

