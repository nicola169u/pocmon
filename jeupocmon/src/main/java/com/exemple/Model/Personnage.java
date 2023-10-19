package main.java.com.exemple.Model;

import main.java.com.exemple.Model.Direction;
import main.java.com.exemple.Model.Labyrinthe;

public abstract class Personnage {
    protected int posX;
    protected int posY;

    public Personnage() {


    }

    public void avancer(Direction direction, Labyrinthe lab){
        switch (direction){
            case GAUCHE:
                if(!lab.getCase(this.posX - 1, this.posY).estMur()){
                    this.posX--;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case DROITE:
                if(!lab.getCase(this.posX + 1, this.posY).estMur()){
                    this.posX++;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case BAS:
                if(!lab.getCase(this.posX, this.posY + 1).estMur()){
                    this.posY++;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case HAUT:
                if(!lab.getCase(this.posX, this.posY - 1).estMur()){
                    this.posY--;
                }else if(!this.estMonstre()){
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
        }
    }


    public abstract boolean estMonstre();
}
