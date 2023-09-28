public class Joueur extends Personnage {
    public Joueur() {
        super();
        posX = 1;
        posY = 1;
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
}
