public class Joueur extends Personnage {
    public Joueur() {
        super();
    }


    public void avancer(Direction direction, Labyrinthe lab){
        switch (direction){
            case GAUCHE:
                if(!lab.getCase(this.posX, this.posY - 1).estMur()){
                    this.posY -= 1;
                }else{
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case DROITE:
                if(!lab.getCase(this.posX, this.posY + 1).estMur()){
                    this.posY += 1;
                }else{
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case BAS:
                if(!lab.getCase(this.posX, this.posX + 1).estMur()){
                    this.posX += 1;
                }else{
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
            case HAUT:
                if(!lab.getCase(this.posX, this.posX - 1).estMur()){
                    this.posX -= 1;
                }else{
                    System.out.println("On fonce droit dans un mur inconscient !!");
                }
                break;
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
}
