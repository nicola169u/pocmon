package main.java.com.exemple.Model;


import main.java.com.exemple.Tools.Direction;

/**
 * Classe représentant le Joueur
 */
public class Joueur extends Personnage {

    /**
     * Variable qui indique le nombre de dégât infligé par attaque
     */
    private int degat;
    /**
     * Variable qui indique la direction que doit prendre l'épee
     */
    private Direction directionEpee;
    /**
     * Variable qui indique si le joueur sait nager ou pas
     */
    private boolean canSwim;

    private boolean peutAvancer;



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
        canSwim = false;
        peutAvancer = true;
    }

    @Override
    public String getType() {
        if(isMort()){
            return "Mort";
        }
        switch (getDernDirection()){
            case GAUCHE:
                return "JoueurG";
            case DROITE:
                return "JoueurD";
            case BAS:
                return "JoueurB";
            default:
                return "JoueurH";
        }
    }



    /**
     * Procédure qui gère l'attaque du joueur contre le monstre m
     * @param m le monstre
     */
    public void attaquer(Monstre m){
        if(vivant && monstreACote(m)){
            m.subirDegat(degat);
            this.attaque = true;
        }else{
            attaque = false;
        }


    }


    /**
     * Fonction qui indique si le joueur est a côté du monstre m
     * @param m le monstre
     * @return true si le monstre est à côté, false sinon
     */
    public boolean monstreACote(Monstre m){
        double distance = distanceEntite(this.posX, this.posY, m.getPosX(), m.getPosY());
        if(this.posX == m.getPosX() - 1 && this.posY == m.getPosY()){
            this.directionEpee = Direction.DROITE;
        }

        if(this.posX == m.getPosX() + 1 && this.posY == m.getPosY()){
            this.directionEpee = Direction.GAUCHE;
        }

        if(this.posX == m.getPosX() && this.posY-1 == m.getPosY()){
            this.directionEpee = Direction.HAUT;
        }

        if(this.posX == m.getPosX() && this.posY+1 == m.getPosY()){
            this.directionEpee = Direction.BAS;
        }

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

    public int getDegat(){
        return degat;
    }

    public Direction getDirectionEpee(){
        return directionEpee;
    }

    public void setCanSwim(boolean swim){
        canSwim = swim;
    }

    @Override
    public boolean canSwim() {
        return canSwim;
    }

    public void reset() {
        //On le remet à la position (0;0)
        setPosX(1);
        setPosY(1);
        //On lui redonne ses points de vie
        revivre();
        resetDegat();
        setInvulnerable(false);
        //Il ne sait à nouveau pas nager
        setCanSwim(false);
    }

    public void setPeutAvancer(boolean b) {
        peutAvancer = b;
    }

    public boolean peutAvancer() {
        return peutAvancer;
    }
}