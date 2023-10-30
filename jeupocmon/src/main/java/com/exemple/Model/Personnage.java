package main.java.com.exemple.Model;

import main.java.com.exemple.Model.Direction;
import main.java.com.exemple.Model.Labyrinthe;

public abstract class Personnage {
    protected int posX;
    protected int posY;

    protected Labyrinthe labyrinthe;

    protected boolean vivant;

    protected int pv;


    public Personnage(int x, int y, int pv) {
        this.posX = x;
        this.posY = y;
        this.vivant = true;
        this.pv = pv;



    }

    public void avancer(Direction direction){
        switch (direction){
            case GAUCHE:
                if(!labyrinthe.getCase(this.posX - 1, this.posY).estMur() && vivant){
                    this.posX--;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case DROITE:
                if(!labyrinthe.getCase(this.posX + 1, this.posY).estMur() && vivant){
                    this.posX++;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case BAS:
                if(!labyrinthe.getCase(this.posX, this.posY + 1).estMur() && vivant){
                    this.posY++;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case HAUT:
                if(!labyrinthe.getCase(this.posX, this.posY - 1).estMur() && vivant){
                    this.posY--;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
        }
    }

    public boolean isVivant(){
        return this.vivant;
    }

    public boolean isMort(){
        return !vivant;
    }

    public void setLabyrinthe(Labyrinthe labyrinthe1){
        if(labyrinthe1 != null){
            this.labyrinthe = labyrinthe1;
        }
    }

    public Labyrinthe getLabyrinthe(){
        return this.labyrinthe;
    }


    public abstract boolean estMonstre();

    public abstract boolean estFantome();

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void estMort(){
        // plus tard vérifier que pv == 0 ou non et hop
        vivant = false;
    }

    public double distancePerso(Personnage perso) {
        return Math.sqrt(Math.pow((this.posX-perso.posX),2)+Math.pow((this.posY-perso.posY),2));

    }

    public double distanceEntite(int px, int py, int dx, int dy) {
        return Math.sqrt(Math.pow((px - dx),2)+Math.pow((py - dy),2));

    }

    public void subirDegat(int degat){
        pv -= degat;
        if(pv < 0){
            pv = 0;
        }
        mort();

    }

    public int getPv() {
        return pv;
    }

    public void mort(){
        if(pv==0){
            vivant = false;
        }
    }
}