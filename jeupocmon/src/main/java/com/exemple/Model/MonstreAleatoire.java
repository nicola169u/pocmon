package main.java.com.exemple.Model;


import main.java.com.exemple.Tools.Direction;

/**
 * Classe représentant un monstre non-intelligent c'est-à-dire se déplacantde manière aléatoire
 */
public class MonstreAleatoire extends Monstre{
    /**
     * Le déplacement du MonstreAleatoire
     */
    private Direction deplacement;


    /**
     * Constructeur de MonstreAleatoire en fonction de sa position initiale dans le labyrinthe, de ses points de vie et du nombre dégâts à infligé par attaque
     * @param x la position en x
     * @param y la position en y
     * @param pv le nombre de points de vie
     * @param degat le nombre dégât
     */
    public MonstreAleatoire(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
        deplacement = randomDir();
    }


    /**
     * Procédure qui gère le comportement du MonstreAleatoire
     */
    @Override
    public void comportement() {
        avancer(deplacement);
        deplacement = randomDir();
        this.currentNbSprite = (this.currentNbSprite % this.NB_SPRITE) + 1;
        attaquer();

    }

    @Override
    public String getType() {
        if(isMort()){
            return "Mort";
        }
        return "MonstreAleatoire" + currentNbSprite;
    }


    /**
     * Fonction qui indique si le MonstreAleatoire est un MonstreAleatoire
     * @return true
     */
    @Override
    public boolean isMonstreAleatoire() {
        return true;
    }


    /**
     * Fonction qui retourne une direction aléatoire
     * @return une direction aléatoire
     */
    public Direction randomDir() {
        switch ((int) (Math.random() * 4)) {
            case 0:
                return Direction.HAUT;
            case 1:
                return Direction.DROITE;
            case 2:
                return Direction.BAS;
            case 3:
            default:
                return Direction.GAUCHE;
        }
    }

    /**
     * Getter du déplacement du MonstreAleatoire
     * @return le déplacement du MonstreAleatoire
     */
    public Direction getDeplacement(){
        return deplacement;
    }

    /**
     * Procédure qui permet au MonstreAleatoire de se déplacer en dehors de la boucle de jeu
     */
    @Override
    public void run(){

    }
}
