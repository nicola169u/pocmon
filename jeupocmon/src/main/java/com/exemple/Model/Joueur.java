package main.java.com.exemple.Model;


/**
 * Classe représentant le Joueur
 */
public class Joueur extends Personnage {
    /**
     * Variable qui indique si le joueur est en train d'attaquer
     */
    protected boolean attaque;
    /**
     * Variable qui indique le nombre de dégât infligé par attaque
     */
    private int degat;

    /**
     * Constructeur de Joueur en fonction de sa position dans le labyrinthe et de ses points de vie
     * @param x la position en x
     * @param y la position en y
     * @param pv le nombre de points de vie
     */
    public Joueur(int x, int y, int pv) {
        super(x, y, pv);
        this.attaque=false;
        this.degat = 5;
        setVitesse(5);
    }

    /**
     * Procédure qui gère l'attaque du joueur contre le monstre m
     * @param m le monstre
     */
    public void attaquer(Monstre m){
        if(vivant && monstreACote(m)){
            m.subirDegat(degat);
        }

    }


    /**
     * Fonction qui indique si le joueur est a côté du monstre m
     * @param m le monstre
     * @return true si le monstre est à côté, false sinon
     */
    public boolean monstreACote(Monstre m){
        double distance = distanceEntite(this.posX, this.posY, m.getPosX(), m.getPosY());

        return (distance<=1);

    }

    /**
     * Setter de attaque
     * @param attaque si le joueur attaque ou pas
     */
    public void setAttaque(boolean attaque){
        this.attaque = attaque;
    }


    /**
     * Permet au Joueur de recouvrer tous ses points de vie
     */
    public void revivre() {
        setPv(getPvMax());
        this.vivant = true;
    }

    /**
     * Permet de reset les dégâts du Joueur
     */
    public void resetDegat() {
        this.degat = 5;
    }


    public void setDegat(int d){
        this.degat = d;
    }
}