package main.java.com.exemple.Model;


public abstract class Monstre extends Personnage{


    protected Joueur joueurCible;

    protected int id;

    public Monstre(int x, int y){

        super(x, y);
//        Random rand = new Random();
//        posX = rand.nextInt(lab.size);
//        posY = rand.nextInt(lab.size);
//        while(lab.getCase(posX,posY).estMur() || (joueur.getPosX() == posX && joueur.getPosY() == posY)){
//            posX = rand.nextInt(lab.size);
//            posY = rand.nextInt(lab.size);
//        }
//        this.joueurCible = joueur;
    }

    public boolean attaquer(){
        return (this.posX == joueurCible.getPosX() && this.posY == joueurCible.getPosY());
    }

//    public Direction getOrientation(int nb){
//        switch(nb){
//            case 0:
//                return Direction.HAUT;
//            case 1:
//                return Direction.DROITE;
//            case 2:
//                return Direction.BAS;
//            case 3:
//                return Direction.GAUCHE;
//
//
//        }
//
//        return null;
//    }

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
}
