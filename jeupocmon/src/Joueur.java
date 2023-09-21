public class Joueur extends Personnage {
    public Joueur() {
        super();
    }

    // Il faudrait que je récupère les cases du labyrinthe et dessus je vérifie si c'est un mur ou pas
    // ou soit il faudrait que qqn fasse ça dans la classe Labyrinthe comme ça je l'appelle juste
    // et ensuite je fais le switch
    public void avancer(Direction direction, Labyrinthe lab){
        switch(direction){
            case HAUT:
                System.out.println("vers le haut");
                break;
            case BAS:
                System.out.println("vers le bas");
                break;
            case DROITE:
                System.out.println("vers la droite");
                break;
            case GAUCHE:
                System.out.println("vers la gauche");
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
