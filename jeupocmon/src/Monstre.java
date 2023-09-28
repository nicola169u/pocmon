import java.util.Random;

public class Monstre extends Personnage{


    public Monstre(int x, int y){
        super();
        this.posX = x;
        this.posY = y;
    }

    public Direction getOrientation(int nb){
        switch(nb){
            case 0:
                return Direction.HAUT;
            case 1:
                return Direction.DROITE;
            case 2:
                return Direction.BAS;
            case 3:
                return Direction.GAUCHE;


        }

        return null;
    }

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


}
