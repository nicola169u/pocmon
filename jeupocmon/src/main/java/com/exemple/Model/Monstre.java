package main.java.com.exemple.Model;


public abstract class Monstre extends Personnage{


    protected Joueur joueurCible;


    protected int degat;

    public Monstre(int x, int y, int pv, int degat){

        super(x, y, pv);

        if(degat < 0){
            degat = 0;
        }
        this.degat = degat;
    }

    public boolean attaquer(){
        // plus tard verifier en rajoutant une portée du monstre et aussi avec la distance entre le monstre et le joueur
        if(this.posX == joueurCible.getPosX() && this.posY == joueurCible.getPosY()){
            joueurCible.subirDegat(this.degat);
            return true;

        }
        return false;

    }


    public abstract void comportement();

    public int getX(){
        return this.posX;
    }

    public int getY(){
        return this.posY;
    }


    public void setX(int x){
        this.posX = x;
    }


    public void setY(int x){
        this.posX = x;
    }

    @Override
    public boolean estMonstre() {
        return true;
    }

    public Joueur getJoueurCible() {
        return joueurCible;
    }

    public void setJoueurCible(Joueur joueurCible) {
        this.joueurCible = joueurCible;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }


}
