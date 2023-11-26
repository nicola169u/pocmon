package main.java.com.exemple.Model;

import main.java.com.exemple.Tools.Direction;

/**
 * Classe représentant un Fantome
 */
public class Fantome extends MonstreIntelligent {
    /**
     * Variable indiquant si le fantome est en train d'attaquer
     */
    private boolean attaque;
    /**
     * Direction prise par le fantome
     * 1 pour droite et 0 pour gauche
     */
    private boolean direction; // 1 pour droite et 0 pour gauche


    /**
     * Constructeur de Fantome en fonstion de ses coordonnées, du nombre de point de vie et des dégats qu'il peut provoquer
     * @param x la position en x
     * @param y la position en y
     * @param pv le nombre de point de vie
     * @param degat le nombre de dégât
     */
    public Fantome(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
        this.attaque = false;
        this.direction = false;
    }

    /**
     * Procédure permettant au Fantome de bien se comporter (attaques et déplacement)
     */
    @Override
    public void comportement() {
        int pxc = joueurCible.getPosX();
        int pyc = joueurCible.getPosY();

        int dx = pxc - posX;
        int dy = pyc - posY;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                deplacementEnCours = Direction.DROITE;
                this.direction = true;

            } else if (dx < 0) {
                deplacementEnCours = Direction.GAUCHE;
                this.direction = false;
            }
        } else {
            if (dy > 0) {
                deplacementEnCours = Direction.BAS;
            } else if (dy < 0) {
                deplacementEnCours = Direction.HAUT;
            }
        }

        deplacerFantome(deplacementEnCours);
        attaque = attaquer();
    }


    /**
     * Fonction qui indique si le fantome est un fantome, héritée de Monstre
     * @return true
     */
    @Override
    public boolean estFantome() {
        return true;
    }


    /**
     * Fonction qui gère le déplacement du fantome en fonction de sa direction
     * @param direction la direction empruntée
     */
    public void deplacerFantome(Direction direction){
        switch (direction){
            case GAUCHE: if(vivant) this.posX--; break;
            case DROITE: if(vivant) this.posX++; break;
            case BAS:  if(vivant) this.posY++; break;
            case HAUT: if(vivant) this.posY--; break;
        }

    }

    @Override
    public String getType() {
        if(isMort()){
            return "Mort";
        }
        return "Fantome";
    }
}
