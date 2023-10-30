package main.java.com.exemple.Model;

public class Joueur extends Personnage {

    protected boolean attaque;
    private int degat;
    public Joueur(int x, int y, int pv) {
        super(x, y, pv);
        this.attaque=false;
        this.degat = 1;
//        posX = 1;
//        posY = 1;
    }

    public Direction choisirDirection(String lettre)
    {
        switch (lettre){
            case "z":
                return Direction.HAUT;
            case "q":
                return Direction.GAUCHE;
            case "s":
                return Direction.BAS;
            case "d":
                return Direction.DROITE;
        }

        return null;

    }

    public void attaquer(Monstre m){
        if(vivant){
            m.subirDegat(degat);
        }

    }


    public int getPosX(){
        return this.posX;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    @Override
    public boolean estMonstre() {
        return false;
    }

    @Override
    public boolean estFantome() {
        return false;
    }
}