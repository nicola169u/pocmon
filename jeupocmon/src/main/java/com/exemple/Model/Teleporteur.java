package main.java.com.exemple.Model;

public class Teleporteur extends Case{

    public Teleporteur(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public boolean estTeleporteur(){
        return true;
    }

}
