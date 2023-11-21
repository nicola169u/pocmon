package main.java.com.exemple.Model;

public class Fantome extends MonstreIntelligent {

    private boolean attaque;
    private boolean direction; // 1 pour droite et 0 pour gauche
    public Fantome(int x, int y, int pv, int degat) {
        super(x,y, pv, degat);
        this.attaque = false;
        this.direction = false;
    }

    @Override
    public void comportement() {
        int pxc = joueurCible.getPosX();
        int pyc = joueurCible.getPosY();

        int dx = pxc - posX;
        int dy = pyc - posY;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                deplacementEnCours = Direction.DROITE;
                this.direction = true;

            } else if (dx < 0) {
                deplacementEnCours = Direction.GAUCHE;
                this.direction = false;
            }
        } else {
            if (dy > 0) {
                deplacementEnCours = Direction.BAS;
            } else if (dy < 0) {
                deplacementEnCours = Direction.HAUT;
            }
        }

        deplacerFantome(deplacementEnCours);
        attaque = attaquer();
    }


    @Override
    public boolean estFantome() {
        return true;
    }


    public void deplacerFantome(Direction direction){
        switch (direction){
            case GAUCHE: if(vivant) this.posX--; break;
            case DROITE: if(vivant) this.posX++; break;
            case BAS:  if(vivant) this.posY++; break;
            case HAUT: if(vivant) this.posY--; break;
        }

    }

    public boolean getAttaque(){
        return attaque;
    }

    public boolean getDirection(){
        return direction;
    }

}
