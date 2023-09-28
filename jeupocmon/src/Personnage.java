public abstract class Personnage {
    protected int posX;
    protected int posY;

    public Personnage() {


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
}
