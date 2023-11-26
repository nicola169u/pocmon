package main.java.com.exemple.Model;


/**
 * Classe représentant un monstre
 */
public abstract class Monstre extends Personnage implements Runnable{
    /**
     * Le joueur
     */
    protected Joueur joueurCible;
    /**
     * Le nombre de dégât infligé par attaque
     */
    protected int degat;


    /**
     * Constructeur de Monstre en fonction de sa postion dans labyrinthe, de ses points de vie et des dégâts
     * @param x la position en x
     * @param y la position en y
     * @param pv le nombre de points de vie
     * @param degat le nombre de dégât
     */
    public Monstre(int x, int y, int pv, int degat){

        super(x, y, pv);

        if(degat < 0){
            degat = 0;
        }
        this.degat = degat;
    }


    /**
     * Fonction qui gère les attaques du monstre
     * @return true si le monstre attaque le joueur, false sinon
     */
    public boolean attaquer(){
        // plus tard verifier en rajoutant une portée du monstre et aussi avec la distance entre le monstre et le joueur
        if(this.posX == joueurCible.getPosX() && this.posY == joueurCible.getPosY() && this.isVivant()){
            joueurCible.subirDegat(this.degat);
            return true;

        }
        return false;

    }


    /**
     * Procédure qui permet au joueur de se déplacer en temps réel sans boucle de jeu
     */
    @Override
    public void run(){
        while(joueurCible.isVivant()) {
            this.comportement();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }


    /**
     * Procédure abstraite qui gère le comportement du monstre
     */
    public abstract void comportement();

    /**
     * Getter de la position x du monstre dans le labyrinthe
     * @return la position x du monstre dans le labyrinthe
     */
    public int getX(){
        return this.posX;
    }

    /**
     * Getter de la position y du monstre dans le labyrinthe
     * @return la position y du monstre dans le labyrinthe
     */
    public int getY(){
        return this.posY;
    }

    /**
     * Setter de la position x du monstre dans le labyrinthe
     * @param x la position en x
     */
    public void setX(int x){
        this.posX = x;
    }

    /**
     * Setter de la position y du monstre dans le labyrinthe
     * @param y la position en y
     */
    public void setY(int y){
        this.posY = y;
    }

    /**
     * Fonction qui indique si le monstre est un monstre
     * @return true
     */
    @Override
    public boolean estMonstre() {
        return true;
    }

    /**
     * Getter du joueur
     * @return le joueur
     */
    public Joueur getJoueurCible() {
        return joueurCible;
    }

    /**
     * Setter du joueur
     * @param joueurCible le joueur
     */
    public void setJoueurCible(Joueur joueurCible) {
        this.joueurCible = joueurCible;
    }

    /**
     * Getter du nombre de dégâts infligé par attaque
     * @return le nombre de dégâts infligé par attaque
     */
    public int getDegat() {
        return degat;
    }

    /**
     * Setter du nombre de dégâts à infliger par attaque
     * @param degat le nombre de dégât
     */
    public void setDegat(int degat) {
        this.degat = degat;
    }


    abstract public String getType();
}
