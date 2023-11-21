package main.java.com.exemple.Model;

public abstract class Case {
    protected int posX;
    protected int posY;

    public static int TAILLE = 30;

    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }


    public boolean estTresor(){
        return false;
    }


    public boolean estMur(){
        return false;
    }

    public boolean estTeleporteur(){
        return false;
    }
}

